package tn.com.st2i.project.view.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "v_adm_fonc_profil")
public class VAdmFoncProfil implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_fonc_profil")
	private Long idFoncProfil;

	@Column(name = "id_fonc")
	private Long idFonc;

	@Column(name = "id_profil")
	private Long idProfil;

	@Column(name = "f_editer", precision = 1, scale = 0)
	private Integer FEditer;

	@Column(name = "f_valider", precision = 1, scale = 0)
	private Integer FValider;

	@Column(name = "id_app_profil")
	private Long idAppProfil;

	@Column(name = "cod_profil", length = 40)
	private String codProfil;

	@Column(name = "des_profil", length = 300)
	private String desProfil;

	@Column(name = "des_profil_ar", length = 300)
	private String desProfilAr;

	@Column(name = "f_actif", precision = 1, scale = 0)
	private Integer FActif;

	@Column(name = "des_fr_actif", length = 512)
	private String desFrActif;

	@Column(name = "des_ar_actif", length = 512)
	private String desArActif;

	@Column(name = "dat_creat", length = 13)
	private Date datCreat;

	@Column(name = "dat_der_modif", length = 13)
	private Date datDerModif;

	@Column(name = "cod_app", length = 40)
	private String codApp;

	@Column(name = "des_app", length = 300)
	private String desApp;

	@Column(name = "des_app_ar", length = 300)
	private String desAppAr;

	@Column(name = "id_parent")
	private Long idParent;

	@Column(name = "id_app_fonc")
	private Long idAppFonc;

	@Column(name = "cod", length = 40)
	private String cod;

	@Column(name = "label", length = 300)
	private String label;

	@Column(name = "action", length = 300)
	private String action;

	@Column(name = "icon", length = 300)
	private String icon;

	@Column(name = "f_aff_menu")
	private Long FAffMenu;

	@Column(name = "f_aff_etat")
	private Long FAffEtat;

	@Column(name = "f_admin")
	private Long FAdmin;

	@Column(name = "des_fonc", length = 300)
	private String desFonc;

	@Column(name = "des_fonc_ar", length = 300)
	private String desFoncAr;

	public VAdmFoncProfil clone() throws CloneNotSupportedException {
		return (VAdmFoncProfil) super.clone();
	}
}
