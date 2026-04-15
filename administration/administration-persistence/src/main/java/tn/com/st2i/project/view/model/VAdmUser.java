package tn.com.st2i.project.view.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Data
@Immutable
@Entity(name = "VAdmUser")
@Table(name = "v_adm_user")
public class VAdmUser implements java.io.Serializable, Cloneable {
    private transient static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    
    @Column(name = "id")
    private Long id;

    @Column(name = "mail", length = 60)
    private String mail;

    @Column(name = "user_name", length = 300)
    private String username;

    @Column(name = "date_expire", length = 13)
    private Date dateExpire;

    @Column(name = "date_create", length = 13)
    private Date createDate;

    @Column(name = "pwd", length = 100)
    private String pwd;

    @Column(name = "is_active", precision = 1, scale = 0)
    private Integer isActive;

    @Column(name = "code_profes", length = 40)
    private String codeProfes;

    @Column(name = "des_profes_fr", length = 300)
    private String desProfesFr;

    @Column(name = "des_profes_en", length = 300)
    private String desProfesEn;

    @Column(name = "des_profil_fr", length = 300)
    private String desProfilFr;

    @Column(name = "des_profil_en", length = 300)
    private String desProfilEn;

    @Column(name = "state", length = 2147483647)
    private String state;

    public VAdmUser clone() throws CloneNotSupportedException {
        return (VAdmUser) super.clone();
    }
}
