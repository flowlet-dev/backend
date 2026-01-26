package com.example.flowlet.domain.models.entities;

import java.math.BigDecimal;

/**
 * アカウントエンティティ
 */
public record Account(String accountId, String userId, String accountName, BigDecimal initialBalance,
                      BigDecimal currentBalance, Boolean isSavingsAccount) {
}
