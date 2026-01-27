package com.example.flowlet.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * エラーDTO
 */
@Data
@AllArgsConstructor
@Schema(description = "エラーDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    @Schema(description = "エラーコード")
    private String code;

    @Schema(description = "エラー内容")
    private String error;


}
