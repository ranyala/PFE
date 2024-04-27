package tn.com.st2i.project.view.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "V_ADM_TEAM")
public class VAdmTeam implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_hierarchy_team")
	private Long idHierarchyTeam;

	@Column(name = "id_adm_team")
	private Long idAdmTeam;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "libelle", length = 250)
	private String libelle;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "nom_user", length = 300)
	private String nomUser;

	@Column(name = "id_profil")
	private Long idProfil;

	@Column(name = "cod_profil", length = 40)
	private String codProfil;

	@Column(name = "des_profil", length = 300)
	private String desProfil;

	@Column(name = "id_sup")
	private Long idSup;

	@Column(name = "nom_sup", length = 300)
	private String nomSup;

	@Column(name = "login_sup", length = 60)
	private String loginSup;

	@Column(name = "id_profil_sup")
	private Long idProfilSup;

	@Column(name = "cod_profil_sup", length = 40)
	private String codProfilSup;

	@Column(name = "des_profil_sup", length = 300)
	private String desProfilSup;

	public VAdmTeam clone() throws CloneNotSupportedException {
		return (VAdmTeam) super.clone();
	}
}
