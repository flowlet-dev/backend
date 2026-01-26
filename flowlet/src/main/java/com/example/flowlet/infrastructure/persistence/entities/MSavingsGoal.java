package com.example.flowlet.infrastructure.persistence.entities;

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
@Table(name = "m_savings_goals", schema = "flowlet", indexes = {@Index(name = "idx_m_savings_goals_user_id",
        columnList = "user_id")})
public class MSavingsGoal extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('SVG'::text || lpad((nextval('flowlet.seq_m_savings_goals')), 7, '0'))")
    @Column(name = "savings_goal_id", nullable = false, length = 10)
    private String savingsGoalId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "goal_name", nullable = false, length = 100)
    private String goalName;

    @Column(name = "target_amount", precision = 15, scale = 2)
    private BigDecimal targetAmount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "current_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal currentAmount;

    @OneToMany(mappedBy = "savingsGoal")
    private Set<TSavingsAllocation> tSavingsAllocations = new LinkedHashSet<>();


}