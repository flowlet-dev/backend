package com.example.flowlet.infrastructure.persistence.repository;


import com.example.flowlet.infrastructure.persistence.entity.TTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TTransaction, UUID> {
}
