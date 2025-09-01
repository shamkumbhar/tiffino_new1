package com.tiffino.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private LocalDateTime dateJoined;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private String profileImageUrl;
    private Set<String> dietaryPreferences;
    private Set<String> allergenPreferences;
    private String referralCode;
    private String referredBy;

}
