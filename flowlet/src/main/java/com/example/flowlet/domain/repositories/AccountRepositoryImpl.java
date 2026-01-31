package com.example.flowlet.domain.repositories;

import com.example.flowlet.domain.models.entities.Account;
import com.example.flowlet.infrastructure.persistence.entities.MAccount;
import com.example.flowlet.infrastructure.persistence.mappers.AccountMapper;
import com.example.flowlet.infrastructure.persistence.repositories.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 口座リポジトリ実装
 */
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountMapper accountMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateAccountId() {
        return accountJpaRepository.generateAccountId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account findByAccountId(String accountId) {
        MAccount mAccount = accountJpaRepository.findByAccountId(accountId);
        return accountMapper.toDomain(mAccount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> findByUserId(String userId) {
        List<MAccount> mAccounts = accountJpaRepository.findByUserId(userId);
        return accountMapper.toDomainList(mAccounts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Account account) {
        accountJpaRepository.save(accountMapper.toEntity(account));
    }

}
