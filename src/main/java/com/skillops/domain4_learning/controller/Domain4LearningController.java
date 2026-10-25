package com.skillops.domain4_learning.controller;

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
import com.skillops.domain4_learning.entity.Assignment;
import com.skillops.domain4_learning.entity.Course;
import com.skillops.domain4_learning.entity.CourseModule;
import com.skillops.domain4_learning.entity.Enrollment;
import com.skillops.domain4_learning.entity.LearningPath;
import com.skillops.domain4_learning.entity.LearningProgress;
import com.skillops.domain4_learning.entity.Lesson;
import com.skillops.domain4_learning.entity.Quiz;
import com.skillops.domain4_learning.entity.QuizAttempt;
import com.skillops.domain4_learning.entity.Submission;
import com.skillops.domain4_learning.repository.AssignmentRepository;
import com.skillops.domain4_learning.repository.CourseModuleRepository;
import com.skillops.domain4_learning.repository.CourseRepository;
import com.skillops.domain4_learning.repository.EnrollmentRepository;
import com.skillops.domain4_learning.repository.LearningPathRepository;
import com.skillops.domain4_learning.repository.LearningProgressRepository;
import com.skillops.domain4_learning.repository.LessonRepository;
import com.skillops.domain4_learning.repository.QuizAttemptRepository;
import com.skillops.domain4_learning.repository.QuizRepository;
import com.skillops.domain4_learning.repository.SubmissionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/learning-domain")
@Tag(name = OpenApiConfig.DOMAIN_4)
public class Domain4LearningController {

    private final CrudFacade crud;
    private final LearningPathRepository learningPathRepository;
    private final CourseRepository courseRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LearningProgressRepository learningProgressRepository;

    public Domain4LearningController(
            
            CrudFacade crud,
            LearningPathRepository learningPathRepository,
            CourseRepository courseRepository,
            CourseModuleRepository courseModuleRepository,
            LessonRepository lessonRepository,
            AssignmentRepository assignmentRepository,
            SubmissionRepository submissionRepository,
            QuizRepository quizRepository,
            QuizAttemptRepository quizAttemptRepository,
            EnrollmentRepository enrollmentRepository,
            LearningProgressRepository learningProgressRepository
    ) {
        this.crud = crud;
        this.learningPathRepository = learningPathRepository;
        this.courseRepository = courseRepository;
        this.courseModuleRepository = courseModuleRepository;
        this.lessonRepository = lessonRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.quizRepository = quizRepository;
        this.quizAttemptRepository = quizAttemptRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.learningProgressRepository = learningProgressRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_4);
        body.put("learning-paths", learningPathRepository.count());
        body.put("courses", courseRepository.count());
        body.put("course-modules", courseModuleRepository.count());
        body.put("lessons", lessonRepository.count());
        body.put("assignments", assignmentRepository.count());
        body.put("submissions", submissionRepository.count());
        body.put("quizzes", quizRepository.count());
        body.put("quiz-attempts", quizAttemptRepository.count());
        body.put("enrollments", enrollmentRepository.count());
        body.put("learning-progress", learningProgressRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/learning-paths")
    @Operation(summary = "GET all LearningPath records")
    public List<LearningPath> listLearningPath() {
        return crud.findAll(learningPathRepository);
    }

    @GetMapping("/learning-paths/{id}")
    @Operation(summary = "GET LearningPath by id")
    public LearningPath getLearningPath(@PathVariable Long id) {
        return crud.get(learningPathRepository, id, "LearningPath");
    }

    @PostMapping("/learning-paths")
    @Operation(summary = "POST create LearningPath")
    public ResponseEntity<LearningPath> createLearningPath(@Valid @RequestBody LearningPath body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(learningPathRepository, body));
    }

    @PutMapping("/learning-paths/{id}")
    @Operation(summary = "PUT replace LearningPath")
    public LearningPath replaceLearningPath(@PathVariable Long id, @Valid @RequestBody LearningPath body) {
        return crud.replace(learningPathRepository, id, body, "LearningPath");
    }

