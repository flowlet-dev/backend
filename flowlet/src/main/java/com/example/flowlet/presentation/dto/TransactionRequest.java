package com.example.flowlet.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 収支登録リクエスト
 */
@Getter
public class TransactionRequest {

    @NotNull
    private LocalDate transactionDate;

    @Min(1)
    private int amount;

    @NotNull
    private String transactionType;

    @Size(max = 255)
    private String memo;

}
