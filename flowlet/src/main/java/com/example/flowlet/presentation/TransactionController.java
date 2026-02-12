package com.example.flowlet.presentation;

import com.example.flowlet.application.service.TransactionService;
import com.example.flowlet.infrastructure.persistence.entity.TTransaction;
import com.example.flowlet.presentation.dto.TransactionRequest;
import com.example.flowlet.presentation.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * 収支登録API
     *
     * @param request 収支登録リクエスト
     */
    @PostMapping
    public void register(@RequestBody @Valid TransactionRequest request) {

        transactionService.register(
                request.getTransactionDate(),
                request.getAmount(),
                request.getTransactionType(),
                request.getMemo()
        );

    }

    @GetMapping
    public List<TransactionResponse> findAll() {

        List<TTransaction> tTransactions = transactionService.findAll();

        return tTransactions.stream()
                .map(t -> new TransactionResponse(
                        t.getTransactionId(),
                        t.getTransactionDate(),
                        t.getAmount(),
                        t.getTransactionType(),
                        t.getMemo()
                ))
                .toList();
    }

}
