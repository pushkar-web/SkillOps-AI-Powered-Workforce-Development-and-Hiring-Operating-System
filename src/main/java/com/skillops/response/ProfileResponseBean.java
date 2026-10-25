package com.skillops.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseBean {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String bio;
    private String profilePicture;
    private String city;
    private String state;
    private String country;
    private String linkedinUrl;
    private String githubUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
