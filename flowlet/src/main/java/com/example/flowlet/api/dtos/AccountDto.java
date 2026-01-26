package com.example.flowlet.api.dtos;

import com.example.flowlet.domain.models.entities.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 口座情報DTO
 */
@Data
@Schema(description = "口座情報DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {


    @Schema(description = "口座ID", example = "ACC0000001")
    private String accountId;

    @Schema(description = "口座名", example = "三菱UFJ銀行・メイン口座")
    private String accountName;

    @Schema(description = "初期残高", example = "100000.00")
    private BigDecimal initialBalance;

    @Schema(description = "現在の残高", example = "125000.00")
    private BigDecimal currentBalance;

    @Schema(description = "貯金用口座フラグ", example = "true")
    private Boolean isSavingsAccount;

    /**
     * AccountエンティティからDTOに変換するコンストラクタ
     *
     * @param account Accountエンティティ
     */
    public AccountDto(Account account) {
        this.accountId = account.accountId();
        this.accountName = account.accountName();
        this.initialBalance = account.initialBalance();
        this.currentBalance = account.currentBalance();
        this.isSavingsAccount = account.isSavingsAccount();
    }

}
