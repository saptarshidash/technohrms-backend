package com.saptarshi.technohrms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbl_training_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Training training;
    @OneToOne
    private Employee employee;

    private int rating;

    private Date startDate;

    private Date endDate;

    private boolean completionStatus;
}
