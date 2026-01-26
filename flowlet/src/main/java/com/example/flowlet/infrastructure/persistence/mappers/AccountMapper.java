package com.example.flowlet.infrastructure.persistence.mappers;

import com.example.flowlet.domain.models.entities.Account;
import com.example.flowlet.infrastructure.persistence.entities.MAccount;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 口座Mapper
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * MAccountをAccountに変換する
     *
     * @param mAccount MAccountエンティティ
     * @return Accountドメインエンティティ
     */
    Account toDomain(MAccount mAccount);

    /**
     * AccountをMAccountに変換する
     *
     * @param account Accountドメインエンティティ
     * @return MAccountエンティティ
     */
    MAccount toEntity(Account account);

    /**
     * MAccountリストをAccountリストに変換する
     *
     * @param mAccounts MAccountエンティティリスト
     * @return Accountドメインエンティティリスト
     */
    List<Account> toDomainList(List<MAccount> mAccounts);



}
