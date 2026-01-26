package com.example.flowlet.infrastructure.persistence.repositories;

import com.example.flowlet.infrastructure.persistence.entities.MAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 口座リポジトリJPA実装
 */
public interface AccountJpaRepository extends JpaRepository<MAccount, String> {

    List<MAccount> findByUserId(String loginUserId);

}
