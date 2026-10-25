package com.skillops.domain4_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain4_learning.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
