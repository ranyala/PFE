package tn.com.st2i.project.view.model;

import java.sql.Timestamp;
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
@Table(name = "v_adm_log_access")
public class VAdmLogAcces implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "id_user", length = 200)
	private Long idUser;

	@Column(name = "mail", length = 60)
	private String mail;

	@Column(name = "user_name", length = 300)
	private String userName;

	@Column(name = "date_auth", length = 13)
	private Timestamp dateAuth;

	@Column(name = "ip_address", length = 255)
	private String ipAdresse;


	@Column(name = "des_fr", length = 300)
	private String desFr;

	@Column(name = "des_en", length = 300)
	private String desEn;

	@Column(name = "code_access", length = 50)
	private String code_access;

	@Column(name = "code_acces_fr", length = 100)
	private String code_access_fr;

	@Column(name = "code_acces_en", length = 100)
	private String code_access_en;





	public VAdmLogAcces clone() throws CloneNotSupportedException {
		return (VAdmLogAcces) super.clone();
	}
}
