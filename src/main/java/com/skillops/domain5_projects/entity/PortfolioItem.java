package com.skillops.domain5_projects.entity;

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
@Table(name = "portfolio_items", schema = "projects")
public class PortfolioItem {

    public PortfolioItem() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    @NotNull
    private Long profileId;

    @Column(name = "project_id", nullable = false)
    @NotNull
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    @JsonIgnore
    private Project project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "thumbnail_uri")
    private String thumbnailUri;

    @Column(name = "live_url")
    private String liveUrl;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "featured", nullable = false)
    private Boolean featured;

    @Column(name = "views", nullable = false)
    private Integer views;

    @Column(name = "likes", nullable = false)
    private Integer likes;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

}
