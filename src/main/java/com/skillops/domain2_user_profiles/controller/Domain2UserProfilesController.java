package com.skillops.domain2_user_profiles.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skillops.common.CrudFacade;
import com.skillops.config.OpenApiConfig;
import com.skillops.domain2_user_profiles.entity.CareerGoal;
import com.skillops.domain2_user_profiles.entity.Education;
import com.skillops.domain2_user_profiles.entity.Experience;
import com.skillops.domain2_user_profiles.entity.Profile;
import com.skillops.domain2_user_profiles.entity.ProfileCertification;
import com.skillops.domain2_user_profiles.entity.Resume;
import com.skillops.domain2_user_profiles.entity.SkillPassport;
import com.skillops.domain2_user_profiles.repository.CareerGoalRepository;
import com.skillops.domain2_user_profiles.repository.EducationRepository;
import com.skillops.domain2_user_profiles.repository.ExperienceRepository;
import com.skillops.domain2_user_profiles.repository.ProfileCertificationRepository;
import com.skillops.domain2_user_profiles.repository.ProfileRepository;
import com.skillops.domain2_user_profiles.repository.ResumeRepository;
import com.skillops.domain2_user_profiles.repository.SkillPassportRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/profiles-domain")
@Tag(name = OpenApiConfig.DOMAIN_2)
public class Domain2UserProfilesController {

    private final CrudFacade crud;
    private final ProfileRepository profileRepository;
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfileCertificationRepository profileCertificationRepository;
    private final CareerGoalRepository careerGoalRepository;
    private final SkillPassportRepository skillPassportRepository;

