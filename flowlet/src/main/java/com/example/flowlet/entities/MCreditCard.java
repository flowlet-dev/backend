package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_credit_cards", schema = "flowlet", indexes = {
        @Index(name = "idx_m_credit_cards_user_id",
                columnList = "user_id"),
        @Index(name = "idx_m_credit_cards_withdrawal_account_id",
                columnList = "withdrawal_account_id")})
public class MCreditCard extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('CRD'::text || lpad((nextval('flowlet.seq_m_credit_cards')), 7, '0'))")
    @Column(name = "credit_card_id", nullable = false, length = 10)
    private String creditCardId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "card_name", nullable = false, length = 100)
    private String cardName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "withdrawal_account_id", nullable = false)
    private MAccount withdrawalAccount;

    @NotNull
    @Column(name = "withdrawal_day", nullable = false)
    private Integer withdrawalDay;

    @Size(max = 20)
    @NotNull
    @Column(name = "weekend_handling", nullable = false, length = 20)
    private String weekendHandling;

    @Size(max = 20)
    @NotNull
    @Column(name = "payment_cycle", nullable = false, length = 20)
    private String paymentCycle;

    @OneToMany(mappedBy = "creditCard")
    private Set<MRecurringExpens> mRecurringExpenses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "creditCard")
    private Set<TTransaction> tTransactions = new LinkedHashSet<>();


}