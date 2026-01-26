package com.example.flowlet.domain.repositories;

import com.example.flowlet.domain.models.entities.Account;

import java.util.List;

/**
 * 口座リポジトリ
 */
public interface AccountRepository {

    /**
     * ユーザーIDで口座を検索する
     *
     * @param loginUserId ログインユーザーID
     * @return 口座一覧
     */
    List<Account> findByUserId(String loginUserId);

}
