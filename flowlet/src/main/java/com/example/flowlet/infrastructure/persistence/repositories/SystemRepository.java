package com.example.flowlet.infrastructure.persistence.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * システム共通リポジトリ
 */
@Repository
public class SystemRepository {
    @PersistenceContext
    private EntityManager em;

    public LocalDateTime getCurrentDateTime() {

        String sql = "SELECT CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Tokyo'";
        return (LocalDateTime) em.createNativeQuery(sql).getSingleResult();

    }


}
