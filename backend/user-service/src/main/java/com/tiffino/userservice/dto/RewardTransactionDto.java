package com.tiffino.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardTransactionDto {
    private Long id;
    private Long userId;
    private Integer pointsChange;
    private String transactionType;
    private String source;
    private LocalDateTime transactionTime;
    private LocalDateTime expiryDate;
    private String status;
    private Long relatedOrderId;
}
