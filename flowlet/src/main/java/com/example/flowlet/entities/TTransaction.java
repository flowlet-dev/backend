package com.example.flowlet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "t_transaction", schema = "flowlet")
public class TTransaction extends BaseEntity {
    @Id
    @Size(max = 10)
    @Column(name = "transaction_id", nullable = false, length = 10)
    private String transactionId;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Size(max = 5)
    @Column(name = "category_id", length = 5)
    private String categoryId;

    @Size(max = 10)
    @Column(name = "from_account_id", length = 10)
    private String fromAccountId;

    @Size(max = 10)
    @Column(name = "to_account_id", length = 10)
    private String toAccountId;

    @Size(max = 10)
    @Column(name = "virtual_account_id", length = 10)
    private String virtualAccountId;

    @Size(max = 300)
    @Column(name = "memo", length = 300)
    private String memo;


}