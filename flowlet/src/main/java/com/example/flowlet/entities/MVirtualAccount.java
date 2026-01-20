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
@Table(name = "m_virtual_account", schema = "flowlet")
public class MVirtualAccount extends BaseEntity {
    @Id
    @Size(max = 10)
    @Column(name = "virtual_account_id", nullable = false, length = 10)
    private String virtualAccountId;

    @Size(max = 10)
    @NotNull
    @Column(name = "account_id", nullable = false, length = 10)
    private String accountId;

    @Size(max = 50)
    @NotNull
    @Column(name = "virtual_account_name", nullable = false, length = 50)
    private String virtualAccountName;

    @Size(max = 300)
    @Column(name = "purpose", length = 300)
    private String purpose;


}