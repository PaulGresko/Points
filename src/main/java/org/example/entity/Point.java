package org.example.entity;


import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pointa")
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


    public Point(){}
    public Point(Double x, Double y, Double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }

    public Double getZ() {
        return Z;
    }

    public void setZ(Double z) {
        Z = z;
    }
}
