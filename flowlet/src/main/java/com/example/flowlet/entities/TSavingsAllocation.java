package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_savings_allocations", schema = "flowlet", indexes = {
        @Index(name = "idx_t_savings_allocations_transaction_id",
                columnList = "transaction_id"),
        @Index(name = "idx_t_savings_allocations_goal_id",
                columnList = "savings_goal_id")})
public class TSavingsAllocation extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('SAL'::text || lpad((nextval('flowlet.seq_t_savings_allocations')), 7, '0'))")
    @Column(name = "savings_allocation_id", nullable = false, length = 10)
    private String savingsAllocationId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TTransaction transaction;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "savings_goal_id", nullable = false)
    private MSavingsGoal savingsGoal;

    @NotNull
    @Column(name = "allocation_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal allocationAmount;


}