package com.example.flowlet.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "m_recurring_expenses", schema = "flowlet", indexes = {@Index(name = "idx_m_recurring_expenses_user_id",
        columnList = "user_id")})
public class MRecurringExpens extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('REC'::text || lpad((nextval('flowlet.seq_m_recurring_expenses')), 7, '0'))")
    @Column(name = "recurring_expense_id", nullable = false, length = 10)
    private String recurringExpenseId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "expense_name", nullable = false, length = 100)
    private String expenseName;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private MCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private MAccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id")
    private MCreditCard creditCard;

    @NotNull
    @Column(name = "payment_day", nullable = false)
    private Integer paymentDay;

    @Column(name = "memo", length = Integer.MAX_VALUE)
    private String memo;


}