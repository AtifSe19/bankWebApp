package com.redmath.bankWebApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "balances")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Balance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private LocalDateTime date;
    @NonNull
    private Long amount;
    @NonNull
    private String db_cr_indicator;

    @ManyToOne
    @JoinColumn(name = "fk_account_holder_id", nullable = false)
    @JsonIgnore
    private AccountHolder accountHolders;

}
