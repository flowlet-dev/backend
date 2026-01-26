package com.example.flowlet.api.controllers;

import com.example.flowlet.api.dtos.AccountDto;
import com.example.flowlet.application.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "口座関連API")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * 口座一覧取得API
     *
     * @return 口座一覧
     */
    @Operation(summary = "口座一覧取得API", description = "口座一覧を取得")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<AccountDto>> getAccounts() {

        String loginUserId = "0000000001";

        List<AccountDto> responseBody = accountService.getAccounts(loginUserId);
        return ResponseEntity.ok(responseBody);

    }

}
