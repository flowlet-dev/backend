package com.example.flowlet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "m_account", schema = "flowlet")
public class MAccount extends BaseEntity {
    @Id
    @Size(max = 10)
    @Column(name = "account_id", nullable = false, length = 10)
    private String accountId;

    @Size(max = 50)
    @NotNull
    @Column(name = "account_name", nullable = false, length = 50)
    private String accountName;

    @Size(max = 10)
    @NotNull
    @Column(name = "account_type", nullable = false, length = 10)
    private String accountType;

    @NotNull
    @Column(name = "initial_balance", nullable = false)
    private Integer initialBalance;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;


}