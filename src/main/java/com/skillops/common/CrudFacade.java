package com.skillops.common;

import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CrudFacade {

    private static final Logger log = LoggerFactory.getLogger(CrudFacade.class);
    private final ObjectMapper objectMapper;

    public CrudFacade(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> findAll(JpaRepository<T, Long> repository) {
        return repository.findAll();
    }

    public <T> T get(JpaRepository<T, Long> repository, Long id, String resource) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resource + " not found: " + id));
    }

    public <T> T create(JpaRepository<T, Long> repository, T entity) {
        setId(entity, null);
        T saved = repository.save(entity);
        log.info("Created {} record", saved.getClass().getSimpleName());
        return saved;
    }

    public <T> T replace(JpaRepository<T, Long> repository, Long id, T incoming, String resource) {
        T existing = get(repository, id, resource);
        copyNonNull(incoming, existing, "id");
        T saved = repository.save(existing);
        log.info("Replaced {} id={}", saved.getClass().getSimpleName(), id);
        return saved;
    }

    public <T> T patch(JpaRepository<T, Long> repository, Long id, Map<String, Object> updates, String resource) {
        T existing = get(repository, id, resource);
        Map<String, Object> copy = new LinkedHashMap<>(updates);
        copy.remove("id");
        try {
            objectMapper.updateValue(existing, copy);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid PATCH body: " + ex.getMessage());
        }
        T saved = repository.save(existing);
        log.info("Patched {} id={}", resource, id);
        return saved;
    }

    public <T> void delete(JpaRepository<T, Long> repository, Long id, String resource) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(resource + " not found: " + id);
        }
        repository.deleteById(id);
        log.info("Deleted {} id={}", resource, id);
    }

    private void copyNonNull(Object source, Object target, String... ignore) {
        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper dest = new BeanWrapperImpl(target);
        for (PropertyDescriptor descriptor : src.getPropertyDescriptors()) {
            String name = descriptor.getName();
            if ("class".equals(name) || List.of(ignore).contains(name)) {
                continue;
            }
            if (!src.isReadableProperty(name) || !dest.isWritableProperty(name)) {
                continue;
            }
            Object value = src.getPropertyValue(name);
            if (value != null) {
                dest.setPropertyValue(name, value);
            }
        }
    }

    private void setId(Object entity, Long id) {
        try {
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
        } catch (ReflectiveOperationException ignored) {
            // entities without setId are left unchanged
        }
    }
}
