package com.skillops.domain1_identity_authentication.controller;

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
import com.skillops.domain1_identity_authentication.entity.AuditLog;
import com.skillops.domain1_identity_authentication.entity.LoginHistory;
import com.skillops.domain1_identity_authentication.entity.Permission;
import com.skillops.domain1_identity_authentication.entity.RefreshToken;
import com.skillops.domain1_identity_authentication.entity.Role;
import com.skillops.domain1_identity_authentication.entity.Session;
import com.skillops.domain1_identity_authentication.entity.User;
import com.skillops.domain1_identity_authentication.entity.UserRole;
import com.skillops.domain1_identity_authentication.repository.AuditLogRepository;
import com.skillops.domain1_identity_authentication.repository.LoginHistoryRepository;
import com.skillops.domain1_identity_authentication.repository.PermissionRepository;
import com.skillops.domain1_identity_authentication.repository.RefreshTokenRepository;
import com.skillops.domain1_identity_authentication.repository.RoleRepository;
import com.skillops.domain1_identity_authentication.repository.SessionRepository;
import com.skillops.domain1_identity_authentication.repository.UserRepository;
import com.skillops.domain1_identity_authentication.repository.UserRoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = OpenApiConfig.DOMAIN_1, description = "Users, roles, permissions, sessions, tokens, login history, and audit logs")
public class IdentityController {

    private final CrudFacade crud;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRoleRepository userRoleRepository;
    private final SessionRepository sessionRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final LoginHistoryRepository loginHistoryRepository;
    private final AuditLogRepository auditLogRepository;

    public IdentityController(
            CrudFacade crud,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            UserRoleRepository userRoleRepository,
            SessionRepository sessionRepository,
            RefreshTokenRepository refreshTokenRepository,
            LoginHistoryRepository loginHistoryRepository,
            AuditLogRepository auditLogRepository
    ) {
        this.crud = crud;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRoleRepository = userRoleRepository;
        this.sessionRepository = sessionRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.loginHistoryRepository = loginHistoryRepository;
        this.auditLogRepository = auditLogRepository;
    }

    @GetMapping("/health")
    @Operation(summary = "GET health / row counts")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("domain", OpenApiConfig.DOMAIN_1);
        body.put("users", userRepository.count());
        body.put("roles", roleRepository.count());
        body.put("permissions", permissionRepository.count());
        body.put("userRoles", userRoleRepository.count());
        body.put("sessions", sessionRepository.count());
        body.put("refreshTokens", refreshTokenRepository.count());
        body.put("loginHistory", loginHistoryRepository.count());
        body.put("auditLogs", auditLogRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/users")
    @Operation(summary = "GET all users")
    public List<User> listUsers() {
        return crud.findAll(userRepository);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "GET user by id")
    public User getUser(@PathVariable Long id) {
        return crud.get(userRepository, id, "User");
    }

    @PostMapping("/users")
    @Operation(summary = "POST create user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(userRepository, body));
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "PUT replace user")
    public User replaceUser(@PathVariable Long id, @Valid @RequestBody User body) {
        return crud.replace(userRepository, id, body, "User");
    }

    @PatchMapping("/users/{id}")
    @Operation(summary = "PATCH user")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(userRepository, id, updates, "User");
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "DELETE user")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        crud.delete(userRepository, id, "User");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles")
    @Operation(summary = "GET all roles")
    public List<Role> listRoles() {
        return crud.findAll(roleRepository);
    }

    @GetMapping("/roles/{id}")
    @Operation(summary = "GET role by id")
    public Role getRole(@PathVariable Long id) {
        return crud.get(roleRepository, id, "Role");
    }

