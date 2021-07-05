package com.saptarshi.technohrms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_leave_setup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String leaveName;

    @OneToOne
    private Employee employee;

    private int totalLeave;

    private int usedLeave;

    private int pendingLeave;

}
