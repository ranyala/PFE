package tn.com.st2i.project.administration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "adm_fonc_profil")

public class AdmFoncProfil implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_fonc_profil_id_fonc_profil_seq", name = "adm_fonc_profil_seq")
	@GeneratedValue(generator = "adm_fonc_profil_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long idFoncProfil;

	@Column(name = "id_fonc", nullable = false)
	private Long idFonc;

	@Column(name = "id_profil", nullable = false)
	private Long idProfil;

	

	public AdmFoncProfil clone() throws CloneNotSupportedException {
		return (AdmFoncProfil) super.clone();
	}
}
