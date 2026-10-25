package com.skillops.domain1_identity_authentication.dto;

public record UserRoleResponse(Long id, Long userId, String email, Long roleId, String roleName, String assignedAt) {
}
