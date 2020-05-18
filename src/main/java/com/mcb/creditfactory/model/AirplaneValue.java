package com.mcb.creditfactory.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcb.creditfactory.external.CollateralValue;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "airplane_assessed_value")
@Data

public class AirplaneValue implements CollateralValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "value")
    private BigDecimal value;



    @Override
    public String toString() {
        return "AirplaneValue{" +
                "id=" + id +
                ", localDate=" + date +
                ", value=" + value +
                '}';
    }
}
