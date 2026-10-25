package com.skillops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SkillOpsRestApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void allTenDomainSummariesAreReachable() throws Exception {
        mockMvc.perform(get("/api/v1/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
        mockMvc.perform(get("/api/v1/profiles-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.profiles").exists());
        mockMvc.perform(get("/api/v1/skills-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.skills").exists());
        mockMvc.perform(get("/api/v1/learning-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.courses").exists());
        mockMvc.perform(get("/api/v1/projects-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.projects").exists());
        mockMvc.perform(get("/api/v1/assessments-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.assessments").exists());
        mockMvc.perform(get("/api/v1/interviews-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.interviews").exists());
        mockMvc.perform(get("/api/v1/recruitment-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.companies").exists());
        mockMvc.perform(get("/api/v1/notifications-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.notifications").exists());
        mockMvc.perform(get("/api/v1/analytics-domain/summary")).andExpect(status().isOk()).andExpect(jsonPath("$.dashboards").exists());
    }

    @Test
    void swaggerDocumentsAllTenDomainsInOrder() throws Exception {
        MvcResult result = mockMvc.perform(get("/v3/api-docs")).andExpect(status().isOk()).andReturn();
        JsonNode tags = objectMapper.readTree(result.getResponse().getContentAsString()).get("tags");
        assertThat(tags.get(0).get("name").asText()).isEqualTo("Domain 1: Identity & Authentication");
        assertThat(tags.get(5).get("name").asText()).isEqualTo("Domain 6: Assessments");
        assertThat(tags.get(9).get("name").asText()).isEqualTo("Domain 10: Analytics");
    }

    @Test
    void identityRoleCrudAndValidation() throws Exception {
        String created = mockMvc.perform(post("/api/v1/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"REST_TEST_ROLE\",\"description\":\"create\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readTree(created).get("id").asLong();

        mockMvc.perform(get("/api/v1/roles/" + id)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("REST_TEST_ROLE"));
        mockMvc.perform(patch("/api/v1/roles/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"description\":\"patched\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.description").value("patched"));
        mockMvc.perform(put("/api/v1/roles/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"REST_TEST_ROLE\",\"description\":\"replaced\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.description").value("replaced"));
        mockMvc.perform(delete("/api/v1/roles/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/roles/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void remainingDomainsSupportCrud() throws Exception {
        mockMvc.perform(get("/api/v1/profiles-domain/profiles/1")).andExpect(status().isOk());
        mockMvc.perform(patch("/api/v1/profiles-domain/profiles/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"city\":\"Mumbai\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.city").value("Mumbai"));

        mockMvc.perform(get("/api/v1/skills-domain/skills")).andExpect(status().isOk()).andExpect(jsonPath("$[0].skillName").exists());
        mockMvc.perform(get("/api/v1/learning-domain/courses")).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/projects-domain/projects")).andExpect(status().isOk());

        String uniqueTitle = "API Test Assessment " + java.util.UUID.randomUUID().toString().substring(0, 8);
        String assessment = mockMvc.perform(post("/api/v1/assessments-domain/assessments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"" + uniqueTitle + "\",\"assessmentType\":\"MCQ\",\"totalMarks\":20,\"durationMinutes\":15,\"passingMarks\":10,\"isPublished\":false}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        long assessmentId = objectMapper.readTree(assessment).get("id").asLong();
        mockMvc.perform(patch("/api/v1/assessments-domain/assessments/" + assessmentId)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"difficulty\":\"BEGINNER\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.difficulty").value("BEGINNER"));
        mockMvc.perform(delete("/api/v1/assessments-domain/assessments/" + assessmentId)).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/recruitment-domain/companies")).andExpect(status().isOk()).andExpect(jsonPath("$[0].companyName").exists());
        mockMvc.perform(get("/api/v1/interviews-domain/interviews")).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/notifications-domain/notifications")).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/analytics-domain/dashboards")).andExpect(status().isOk());
    }

    @Test
    void validationAndMissingResources() throws Exception {
        mockMvc.perform(post("/api/v1/assessments-domain/assessments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assessmentType\":\"MCQ\",\"totalMarks\":20,\"durationMinutes\":15,\"passingMarks\":10}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.title").exists());
        mockMvc.perform(get("/api/v1/recruitment-domain/companies/999999")).andExpect(status().isNotFound());
    }
}
