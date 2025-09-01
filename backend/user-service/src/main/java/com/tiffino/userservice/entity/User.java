package com.tiffino.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"phone_number"}),
        @UniqueConstraint(columnNames = {"referral_code"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(name = "password_hash")
    private String passwordHash;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;


    @Column(name = "date_joined")
    private LocalDateTime dateJoined;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @ElementCollection
    @CollectionTable(name = "user_dietary_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "dietary_preference")
    private Set<String> dietaryPreferences;

    @ElementCollection
    @CollectionTable(name = "user_allergen_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "allergen_preference")
    private Set<String> allergenPreferences;

    @Column(name = "referral_code", unique = true)
    private String referralCode;

    @Column(name = "referred_by")
    private String referredBy;
}
