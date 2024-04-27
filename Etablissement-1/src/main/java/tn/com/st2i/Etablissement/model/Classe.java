package tn.com.st2i.Etablissement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="classe")
public class Classe implements Serializable,Cloneable{

    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "classe_id_classe_seq", name = "classe_id_classe_seq")
    @GeneratedValue(generator = "classe_id_classe_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "nbr", nullable = false, length = 40)
    private int nbr ;

    @Transient
    private List<Long> classes;






}
