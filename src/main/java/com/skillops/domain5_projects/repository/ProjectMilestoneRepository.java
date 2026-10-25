package com.skillops.domain5_projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain5_projects.entity.ProjectMilestone;

public interface ProjectMilestoneRepository extends JpaRepository<ProjectMilestone, Long> {
}
