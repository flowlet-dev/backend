package com.example.flowlet.application.service;

import com.example.flowlet.infrastructure.persistence.entity.TTransaction;
import com.example.flowlet.infrastructure.persistence.repository.TransactionJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TransactionService {

    private final TransactionJpaRepository transactionJpaRepository;

    public TransactionService(TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }

    /**
     * 収支登録
     *
     * @param transactionDate 取引日
     * @param amount          金額
     * @param transactionType 取引種別
     * @param memo            メモ
     */
    public void register(LocalDate transactionDate, int amount, String transactionType, String memo) {

        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }

        TTransaction tTransaction = new TTransaction(
                UUID.randomUUID(),
                transactionDate,
                amount,
                transactionType,
                memo
        );

        transactionJpaRepository.save(tTransaction);

    }

    /**
     * 収支一覧取得
     *
     * @return 収支一覧
     */
    @Transactional(readOnly = true)
    public List<TTransaction> findAll() {
        return transactionJpaRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getTransactionDate().compareTo(a.getTransactionDate()))
                .toList();
    }

}