    @PostMapping("/roles")
    @Operation(summary = "POST create role")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(roleRepository, body));
    }

    @PutMapping("/roles/{id}")
    @Operation(summary = "PUT replace role")
    public Role replaceRole(@PathVariable Long id, @Valid @RequestBody Role body) {
        return crud.replace(roleRepository, id, body, "Role");
    }

    @PatchMapping("/roles/{id}")
    @Operation(summary = "PATCH role")
    public Role patchRole(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(roleRepository, id, updates, "Role");
    }

    @DeleteMapping("/roles/{id}")
    @Operation(summary = "DELETE role")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        crud.delete(roleRepository, id, "Role");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/permissions")
    @Operation(summary = "GET all permissions")
    public List<Permission> listPermissions() {
        return crud.findAll(permissionRepository);
    }

    @GetMapping("/permissions/{id}")
    @Operation(summary = "GET permission by id")
    public Permission getPermission(@PathVariable Long id) {
        return crud.get(permissionRepository, id, "Permission");
    }

    @PostMapping("/permissions")
    @Operation(summary = "POST create permission")
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody Permission body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(permissionRepository, body));
    }

    @PutMapping("/permissions/{id}")
    @Operation(summary = "PUT replace permission")
    public Permission replacePermission(@PathVariable Long id, @Valid @RequestBody Permission body) {
        return crud.replace(permissionRepository, id, body, "Permission");
    }

    @PatchMapping("/permissions/{id}")
    @Operation(summary = "PATCH permission")
    public Permission patchPermission(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(permissionRepository, id, updates, "Permission");
    }

    @DeleteMapping("/permissions/{id}")
    @Operation(summary = "DELETE permission")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        crud.delete(permissionRepository, id, "Permission");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-roles")
    @Operation(summary = "GET all user-role assignments")
    public List<UserRole> listUserRoles() {
        return crud.findAll(userRoleRepository);
    }

    @GetMapping("/user-roles/{id}")
    @Operation(summary = "GET user-role by id")
    public UserRole getUserRole(@PathVariable Long id) {
        return crud.get(userRoleRepository, id, "UserRole");
    }

    @PostMapping("/user-roles")
    @Operation(summary = "POST create user-role")
    public ResponseEntity<UserRole> createUserRole(@Valid @RequestBody UserRole body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(userRoleRepository, body));
    }

    @PutMapping("/user-roles/{id}")
    @Operation(summary = "PUT replace user-role")
    public UserRole replaceUserRole(@PathVariable Long id, @Valid @RequestBody UserRole body) {
        return crud.replace(userRoleRepository, id, body, "UserRole");
    }

    @PatchMapping("/user-roles/{id}")
    @Operation(summary = "PATCH user-role")
    public UserRole patchUserRole(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(userRoleRepository, id, updates, "UserRole");
    }

    @DeleteMapping("/user-roles/{id}")
    @Operation(summary = "DELETE user-role")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        crud.delete(userRoleRepository, id, "UserRole");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sessions")
    @Operation(summary = "GET all sessions")
    public List<Session> listSessions() {
        return crud.findAll(sessionRepository);
    }

    @GetMapping("/sessions/{id}")
    @Operation(summary = "GET session by id")
    public Session getSession(@PathVariable Long id) {
        return crud.get(sessionRepository, id, "Session");
    }

    @PostMapping("/sessions")
    @Operation(summary = "POST create session")
    public ResponseEntity<Session> createSession(@Valid @RequestBody Session body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(sessionRepository, body));
    }

    @PutMapping("/sessions/{id}")
    @Operation(summary = "PUT replace session")
    public Session replaceSession(@PathVariable Long id, @Valid @RequestBody Session body) {
        return crud.replace(sessionRepository, id, body, "Session");
    }

    @PatchMapping("/sessions/{id}")
    @Operation(summary = "PATCH session")
    public Session patchSession(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(sessionRepository, id, updates, "Session");
    }

    @DeleteMapping("/sessions/{id}")
    @Operation(summary = "DELETE session")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        crud.delete(sessionRepository, id, "Session");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/refresh-tokens")
    @Operation(summary = "GET all refresh tokens")
    public List<RefreshToken> listRefreshTokens() {
        return crud.findAll(refreshTokenRepository);
    }

    @GetMapping("/refresh-tokens/{id}")
    @Operation(summary = "GET refresh token by id")
    public RefreshToken getRefreshToken(@PathVariable Long id) {
        return crud.get(refreshTokenRepository, id, "RefreshToken");
    }

    @PostMapping("/refresh-tokens")
    @Operation(summary = "POST create refresh token")
    public ResponseEntity<RefreshToken> createRefreshToken(@Valid @RequestBody RefreshToken body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(refreshTokenRepository, body));
    }

    @PutMapping("/refresh-tokens/{id}")
    @Operation(summary = "PUT replace refresh token")
    public RefreshToken replaceRefreshToken(@PathVariable Long id, @Valid @RequestBody RefreshToken body) {
        return crud.replace(refreshTokenRepository, id, body, "RefreshToken");
    }

    @PatchMapping("/refresh-tokens/{id}")
    @Operation(summary = "PATCH refresh token")
    public RefreshToken patchRefreshToken(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(refreshTokenRepository, id, updates, "RefreshToken");
    }

    @DeleteMapping("/refresh-tokens/{id}")
    @Operation(summary = "DELETE refresh token")
    public ResponseEntity<Void> deleteRefreshToken(@PathVariable Long id) {
        crud.delete(refreshTokenRepository, id, "RefreshToken");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login-history")
    @Operation(summary = "GET all login history")
    public List<LoginHistory> listLoginHistory() {
        return crud.findAll(loginHistoryRepository);
    }

    @GetMapping("/login-history/{id}")
    @Operation(summary = "GET login history by id")
    public LoginHistory getLoginHistory(@PathVariable Long id) {
        return crud.get(loginHistoryRepository, id, "LoginHistory");
    }

    @PostMapping("/login-history")
    @Operation(summary = "POST create login history")
    public ResponseEntity<LoginHistory> createLoginHistory(@Valid @RequestBody LoginHistory body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(loginHistoryRepository, body));
    }

    @PutMapping("/login-history/{id}")
    @Operation(summary = "PUT replace login history")
    public LoginHistory replaceLoginHistory(@PathVariable Long id, @Valid @RequestBody LoginHistory body) {
        return crud.replace(loginHistoryRepository, id, body, "LoginHistory");
    }

    @PatchMapping("/login-history/{id}")
    @Operation(summary = "PATCH login history")
    public LoginHistory patchLoginHistory(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(loginHistoryRepository, id, updates, "LoginHistory");
    }

    @DeleteMapping("/login-history/{id}")
    @Operation(summary = "DELETE login history")
    public ResponseEntity<Void> deleteLoginHistory(@PathVariable Long id) {
        crud.delete(loginHistoryRepository, id, "LoginHistory");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/audit-logs")
    @Operation(summary = "GET all audit logs")
    public List<AuditLog> listAuditLogs() {
        return crud.findAll(auditLogRepository);
    }

    @GetMapping("/audit-logs/{id}")
    @Operation(summary = "GET audit log by id")
    public AuditLog getAuditLog(@PathVariable Long id) {
        return crud.get(auditLogRepository, id, "AuditLog");
    }

    @PostMapping("/audit-logs")
    @Operation(summary = "POST create audit log")
    public ResponseEntity<AuditLog> createAuditLog(@Valid @RequestBody AuditLog body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(auditLogRepository, body));
    }

    @PutMapping("/audit-logs/{id}")
    @Operation(summary = "PUT replace audit log")
    public AuditLog replaceAuditLog(@PathVariable Long id, @Valid @RequestBody AuditLog body) {
        return crud.replace(auditLogRepository, id, body, "AuditLog");
    }

    @PatchMapping("/audit-logs/{id}")
    @Operation(summary = "PATCH audit log")
    public AuditLog patchAuditLog(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(auditLogRepository, id, updates, "AuditLog");
    }

    @DeleteMapping("/audit-logs/{id}")
    @Operation(summary = "DELETE audit log")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        crud.delete(auditLogRepository, id, "AuditLog");
        return ResponseEntity.noContent().build();
    }
}
