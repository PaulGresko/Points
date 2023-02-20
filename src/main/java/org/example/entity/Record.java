package org.example.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Record implements Serializable {
    @Id
    @SequenceGenerator(name="record_id_seq_generator",
    sequenceName = "record_id_seq",allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
            generator = "record_id_seq_generator")
    private Long ID;

    @Column
    private Double S;
    @OneToMany(mappedBy = "record")
    private List<Point> points = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calculation_id")
    private Calculation calculation;

}
