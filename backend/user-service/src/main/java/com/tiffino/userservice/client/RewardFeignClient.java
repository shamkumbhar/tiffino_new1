package com.tiffino.userservice.client;

import com.tiffino.userservice.dto.RewardTransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "LOYALTY-SERVICE")
public interface RewardFeignClient {
    @PostMapping("/reward/referral/{userId}")
    RewardTransactionDto awardReferralBonus(@PathVariable("userId") Long userId);
}
