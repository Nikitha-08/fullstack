package com.java.agrofund.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LoanRejected")
@Entity
public class LoanRejected {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String ApplicantName;
    private String RejectedReason;
    private String Requirements;

}
