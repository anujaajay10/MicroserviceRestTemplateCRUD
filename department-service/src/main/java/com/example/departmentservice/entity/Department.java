package com.example.departmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "departments")
public class Department {
    @Id
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
