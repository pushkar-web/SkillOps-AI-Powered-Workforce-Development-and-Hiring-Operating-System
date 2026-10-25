package com.skillops.domain1_identity_authentication.dto;

public record UserResponse(
        Long id,
        Long tenantId,
        String email,
        String phone,
        String status,
        Boolean emailVerified,
        String lastLogin,
        String createdAt,
        String updatedAt
) {
}
