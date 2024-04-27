package tn.com.st2i.Etablissement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="etablissement")
public class Etablissement implements java.io.Serializable,Cloneable {

    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "etablissement_id_etablissement_seq", name = "etablissement_id_etablissement_seq")
    @GeneratedValue(generator = "etablissement_id_etablissement_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "adresse", nullable = false, length = 40)
    private String adresse ;

    @Transient
    private List<Long> classes;

    public Etablissement  clone() throws CloneNotSupportedException {
        return (Etablissement) super.clone();
    }


}
