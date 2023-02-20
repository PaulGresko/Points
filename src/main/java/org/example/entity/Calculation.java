package org.example.entity;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Getter
@Setter
@NoArgsConstructor
@Table
public class Calculation implements Serializable {
    @Id
    @SequenceGenerator(name="calculation_id_seq_generator",
            sequenceName = "calculation_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "calculation_id_seq_generator")
    private Long ID;
    @Column
    private Date t1;
    @Column
    private Date t2;
    //@Column
    private String method;

    @OneToMany(mappedBy = "calculation")
    private List<Record> records = new ArrayList<>();
}
