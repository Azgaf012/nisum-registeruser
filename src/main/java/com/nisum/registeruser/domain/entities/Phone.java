package com.nisum.registeruser.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Integer citycode;
    private Integer countrycode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}
