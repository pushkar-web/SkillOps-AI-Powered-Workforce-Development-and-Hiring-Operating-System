package com.skillops.domain8_recruitment.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offers", schema = "recruitment")
public class Offer {

    public Offer() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", insertable = false, updatable = false)
    @JsonIgnore
    private JobApplication application;

    @NotBlank
    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "offered_ctc")
    private BigDecimal offeredCtc;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "offer_expiry")
    private LocalDate offerExpiry;

    @NotBlank
    @Column(name = "offer_status", nullable = false)
    private String offerStatus;

}
