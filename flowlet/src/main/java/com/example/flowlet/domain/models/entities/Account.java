package com.example.flowlet.domain.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 口座エンティティ
 */
@Data
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Account {

    String accountId;

    String userId;

    String accountName;

    BigDecimal initialBalance;

    BigDecimal currentBalance;

    Boolean isSavingsAccount;

    /**
     * 口座エンティティを生成するコンストラクタ
     *
     * @param accountId        口座ID
     * @param userId           ユーザーID
     * @param accountName      口座名
     * @param initialBalance   初期残高
     * @param isSavingsAccount 保有口座フラグ
     */
    public Account(String accountId, String userId, String accountName, BigDecimal initialBalance, Boolean isSavingsAccount) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountName = accountName;
        this.initialBalance = initialBalance;
        this.currentBalance = initialBalance;
        this.isSavingsAccount = isSavingsAccount;
    }

}
