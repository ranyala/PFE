package tn.com.st2i.project.administration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "adm_profession")
public class AdmProfession implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_profession_id_profes_seq", name = "adm_profession_id_profes_seq")
	@GeneratedValue(generator = "adm_profession_id_profes_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false, length = 40)
	private String code;

	@Column(name = "des_fr", nullable = false, length = 300)
	private String des_fr;

	@Column(name = "des_en", length = 300)
	private String des_en;

	public AdmProfession clone() throws CloneNotSupportedException {
		return (AdmProfession) super.clone();
	}
}