    public Domain2UserProfilesController(
            
            CrudFacade crud,
            ProfileRepository profileRepository,
            ResumeRepository resumeRepository,
            EducationRepository educationRepository,
            ExperienceRepository experienceRepository,
            ProfileCertificationRepository profileCertificationRepository,
            CareerGoalRepository careerGoalRepository,
            SkillPassportRepository skillPassportRepository
    ) {
        this.crud = crud;
        this.profileRepository = profileRepository;
        this.resumeRepository = resumeRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.profileCertificationRepository = profileCertificationRepository;
        this.careerGoalRepository = careerGoalRepository;
        this.skillPassportRepository = skillPassportRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_2);
        body.put("profiles", profileRepository.count());
        body.put("resumes", resumeRepository.count());
        body.put("educations", educationRepository.count());
        body.put("experiences", experienceRepository.count());
        body.put("profile-certifications", profileCertificationRepository.count());
        body.put("career-goals", careerGoalRepository.count());
        body.put("skill-passports", skillPassportRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/profiles")
    @Operation(summary = "GET all Profile records")
    public List<Profile> listProfile() {
        return crud.findAll(profileRepository);
    }

    @GetMapping("/profiles/{id}")
    @Operation(summary = "GET Profile by id")
    public Profile getProfile(@PathVariable Long id) {
        return crud.get(profileRepository, id, "Profile");
    }

    @PostMapping("/profiles")
    @Operation(summary = "POST create Profile")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(profileRepository, body));
    }

    @PutMapping("/profiles/{id}")
    @Operation(summary = "PUT replace Profile")
    public Profile replaceProfile(@PathVariable Long id, @Valid @RequestBody Profile body) {
        return crud.replace(profileRepository, id, body, "Profile");
    }

    @PatchMapping("/profiles/{id}")
    @Operation(summary = "PATCH Profile")
    public Profile patchProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(profileRepository, id, updates, "Profile");
    }

    @DeleteMapping("/profiles/{id}")
    @Operation(summary = "DELETE Profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        crud.delete(profileRepository, id, "Profile");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumes")
    @Operation(summary = "GET all Resume records")
    public List<Resume> listResume() {
        return crud.findAll(resumeRepository);
    }

    @GetMapping("/resumes/{id}")
    @Operation(summary = "GET Resume by id")
    public Resume getResume(@PathVariable Long id) {
        return crud.get(resumeRepository, id, "Resume");
    }

    @PostMapping("/resumes")
    @Operation(summary = "POST create Resume")
    public ResponseEntity<Resume> createResume(@Valid @RequestBody Resume body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(resumeRepository, body));
    }

    @PutMapping("/resumes/{id}")
    @Operation(summary = "PUT replace Resume")
    public Resume replaceResume(@PathVariable Long id, @Valid @RequestBody Resume body) {
        return crud.replace(resumeRepository, id, body, "Resume");
    }

    @PatchMapping("/resumes/{id}")
    @Operation(summary = "PATCH Resume")
    public Resume patchResume(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(resumeRepository, id, updates, "Resume");
    }

    @DeleteMapping("/resumes/{id}")
    @Operation(summary = "DELETE Resume")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        crud.delete(resumeRepository, id, "Resume");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/educations")
    @Operation(summary = "GET all Education records")
    public List<Education> listEducation() {
        return crud.findAll(educationRepository);
    }

    @GetMapping("/educations/{id}")
    @Operation(summary = "GET Education by id")
    public Education getEducation(@PathVariable Long id) {
        return crud.get(educationRepository, id, "Education");
    }

    @PostMapping("/educations")
    @Operation(summary = "POST create Education")
    public ResponseEntity<Education> createEducation(@Valid @RequestBody Education body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(educationRepository, body));
    }

    @PutMapping("/educations/{id}")
    @Operation(summary = "PUT replace Education")
    public Education replaceEducation(@PathVariable Long id, @Valid @RequestBody Education body) {
        return crud.replace(educationRepository, id, body, "Education");
    }

    @PatchMapping("/educations/{id}")
    @Operation(summary = "PATCH Education")
    public Education patchEducation(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(educationRepository, id, updates, "Education");
    }

    @DeleteMapping("/educations/{id}")
    @Operation(summary = "DELETE Education")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        crud.delete(educationRepository, id, "Education");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/experiences")
    @Operation(summary = "GET all Experience records")
    public List<Experience> listExperience() {
        return crud.findAll(experienceRepository);
    }

    @GetMapping("/experiences/{id}")
    @Operation(summary = "GET Experience by id")
    public Experience getExperience(@PathVariable Long id) {
        return crud.get(experienceRepository, id, "Experience");
    }

    @PostMapping("/experiences")
    @Operation(summary = "POST create Experience")
    public ResponseEntity<Experience> createExperience(@Valid @RequestBody Experience body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(experienceRepository, body));
    }

    @PutMapping("/experiences/{id}")
    @Operation(summary = "PUT replace Experience")
    public Experience replaceExperience(@PathVariable Long id, @Valid @RequestBody Experience body) {
        return crud.replace(experienceRepository, id, body, "Experience");
    }

    @PatchMapping("/experiences/{id}")
    @Operation(summary = "PATCH Experience")
    public Experience patchExperience(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(experienceRepository, id, updates, "Experience");
    }

    @DeleteMapping("/experiences/{id}")
    @Operation(summary = "DELETE Experience")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        crud.delete(experienceRepository, id, "Experience");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile-certifications")
    @Operation(summary = "GET all ProfileCertification records")
    public List<ProfileCertification> listProfileCertification() {
        return crud.findAll(profileCertificationRepository);
    }

    @GetMapping("/profile-certifications/{id}")
    @Operation(summary = "GET ProfileCertification by id")
    public ProfileCertification getProfileCertification(@PathVariable Long id) {
        return crud.get(profileCertificationRepository, id, "ProfileCertification");
    }

    @PostMapping("/profile-certifications")
    @Operation(summary = "POST create ProfileCertification")
    public ResponseEntity<ProfileCertification> createProfileCertification(@Valid @RequestBody ProfileCertification body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(profileCertificationRepository, body));
    }

    @PutMapping("/profile-certifications/{id}")
    @Operation(summary = "PUT replace ProfileCertification")
    public ProfileCertification replaceProfileCertification(@PathVariable Long id, @Valid @RequestBody ProfileCertification body) {
        return crud.replace(profileCertificationRepository, id, body, "ProfileCertification");
    }

    @PatchMapping("/profile-certifications/{id}")
    @Operation(summary = "PATCH ProfileCertification")
    public ProfileCertification patchProfileCertification(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(profileCertificationRepository, id, updates, "ProfileCertification");
    }

    @DeleteMapping("/profile-certifications/{id}")
    @Operation(summary = "DELETE ProfileCertification")
    public ResponseEntity<Void> deleteProfileCertification(@PathVariable Long id) {
        crud.delete(profileCertificationRepository, id, "ProfileCertification");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/career-goals")
    @Operation(summary = "GET all CareerGoal records")
    public List<CareerGoal> listCareerGoal() {
        return crud.findAll(careerGoalRepository);
    }

    @GetMapping("/career-goals/{id}")
    @Operation(summary = "GET CareerGoal by id")
    public CareerGoal getCareerGoal(@PathVariable Long id) {
        return crud.get(careerGoalRepository, id, "CareerGoal");
    }

    @PostMapping("/career-goals")
    @Operation(summary = "POST create CareerGoal")
    public ResponseEntity<CareerGoal> createCareerGoal(@Valid @RequestBody CareerGoal body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(careerGoalRepository, body));
    }

    @PutMapping("/career-goals/{id}")
    @Operation(summary = "PUT replace CareerGoal")
    public CareerGoal replaceCareerGoal(@PathVariable Long id, @Valid @RequestBody CareerGoal body) {
        return crud.replace(careerGoalRepository, id, body, "CareerGoal");
    }

    @PatchMapping("/career-goals/{id}")
    @Operation(summary = "PATCH CareerGoal")
    public CareerGoal patchCareerGoal(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(careerGoalRepository, id, updates, "CareerGoal");
    }

    @DeleteMapping("/career-goals/{id}")
    @Operation(summary = "DELETE CareerGoal")
    public ResponseEntity<Void> deleteCareerGoal(@PathVariable Long id) {
        crud.delete(careerGoalRepository, id, "CareerGoal");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skill-passports")
    @Operation(summary = "GET all SkillPassport records")
    public List<SkillPassport> listSkillPassport() {
        return crud.findAll(skillPassportRepository);
    }

    @GetMapping("/skill-passports/{id}")
    @Operation(summary = "GET SkillPassport by id")
    public SkillPassport getSkillPassport(@PathVariable Long id) {
        return crud.get(skillPassportRepository, id, "SkillPassport");
    }

    @PostMapping("/skill-passports")
    @Operation(summary = "POST create SkillPassport")
    public ResponseEntity<SkillPassport> createSkillPassport(@Valid @RequestBody SkillPassport body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillPassportRepository, body));
    }

    @PutMapping("/skill-passports/{id}")
    @Operation(summary = "PUT replace SkillPassport")
    public SkillPassport replaceSkillPassport(@PathVariable Long id, @Valid @RequestBody SkillPassport body) {
        return crud.replace(skillPassportRepository, id, body, "SkillPassport");
    }

    @PatchMapping("/skill-passports/{id}")
    @Operation(summary = "PATCH SkillPassport")
    public SkillPassport patchSkillPassport(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillPassportRepository, id, updates, "SkillPassport");
    }

    @DeleteMapping("/skill-passports/{id}")
    @Operation(summary = "DELETE SkillPassport")
    public ResponseEntity<Void> deleteSkillPassport(@PathVariable Long id) {
        crud.delete(skillPassportRepository, id, "SkillPassport");
        return ResponseEntity.noContent().build();
    }

}
