package tn.com.st2i.project.administration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "adm_log_acces")

public class AdmLogAccess implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_log_acces_id_log_seq", name = "adm_log_acces_id_log_seq")
	@GeneratedValue(generator = "adm_log_acces_id_log_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_log", unique = true, nullable = false)
	private Long idLog;

	@Column(name = "dat_log", length = 13)
	private Date datLog;

	@Column(name = "time_log", length = 15)
	private Date timeLog;

	@Column(name = "session_id")
	private String sessionId;

	@Column(name = "remote_host", length = 40)
	private String remoteHost;

	@Column(name = "remote_addr", length = 40)
	private String remoteAddr;

	@Column(name = "browser", length = 40)
	private String browser;

	@Column(name = "login", nullable = false, length = 200)
	private String login;

	@Column(name = "acces", nullable = false, length = 20)
	private String acces;

	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "nom", length = 200)
	private String nom;

	@Column(name = "id_fonc")
	private Long idFonc;

	@Column(name = "label_fonc", length = 200)
	private String labelFonc;

	@Column(name = "obs", length = 3000)
	private String obs;

	public AdmLogAccess clone() throws CloneNotSupportedException {
		return (AdmLogAccess) super.clone();
	}
}
