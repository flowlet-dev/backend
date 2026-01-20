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
@Table(name = "m_setting", schema = "flowlet")
public class MSetting extends BaseEntity {
    @Id
    @Size(max = 10)
    @Column(name = "setting_id", nullable = false, length = 10)
    private String settingId;

    @Size(max = 50)
    @NotNull
    @Column(name = "setting_key", nullable = false, length = 50)
    private String settingKey;

    @Size(max = 100)
    @NotNull
    @Column(name = "setting_value", nullable = false, length = 100)
    private String settingValue;


}