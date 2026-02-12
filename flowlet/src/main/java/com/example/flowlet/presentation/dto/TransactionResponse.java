package com.example.flowlet.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 収支レスポンス
 */
@Getter
@AllArgsConstructor
public class TransactionResponse {

    private UUID transactionId;
    private LocalDate transactionDate;
    private int amount;
    private String transactionType;
    private String memo;

}
