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
@Table(name = "m_categories", schema = "flowlet", indexes = {
        @Index(name = "idx_m_categories_user_id",
                columnList = "user_id"),
        @Index(name = "idx_m_categories_parent_category_id",
                columnList = "parent_category_id")})
public class MCategory extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('CAT'::text || lpad((nextval('flowlet.seq_m_categories')), 7, '0'))")
    @Column(name = "category_id", nullable = false, length = 10)
    private String categoryId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Size(max = 100)
    @NotNull
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Size(max = 20)
    @NotNull
    @Column(name = "category_type", nullable = false, length = 20)
    private String categoryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private MCategory parentCategory;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "parentCategory")
    private Set<MCategory> mCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<MRecurringExpens> mRecurringExpenses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<TBudget> tBudgets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<TTransaction> tTransactions = new LinkedHashSet<>();


}