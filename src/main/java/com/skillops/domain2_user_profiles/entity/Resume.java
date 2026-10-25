package com.skillops.domain2_user_profiles.entity;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resumes", schema = "user_profiles")
public class Resume {

    public Resume() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    @NotNull
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @JsonIgnore
    private Profile profile;

    @Column(name = "file_name", nullable = false)
    @NotBlank
    private String fileName;

    @Column(name = "file_url", nullable = false)
    @NotBlank
    private String fileUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "parsed_text")
    private String parsedText;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @Column(name = "uploaded_at", nullable = false)
    private OffsetDateTime uploadedAt;

}
