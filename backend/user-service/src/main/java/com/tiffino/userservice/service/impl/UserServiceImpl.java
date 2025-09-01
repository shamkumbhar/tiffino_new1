package com.tiffino.userservice.service.impl;

import com.tiffino.userservice.client.RewardFeignClient;
import com.tiffino.userservice.dto.UserDTO;
import com.tiffino.userservice.entity.User;
import com.tiffino.userservice.exception.ResourceNotFoundException;
import com.tiffino.userservice.repository.UserRepository;
import com.tiffino.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RewardFeignClient rewardClient;

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setDateJoined(LocalDateTime.now());
        user.setIsActive(true);

        // Generate unique referral code for user
        user.setReferralCode("REF" + System.currentTimeMillis() % 1000000);

        // Save new user
        User savedUser = userRepo.save(user);

        // ✅ Handle referral reward
        if (dto.getReferredBy() != null && !dto.getReferredBy().isBlank()) {
            user.setReferredBy(dto.getReferredBy());

            // Find referrer
            User referrer = userRepo.findAll().stream()
                    .filter(u -> dto.getReferredBy().equals(u.getReferralCode()))
                    .findFirst()
                    .orElse(null);

            if (referrer != null) {
                // Trigger reward transaction for the referrer
                rewardClient.awardReferralBonus(referrer.getId());

            }
        }
        return toDTO(savedUser);
    }

//    @Override
//    public UserDTO createUser(UserDTO dto) {
//        User user = new User();
//        BeanUtils.copyProperties(dto, user);
//        user.setDateJoined(LocalDateTime.now());
//        user.setIsActive(true);
//        return toDTO(userRepo.save(user));
//    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BeanUtils.copyProperties(dto, user, "id", "dateJoined");
        return toDTO(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
