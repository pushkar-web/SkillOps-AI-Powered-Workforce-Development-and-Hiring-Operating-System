package com.skillops.domain8_recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain8_recruitment.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
