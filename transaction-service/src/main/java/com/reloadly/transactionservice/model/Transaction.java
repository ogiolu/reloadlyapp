package com.reloadly.transactionservice.model;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
@Entity
@Audited
public class Transaction   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private TransactionType transactionType;
    private Status status;
    @NotNull
    private Long accountId;
    private Double transctionAmount;
    private Timestamp transDate;

}
