package com.example.flowlet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "m_users", schema = "flowlet", uniqueConstraints = {
        @UniqueConstraint(name = "m_users_username_key",
                columnNames = {"username"}),
        @UniqueConstraint(name = "m_users_email_key",
                columnNames = {"email"})})
public class MUser extends BaseEntity {
    @Id
    @Size(max = 10)
    @ColumnDefault("('USR'::text || lpad((nextval('flowlet.seq_m_users')), 7, '0'))")
    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @Size(max = 255)
    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;


}