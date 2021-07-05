package com.saptarshi.technohrms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbl_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String mobile;
    private Date joiningDate;
    private Date dob;
    private String workType;

    @OneToOne
    private Designation designation;
    @OneToOne
    private Department department;



}

