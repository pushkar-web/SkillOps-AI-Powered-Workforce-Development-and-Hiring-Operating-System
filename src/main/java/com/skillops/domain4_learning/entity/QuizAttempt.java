package com.skillops.domain4_learning.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz_attempts", schema = "learning")
public class QuizAttempt {

    public QuizAttempt() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quiz_id", nullable = false)
    @NotNull
    private Long quizId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    @JsonIgnore
    private Quiz quiz;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "obtained_marks", nullable = false)
    private Integer obtainedMarks;

    @Column(name = "passed", nullable = false)
    private Boolean passed;

    @Column(name = "attempt_number", nullable = false)
    private Integer attemptNumber;

    @Column(name = "attempted_at", nullable = false)
    private OffsetDateTime attemptedAt;

}
