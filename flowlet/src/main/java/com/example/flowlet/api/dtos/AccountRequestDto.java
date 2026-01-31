package com.example.flowlet.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 口座リクエストDTO
 */
@Data
@Schema(description = "口座リクエストDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequestDto {

    @Schema(description = "口座名", example = "口座名", minLength = 1, maxLength = 100)
    @NotBlank(message = "{error.unspecified_account_name.message}")
    @Size(max = 100, message = "{error.invalid_account_name_length.message}")
    private String accountName;

    @Schema(description = "初期残高", example = "10000.00")
    @NotNull(message = "{error.unspecified_initial_balance.message}")
    @Min(value = 0, message = "{error.invalid_initial_balance.message}")
    private BigDecimal initialBalance;

    @Schema(description = "貯金用口座フラグ", example = "true")
    @NotNull(message = "{error.unspecified_is_savings_account.message}")
    private Boolean isSavingsAccount;

}
