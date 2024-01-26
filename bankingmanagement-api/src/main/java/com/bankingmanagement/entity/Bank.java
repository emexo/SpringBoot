package com.bankingmanagement.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "Bank")
public class Bank implements Serializable {
    public static final long serialVersionUID = 4543545l;

    @Id
    @Column(name = "Bank_Code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankCode;

    @Column(name = "Bank_Name")
    private String bankName;

    @Column(name = "Bank_Address")
    private String bankAddress;

    @OneToMany(mappedBy = "bank")
    private Set<Branch> branchSet;

}
