package org.example.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "point")
public class Point implements Serializable {
    @Id
            @SequenceGenerator(name = "point_id_seq_generator",
            sequenceName = "point_id_seq",allocationSize = 1)
            @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "point_id_seq_generator")
    Integer ID;
    @Column
    Double X;
    @Column
    Double Y;
    @Column
    Double Z;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;
}
