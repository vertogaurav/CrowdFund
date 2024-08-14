package com.example.CrowdFund.Services;

import com.example.CrowdFund.Entity.Project;
import com.example.CrowdFund.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        if (project != null) {
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setFund(projectDetails.getFund());
            project.setStatus(projectDetails.getStatus());
            return projectRepository.save(project);
        }
        return null;
    }
}