package com.payment.processor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "accounts", schema = "public") // Specify the schema name if necessary
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "last_payment_date")
    private Timestamp lastPaymentDate;

    @Column(name = "created_on")
    private Timestamp createdOn;
}