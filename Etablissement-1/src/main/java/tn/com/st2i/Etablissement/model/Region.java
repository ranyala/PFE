package tn.com.st2i.Etablissement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="region")
public class Region implements java.io.Serializable,Cloneable{
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "region_id_region_seq", name = "region_id_region_seq")
    @GeneratedValue(generator = "region_id_region_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    public Region clone() throws CloneNotSupportedException {
        return (Region) super.clone();
    }


}
