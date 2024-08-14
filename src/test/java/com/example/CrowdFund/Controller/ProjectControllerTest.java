package com.example.CrowdFund.Controller;

import com.example.CrowdFund.Controllers.ProjectController;
import com.example.CrowdFund.Entity.Project;
import com.example.CrowdFund.Entity.Status;
import com.example.CrowdFund.Services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testGetAllProjects() throws Exception {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");
        project1.setDescription("Description 1");
        project1.setFund(1000.0);
        project1.setStatus(Status.ACTIVE);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");
        project2.setDescription("Description 2");
        project2.setFund(2000.0);
        project2.setStatus(Status.ACTIVE);

        List<Project> projects = Arrays.asList(project1, project2);

        when(projectService.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Project 1"))
                .andExpect(jsonPath("$[1].title").value("Project 2"))
                .andDo(print());
    }

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setTitle("Project 1");
        project.setDescription("Description 1");
        project.setFund(1000.0);
        project.setStatus(Status.ACTIVE);

        when(projectService.saveProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Project 1\", \"description\": \"Description 1\", \"fund\": 1000.0, \"status\": \"ACTIVE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Project 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.fund").value(1000.0))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andDo(print());
    }
}