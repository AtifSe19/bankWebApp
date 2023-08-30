package com.redmath.bankWebApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_holder")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AccountHolder {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String username;
    @NotNull
    @Column(unique=true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String roles;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Balance> balances;
}
