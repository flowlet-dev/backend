package com.example.flowlet.infrastructure.persistence.repositories;

import com.example.flowlet.infrastructure.persistence.entities.MAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

/**
 * 口座リポジトリJPA実装
 */
public interface AccountJpaRepository extends JpaRepository<MAccount, String> {

    @NativeQuery("SELECT 'ACC' || lpad(cast(nextval('flowlet.seq_m_accounts') as character varying), 7, '0');")
    String generateAccountId();

    MAccount findByAccountId(String accountId);

    List<MAccount> findByUserId(String loginUserId);

}
