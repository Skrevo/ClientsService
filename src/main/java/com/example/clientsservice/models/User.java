package com.example.clientsservice.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//
@Entity
@Table(name = "users")
public class User {
    public enum Role{
        USER,
        ADMIN
    }
    public enum Status{
        CREATED, ACTIVE, BLOCKED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private Role role;
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private Status status;
    @Column(nullable = false, unique = true)
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