    @PatchMapping("/learning-paths/{id}")
    @Operation(summary = "PATCH LearningPath")
    public LearningPath patchLearningPath(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(learningPathRepository, id, updates, "LearningPath");
    }

    @DeleteMapping("/learning-paths/{id}")
    @Operation(summary = "DELETE LearningPath")
    public ResponseEntity<Void> deleteLearningPath(@PathVariable Long id) {
        crud.delete(learningPathRepository, id, "LearningPath");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses")
    @Operation(summary = "GET all Course records")
    public List<Course> listCourse() {
        return crud.findAll(courseRepository);
    }

    @GetMapping("/courses/{id}")
    @Operation(summary = "GET Course by id")
    public Course getCourse(@PathVariable Long id) {
        return crud.get(courseRepository, id, "Course");
    }

    @PostMapping("/courses")
    @Operation(summary = "POST create Course")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(courseRepository, body));
    }

    @PutMapping("/courses/{id}")
    @Operation(summary = "PUT replace Course")
    public Course replaceCourse(@PathVariable Long id, @Valid @RequestBody Course body) {
        return crud.replace(courseRepository, id, body, "Course");
    }

    @PatchMapping("/courses/{id}")
    @Operation(summary = "PATCH Course")
    public Course patchCourse(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(courseRepository, id, updates, "Course");
    }

    @DeleteMapping("/courses/{id}")
    @Operation(summary = "DELETE Course")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        crud.delete(courseRepository, id, "Course");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course-modules")
    @Operation(summary = "GET all CourseModule records")
    public List<CourseModule> listCourseModule() {
        return crud.findAll(courseModuleRepository);
    }

    @GetMapping("/course-modules/{id}")
    @Operation(summary = "GET CourseModule by id")
    public CourseModule getCourseModule(@PathVariable Long id) {
        return crud.get(courseModuleRepository, id, "CourseModule");
    }

    @PostMapping("/course-modules")
    @Operation(summary = "POST create CourseModule")
    public ResponseEntity<CourseModule> createCourseModule(@Valid @RequestBody CourseModule body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(courseModuleRepository, body));
    }

    @PutMapping("/course-modules/{id}")
    @Operation(summary = "PUT replace CourseModule")
    public CourseModule replaceCourseModule(@PathVariable Long id, @Valid @RequestBody CourseModule body) {
        return crud.replace(courseModuleRepository, id, body, "CourseModule");
    }

    @PatchMapping("/course-modules/{id}")
    @Operation(summary = "PATCH CourseModule")
    public CourseModule patchCourseModule(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(courseModuleRepository, id, updates, "CourseModule");
    }

    @DeleteMapping("/course-modules/{id}")
    @Operation(summary = "DELETE CourseModule")
    public ResponseEntity<Void> deleteCourseModule(@PathVariable Long id) {
        crud.delete(courseModuleRepository, id, "CourseModule");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lessons")
    @Operation(summary = "GET all Lesson records")
    public List<Lesson> listLesson() {
        return crud.findAll(lessonRepository);
    }

    @GetMapping("/lessons/{id}")
    @Operation(summary = "GET Lesson by id")
    public Lesson getLesson(@PathVariable Long id) {
        return crud.get(lessonRepository, id, "Lesson");
    }

    @PostMapping("/lessons")
    @Operation(summary = "POST create Lesson")
    public ResponseEntity<Lesson> createLesson(@Valid @RequestBody Lesson body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(lessonRepository, body));
    }

    @PutMapping("/lessons/{id}")
    @Operation(summary = "PUT replace Lesson")
    public Lesson replaceLesson(@PathVariable Long id, @Valid @RequestBody Lesson body) {
        return crud.replace(lessonRepository, id, body, "Lesson");
    }

    @PatchMapping("/lessons/{id}")
    @Operation(summary = "PATCH Lesson")
    public Lesson patchLesson(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(lessonRepository, id, updates, "Lesson");
    }

    @DeleteMapping("/lessons/{id}")
    @Operation(summary = "DELETE Lesson")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        crud.delete(lessonRepository, id, "Lesson");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assignments")
    @Operation(summary = "GET all Assignment records")
    public List<Assignment> listAssignment() {
        return crud.findAll(assignmentRepository);
    }

    @GetMapping("/assignments/{id}")
    @Operation(summary = "GET Assignment by id")
    public Assignment getAssignment(@PathVariable Long id) {
        return crud.get(assignmentRepository, id, "Assignment");
    }

    @PostMapping("/assignments")
    @Operation(summary = "POST create Assignment")
    public ResponseEntity<Assignment> createAssignment(@Valid @RequestBody Assignment body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assignmentRepository, body));
    }

    @PutMapping("/assignments/{id}")
    @Operation(summary = "PUT replace Assignment")
    public Assignment replaceAssignment(@PathVariable Long id, @Valid @RequestBody Assignment body) {
        return crud.replace(assignmentRepository, id, body, "Assignment");
    }

    @PatchMapping("/assignments/{id}")
    @Operation(summary = "PATCH Assignment")
    public Assignment patchAssignment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assignmentRepository, id, updates, "Assignment");
    }

    @DeleteMapping("/assignments/{id}")
    @Operation(summary = "DELETE Assignment")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        crud.delete(assignmentRepository, id, "Assignment");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/submissions")
    @Operation(summary = "GET all Submission records")
    public List<Submission> listSubmission() {
        return crud.findAll(submissionRepository);
    }

    @GetMapping("/submissions/{id}")
    @Operation(summary = "GET Submission by id")
    public Submission getSubmission(@PathVariable Long id) {
        return crud.get(submissionRepository, id, "Submission");
    }

    @PostMapping("/submissions")
    @Operation(summary = "POST create Submission")
    public ResponseEntity<Submission> createSubmission(@Valid @RequestBody Submission body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(submissionRepository, body));
    }

    @PutMapping("/submissions/{id}")
    @Operation(summary = "PUT replace Submission")
    public Submission replaceSubmission(@PathVariable Long id, @Valid @RequestBody Submission body) {
        return crud.replace(submissionRepository, id, body, "Submission");
    }

    @PatchMapping("/submissions/{id}")
    @Operation(summary = "PATCH Submission")
    public Submission patchSubmission(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(submissionRepository, id, updates, "Submission");
    }

    @DeleteMapping("/submissions/{id}")
    @Operation(summary = "DELETE Submission")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        crud.delete(submissionRepository, id, "Submission");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quizzes")
    @Operation(summary = "GET all Quiz records")
    public List<Quiz> listQuiz() {
        return crud.findAll(quizRepository);
    }

    @GetMapping("/quizzes/{id}")
    @Operation(summary = "GET Quiz by id")
    public Quiz getQuiz(@PathVariable Long id) {
        return crud.get(quizRepository, id, "Quiz");
    }

    @PostMapping("/quizzes")
    @Operation(summary = "POST create Quiz")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(quizRepository, body));
    }

    @PutMapping("/quizzes/{id}")
    @Operation(summary = "PUT replace Quiz")
    public Quiz replaceQuiz(@PathVariable Long id, @Valid @RequestBody Quiz body) {
        return crud.replace(quizRepository, id, body, "Quiz");
    }

    @PatchMapping("/quizzes/{id}")
    @Operation(summary = "PATCH Quiz")
    public Quiz patchQuiz(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(quizRepository, id, updates, "Quiz");
    }

    @DeleteMapping("/quizzes/{id}")
    @Operation(summary = "DELETE Quiz")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        crud.delete(quizRepository, id, "Quiz");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quiz-attempts")
    @Operation(summary = "GET all QuizAttempt records")
    public List<QuizAttempt> listQuizAttempt() {
        return crud.findAll(quizAttemptRepository);
    }

    @GetMapping("/quiz-attempts/{id}")
    @Operation(summary = "GET QuizAttempt by id")
    public QuizAttempt getQuizAttempt(@PathVariable Long id) {
        return crud.get(quizAttemptRepository, id, "QuizAttempt");
    }

    @PostMapping("/quiz-attempts")
    @Operation(summary = "POST create QuizAttempt")
    public ResponseEntity<QuizAttempt> createQuizAttempt(@Valid @RequestBody QuizAttempt body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(quizAttemptRepository, body));
    }

    @PutMapping("/quiz-attempts/{id}")
    @Operation(summary = "PUT replace QuizAttempt")
    public QuizAttempt replaceQuizAttempt(@PathVariable Long id, @Valid @RequestBody QuizAttempt body) {
        return crud.replace(quizAttemptRepository, id, body, "QuizAttempt");
    }

    @PatchMapping("/quiz-attempts/{id}")
    @Operation(summary = "PATCH QuizAttempt")
    public QuizAttempt patchQuizAttempt(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(quizAttemptRepository, id, updates, "QuizAttempt");
    }

    @DeleteMapping("/quiz-attempts/{id}")
    @Operation(summary = "DELETE QuizAttempt")
    public ResponseEntity<Void> deleteQuizAttempt(@PathVariable Long id) {
        crud.delete(quizAttemptRepository, id, "QuizAttempt");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enrollments")
    @Operation(summary = "GET all Enrollment records")
    public List<Enrollment> listEnrollment() {
        return crud.findAll(enrollmentRepository);
    }

    @GetMapping("/enrollments/{id}")
    @Operation(summary = "GET Enrollment by id")
    public Enrollment getEnrollment(@PathVariable Long id) {
        return crud.get(enrollmentRepository, id, "Enrollment");
    }

    @PostMapping("/enrollments")
    @Operation(summary = "POST create Enrollment")
    public ResponseEntity<Enrollment> createEnrollment(@Valid @RequestBody Enrollment body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(enrollmentRepository, body));
    }

    @PutMapping("/enrollments/{id}")
    @Operation(summary = "PUT replace Enrollment")
    public Enrollment replaceEnrollment(@PathVariable Long id, @Valid @RequestBody Enrollment body) {
        return crud.replace(enrollmentRepository, id, body, "Enrollment");
    }

    @PatchMapping("/enrollments/{id}")
    @Operation(summary = "PATCH Enrollment")
    public Enrollment patchEnrollment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(enrollmentRepository, id, updates, "Enrollment");
    }

    @DeleteMapping("/enrollments/{id}")
    @Operation(summary = "DELETE Enrollment")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        crud.delete(enrollmentRepository, id, "Enrollment");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/learning-progress")
    @Operation(summary = "GET all LearningProgress records")
    public List<LearningProgress> listLearningProgress() {
        return crud.findAll(learningProgressRepository);
    }

    @GetMapping("/learning-progress/{id}")
    @Operation(summary = "GET LearningProgress by id")
    public LearningProgress getLearningProgress(@PathVariable Long id) {
        return crud.get(learningProgressRepository, id, "LearningProgress");
    }

    @PostMapping("/learning-progress")
    @Operation(summary = "POST create LearningProgress")
    public ResponseEntity<LearningProgress> createLearningProgress(@Valid @RequestBody LearningProgress body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(learningProgressRepository, body));
    }

    @PutMapping("/learning-progress/{id}")
    @Operation(summary = "PUT replace LearningProgress")
    public LearningProgress replaceLearningProgress(@PathVariable Long id, @Valid @RequestBody LearningProgress body) {
        return crud.replace(learningProgressRepository, id, body, "LearningProgress");
    }

    @PatchMapping("/learning-progress/{id}")
    @Operation(summary = "PATCH LearningProgress")
    public LearningProgress patchLearningProgress(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(learningProgressRepository, id, updates, "LearningProgress");
    }

    @DeleteMapping("/learning-progress/{id}")
    @Operation(summary = "DELETE LearningProgress")
    public ResponseEntity<Void> deleteLearningProgress(@PathVariable Long id) {
        crud.delete(learningProgressRepository, id, "LearningProgress");
        return ResponseEntity.noContent().build();
    }

}
