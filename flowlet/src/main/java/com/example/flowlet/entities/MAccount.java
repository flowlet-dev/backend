package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_accounts", schema = "flowlet", indexes = {@Index(name = "idx_m_accounts_user_id",
        columnList = "user_id")})
public class MAccount extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('ACC'::text || lpad((nextval('flowlet.seq_m_accounts')), 7, '0'))")
    @Column(name = "account_id", nullable = false, length = 10)
    private String accountId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "initial_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialBalance;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "current_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal currentBalance;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_savings_account", nullable = false)
    private Boolean isSavingsAccount;

    @OneToMany(mappedBy = "withdrawalAccount")
    private Set<MCreditCard> mCreditCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<MRecurringExpens> mRecurringExpenses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<TTransaction> tTransactionsAccount = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fromAccount")
    private Set<TTransaction> tTransactionsFromAccount = new LinkedHashSet<>();

    @OneToMany(mappedBy = "toAccount")
    private Set<TTransaction> tTransactionsToAccount = new LinkedHashSet<>();


}