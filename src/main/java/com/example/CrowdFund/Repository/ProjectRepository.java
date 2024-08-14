package com.example.CrowdFund.Repository;

import com.example.CrowdFund.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
