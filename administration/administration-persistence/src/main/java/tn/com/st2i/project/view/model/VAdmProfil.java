package tn.com.st2i.project.view.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "v_adm_profil")
public class VAdmProfil implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 40)
    private String code;

    @Column(name = "des_fr", nullable = false, length = 300)
    private String desFr;

    @Column(name = "des_en",  length = 300)
    private String desEn;

    @Column(name = "state", nullable = false, precision = 1, scale = 0)
    private String  isActive;
}
