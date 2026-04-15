package tn.com.st2i.project.administration.model;
 
import java.util.List;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
 
@Entity
@Table(name = "adm_fonc")
 
public class AdmFonc implements java.io.Serializable, Cloneable {
    private transient static final long serialVersionUID = 1L;
 
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_fonc_id_fonc_seq", name = "adm_fonc_id_fonc_seq")
    @GeneratedValue(generator = "adm_fonc_id_fonc_seq", strategy = GenerationType.SEQUENCE)
    @Id
    
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
 
    @Column(name = "id_parent")
    private Long idParent;
 
    @Column(name = "code", nullable = false, length = 40)
    private String code;
 
    @Column(name = "icon", length = 300)
    private String icon;
 
    @Column(name = "des_fr", nullable = false, length = 300)
    private String label;

    @Column(name = "des_en", nullable = false, length = 300)
    private String labelEn;

    @Column(name = "is_active", nullable = false)
    private Long is_active;
    
    @Transient
    private Boolean checked=false ;
    
    @Column(name = "router", length = 1500)
    private String action;
 
    @Transient
    private List<AdmFonc> listSousMenu;
 
    public AdmFonc clone() throws CloneNotSupportedException {
        return (AdmFonc) super.clone();
    }
}