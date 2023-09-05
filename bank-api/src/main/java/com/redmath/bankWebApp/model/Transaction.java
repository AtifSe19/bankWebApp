package com.redmath.bankWebApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;
    private String description;
    @NotNull
    private Long amount;
    @NotNull
    private String db_cr_indicator;

    @ManyToOne
    @JoinColumn(name = "fk_account_holder_id", nullable = false)
    @JsonIgnore
    private AccountHolder accountHolder;
}
