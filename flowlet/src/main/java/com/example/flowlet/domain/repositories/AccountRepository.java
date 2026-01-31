package com.example.flowlet.domain.repositories;

import com.example.flowlet.domain.models.entities.Account;

import java.util.List;

/**
 * 口座リポジトリ
 */
public interface AccountRepository {

    /**
     * 口座IDを生成する
     *
     * @return 生成した口座ID
     */
    String generateAccountId();

    /**
     * ユーザーIDで口座を検索する
     *
     * @param loginUserId ログインユーザーID
     * @return 口座一覧
     */
    List<Account> findByUserId(String loginUserId);

    /**
     * 口座を保存する
     *
     * @param account 保存する口座
     */
    void save(Account account);

}
