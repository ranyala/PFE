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
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "adm_profil")
public class AdmProfil implements java.io.Serializable, Cloneable {
    private transient static final long serialVersionUID = 1L;

    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_profil_id_profil_seq", name = "adm_profil_id_profil_seq")
    @GeneratedValue(generator = "adm_profil_id_profil_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 40)
    private String code;

    @Column(name = "des_fr", nullable = false, length = 300)
    private String desFr;

    @Column(name = "des_en",  length = 300)
    private String desEn;

    @Column(name = "is_active", nullable = false, precision = 1, scale = 0)
    private Integer isActive;

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "date_update")
    private Date dateUpdate;
    
    @Transient
    private List<Long> listAdmFoncIds;
    
    public void setDateCreate(java.util.Date date) {
        this.dateCreate = (date != null) ? new Date(date.getTime()) : null;
    }
    public void setDateUpdate(java.util.Date date) {
        this.dateUpdate = (date != null) ? new Date(date.getTime()) : null;
    }

    public AdmProfil clone() throws CloneNotSupportedException {
        return (AdmProfil) super.clone();
    }
}
