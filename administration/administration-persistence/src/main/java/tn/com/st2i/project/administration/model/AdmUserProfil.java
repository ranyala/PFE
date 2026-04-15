package tn.com.st2i.project.administration.model;

import java.util.Date;

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
@Table(name = "adm_user_profil", uniqueConstraints = @UniqueConstraint(columnNames = { "id_user", "id_profil" }))

public class AdmUserProfil implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_user_profil_id_user_profil_seq", name = "adm_user_profil_id_user_profil_seq")
	@GeneratedValue(generator = "adm_user_profil_id_user_profil_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "id_user", nullable = false)
	private Long idUser;

	@Column(name = "id_profil", nullable = false)
	private Long idProfil;

	public AdmUserProfil clone() throws CloneNotSupportedException {
		return (AdmUserProfil) super.clone();
	}

	public AdmUserProfil(Long idUser, Long profileId) {
		this.idUser = idUser;
		this.idProfil = profileId;
	}
}
