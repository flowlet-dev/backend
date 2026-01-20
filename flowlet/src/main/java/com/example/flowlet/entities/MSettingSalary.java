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
@Table(name = "m_setting_salary", schema = "flowlet")
public class MSettingSalary extends BaseEntity {
    @Id
    @Size(max = 10)
    @Column(name = "setting_salary_id", nullable = false, length = 10)
    private String settingSalaryId;

    @NotNull
    @Column(name = "payday", nullable = false)
    private Integer payday;

    @Size(max = 20)
    @NotNull
    @Column(name = "adjust_type", nullable = false, length = 20)
    private String adjustType;


}