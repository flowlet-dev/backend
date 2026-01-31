package com.example.flowlet.api.controllers;

import com.example.flowlet.api.dtos.AccountDto;
import com.example.flowlet.api.dtos.AccountRequestDto;
import com.example.flowlet.api.exceptions.NotFoundException;
import com.example.flowlet.application.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    /**
     * 口座登録API
     *
     * @param request 口座登録リクエスト
     * @return 登録した口座情報
     */
    @Operation(summary = "口座登録API", description = "口座を登録")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<AccountDto> registerAccount(@Valid @RequestBody AccountRequestDto request) {

        String loginUserId = "0000000001";

        AccountDto responseBody = accountService.registerAccount(request, loginUserId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{accountId}")
                .buildAndExpand(responseBody.getAccountId())
                .toUri();

        return ResponseEntity.created(location).body(responseBody);

    }

    /**
     * 口座取得API
     *
     * @param accountId 取得する口座ID
     * @return 指定した口座IDの口座情報
     */
    @Operation(summary = "口座取得API", description = "指定した口座IDの口座情報を取得")
    @GetMapping(path = "/{accountId}", produces = "application/json")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountId) throws NotFoundException {

        String loginUserId = "0000000001";

        AccountDto responseBody = accountService.getAccount(accountId, loginUserId);

        return ResponseEntity.ok(responseBody);

    }

}
