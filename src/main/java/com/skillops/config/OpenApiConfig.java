package com.skillops.config;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

    public static final String DOMAIN_1 = "Domain 1: Identity & Authentication";
    public static final String DOMAIN_2 = "Domain 2: User Profiles";
    public static final String DOMAIN_3 = "Domain 3: Skills";
    public static final String DOMAIN_4 = "Domain 4: Learning";
    public static final String DOMAIN_5 = "Domain 5: Projects";
    public static final String DOMAIN_6 = "Domain 6: Assessments";
    public static final String DOMAIN_7 = "Domain 7: Interviews";
    public static final String DOMAIN_8 = "Domain 8: Recruitment";
    public static final String DOMAIN_9 = "Domain 9: Notifications";
    public static final String DOMAIN_10 = "Domain 10: Analytics";

    private static final Pattern DOMAIN_NUMBER = Pattern.compile("^Domain (\\d+)");

    @Bean
    public OpenAPI skillOpsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SkillOps API")
                        .description("AI-Powered Workforce Development and Hiring Operating System. "
                                + "Each domain exposes GET, POST, PUT, PATCH, and DELETE endpoints.")
                        .version("v1")
                        .contact(new Contact().name("SkillOps").email("support@skillops.io")))
                .tags(List.of(
                        new Tag().name(DOMAIN_1).description("Users, roles, permissions, sessions, tokens, login history, and audit logs"),
                        new Tag().name(DOMAIN_2).description("Profiles, resumes, education, experience, certifications, career goals, and skill passports"),
                        new Tag().name(DOMAIN_3).description("Skill categories, skills, assessments, competencies, gaps, and recommendations"),
                        new Tag().name(DOMAIN_4).description("Learning paths, courses, modules, lessons, assignments, quizzes, and progress"),
                        new Tag().name(DOMAIN_5).description("Projects, milestones, submissions, reviews, and portfolio items"),
                        new Tag().name(DOMAIN_6).description("Assessments, questions, coding challenges, attempts, certificates, and feedback"),
                        new Tag().name(DOMAIN_7).description("Interviews, rounds, feedback, scorecards, and AI reports"),
                        new Tag().name(DOMAIN_8).description("Companies, jobs, applications, matches, offers, and hiring status"),
                        new Tag().name(DOMAIN_9).description("Notification preferences and notifications"),
                        new Tag().name(DOMAIN_10).description("Dashboards and user analytics")
                ));
    }

    @Bean
    public OpenApiCustomizer domainTagOrderCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.setTags(openApi.getTags().stream()
                        .sorted(Comparator.comparingInt(OpenApiConfig::domainNumber).thenComparing(Tag::getName))
                        .toList());
            }
        };
    }

    private static int domainNumber(Tag tag) {
        Matcher matcher = DOMAIN_NUMBER.matcher(tag.getName() == null ? "" : tag.getName());
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : 999;
    }
}
