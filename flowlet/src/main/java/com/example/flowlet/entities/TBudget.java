package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "t_budgets", schema = "flowlet", indexes = {
        @Index(name = "idx_t_budgets_user_id",
                columnList = "user_id"),
        @Index(name = "idx_t_budgets_cycle_dates",
                columnList = "cycle_start_date, cycle_end_date")}, uniqueConstraints = {@UniqueConstraint(name = "t_budgets_user_id_cycle_start_date_category_id_key",
        columnNames = {
                "user_id",
                "cycle_start_date",
                "category_id"})})
public class TBudget extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('BDG'::text || lpad((nextval('flowlet.seq_t_budgets')), 7, '0'))")
    @Column(name = "budget_id", nullable = false, length = 10)
    private String budgetId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @NotNull
    @Column(name = "cycle_start_date", nullable = false)
    private LocalDate cycleStartDate;

    @NotNull
    @Column(name = "cycle_end_date", nullable = false)
    private LocalDate cycleEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MCategory category;

    @NotNull
    @Column(name = "budget_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal budgetAmount;


}