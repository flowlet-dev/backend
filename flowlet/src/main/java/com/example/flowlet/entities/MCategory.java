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
@Table(name = "m_category", schema = "flowlet")
public class MCategory extends BaseEntity {
    @Id
    @Size(max = 5)
    @Column(name = "category_id", nullable = false, length = 5)
    private String categoryId;

    @Size(max = 5)
    @Column(name = "parent_category_id", length = 5)
    private String parentCategoryId;

    @Size(max = 50)
    @NotNull
    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @Size(max = 10)
    @NotNull
    @Column(name = "flow_type", nullable = false, length = 10)
    private String flowType;

    @Size(max = 50)
    @Column(name = "icon_key", length = 50)
    private String iconKey;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;


}