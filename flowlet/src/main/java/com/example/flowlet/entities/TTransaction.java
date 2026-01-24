package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_transactions", schema = "flowlet", indexes = {
        @Index(name = "idx_t_transactions_user_id",
                columnList = "user_id"),
        @Index(name = "idx_t_transactions_date",
                columnList = "transaction_date"),
        @Index(name = "idx_t_transactions_category_id",
                columnList = "category_id"),
        @Index(name = "idx_t_transactions_account_id",
                columnList = "account_id"),
        @Index(name = "idx_t_transactions_credit_card_id",
                columnList = "credit_card_id")})
public class TTransaction extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('TRX'::text || lpad((nextval('flowlet.seq_t_transactions')), 7, '0'))")
    @Column(name = "transaction_id", nullable = false, length = 10)
    private String transactionId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private MAccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id")
    private MCreditCard creditCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id")
    private MAccount fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    private MAccount toAccount;

    @Column(name = "memo", length = Integer.MAX_VALUE)
    private String memo;

    @OneToMany(mappedBy = "transaction")
    private Set<TSavingsAllocation> tSavingsAllocations = new LinkedHashSet<>();


}