package tn.com.st2i.Etablissement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="année_Scolaire")
public class AnnéeScolaire implements java.io.Serializable,Cloneable{


    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "annéeS_id_annéeS_seq", name = "annéeS_id_annéeS_seq")
    @GeneratedValue(generator = "annéeS_id_annéeS_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "année_Début")
    private Date annéeDébut;
    @Column(name = "année_Fin")
    private Date annéeFin;


    public AnnéeScolaire clone() throws CloneNotSupportedException {
        return (AnnéeScolaire) super.clone();
    }
}
