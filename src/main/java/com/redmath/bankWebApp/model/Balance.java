package com.redmath.bankWebApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "balance")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Balance {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private LocalDateTime date;
    @NonNull
    private Long amount;
    @NonNull
    private String db_cr_indicator;

    @ManyToOne
    @JoinColumn(name = "fk_acc_holder_id", nullable = false)
    @JsonIgnore
    private AccountHolder accountHolder;

}
