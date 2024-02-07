package com.carlosbernal2222.vanlifeapi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class VanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "van_id",nullable = false)
    private String name;

    @Column
    private double price;

    @Column(length = 1000)
    private String description;

    private String imgUrl;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private HostEntity host;

    // getters and setters (consider making fields immutable)
}
