package com.example.flowlet.application.services;

import com.example.flowlet.api.dtos.AccountDto;
import com.example.flowlet.api.dtos.AccountRequestDto;
import com.example.flowlet.domain.models.entities.Account;
import com.example.flowlet.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * 口座一覧を取得する
     *
     * @param loginUserId ログインユーザーID
     * @return 口座一覧
     */
    public List<AccountDto> getAccounts(String loginUserId) {

        List<Account> accounts = accountRepository.findByUserId(loginUserId);

        return accounts.stream().map(AccountDto::new).toList();

    }

    /**
     * 口座を登録する
     *
     * @param request     口座登録リクエスト
     * @param loginUserId ログインユーザーID
     * @return 登録した口座情報
     */
    public AccountDto registerAccount(AccountRequestDto request, String loginUserId) {

        String accountId = accountRepository.generateAccountId();

        Account account = new Account(
                accountId,
                loginUserId,
                request.getAccountName(),
                request.getInitialBalance(),
                request.getIsSavingsAccount()
        );

        accountRepository.save(account);

        return new AccountDto(account);

    }
}
