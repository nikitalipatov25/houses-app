package com.nikitalipatov.houseapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "houses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Houses {
    @Id
    @SequenceGenerator(name = "house_local_seq", sequenceName = "houses_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_local_seq")
    private int id;
    private String adress;
    @ManyToOne
    private Users owner;
    @OneToMany
    private Set<Users> residents;
}
