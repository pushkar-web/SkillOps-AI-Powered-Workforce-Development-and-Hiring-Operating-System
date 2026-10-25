package com.skillops.domain5_projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain5_projects.entity.PortfolioItem;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
}
