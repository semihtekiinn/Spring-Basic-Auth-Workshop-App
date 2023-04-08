package com.works.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please type your name.")
    private String firstName;

    @NotBlank(message = "Please type your name.")
    private String lastName;

    @Column(unique = true)
    @Email(message = "It should be in email format.")
    private String email;

    @NotBlank(message = "Please type your password.")
    private String password;

    private boolean enabled;
    private boolean tokenExpired;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",referencedColumnName = "id"))
    private List<Role> roles;
}
