package com.example.flowlet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "m_app_settings", schema = "flowlet")
public class MAppSetting extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('SET'::text || lpad((nextval('flowlet.seq_m_app_settings')), 7, '0'))")
    @Column(name = "app_settings_id", nullable = false, length = 10)
    private String appSettingsId;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @NotNull
    @Column(name = "payday", nullable = false)
    private Integer payday;


}