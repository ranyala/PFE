package tn.com.st2i.project.view.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.json.JSONObject;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "v_adm_fonc_utilisateur")
public class VAdmFoncUtilisateur implements java.io.Serializable, Cloneable {

	private transient static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column(name = "id_fonc")
	private Long idFonc;

	@Column(name = "id_parent")
	private Long idParent;

	@Column(name = "id_app")
	private Long idApp;

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

	@Column(name = "f_editer", precision = 131089, scale = 0)
	private Long FEditer;

	@Column(name = "f_valider", precision = 131089, scale = 0)
	private Long FValider;

	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "url_acces", length = 1500)
	private String urlAcces;

	public VAdmFoncUtilisateur clone() throws CloneNotSupportedException {
		return (VAdmFoncUtilisateur) super.clone();
	}

	public JSONObject toJSON(VAdmFoncUtilisateur menu) throws Exception {
		JSONObject jo = new JSONObject();

		jo.put("title", menu.getDesFonc() != null ? menu.getDesFonc() : "");
		jo.put("link", menu.getUrlAcces() != null ? menu.getUrlAcces() : "");
		return jo;
	}
}
