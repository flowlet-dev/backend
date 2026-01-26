package com.example.flowlet.application.services;

import com.example.flowlet.api.dtos.AccountDto;
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
     * @param userId ユーザーID
     * @return 口座一覧
     */
    public List<AccountDto> getAccounts(String userId) {

        List<Account> accounts = accountRepository.findByUserId(userId);

        return accounts.stream().map(AccountDto::new).toList();

    }
}
