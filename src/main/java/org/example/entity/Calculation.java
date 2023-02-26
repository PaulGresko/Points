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

    public Calculation(){}
    public Calculation(Date t1, Date t2, String method) {
        this.t1 = t1;
        this.t2 = t2;
        this.method = method;
    }

    public Long getID() {
        return ID;
    }

    public Date getStartDate() {
        return t1;
    }

    public Date getEndDate() {
        return t2;
    }

    public String getMethod() {
        return method;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setStartDate(Date startDate) {
        this.t1 = startDate;
    }

    public void setEndDate(Date endDate) {
        this.t2 = endDate;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
