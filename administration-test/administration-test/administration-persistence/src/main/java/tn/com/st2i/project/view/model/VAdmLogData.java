package tn.com.st2i.project.view.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "v_adm_log_data")
public class VAdmLogData implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	@Column(name = "id", length = 200)
	private Long id;

	@Column(name = "id_user", length = 200)
	private Long idUser;


	@Column(name = "mail", length = 60)
	private String mail;

	@Column(name = "user_name", length = 300)
	private String userName;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "date_log")
	private Date dateLog;

	@Column(name = "ip_address", length = 255)
	private String ipAddress;

	@Column(name = "name_service", length = 255)
	private String nameService;

	@Column(name = "uri", length = 255)
	private String uri;

	@Column(name = "http_method", length = 50)
	private String http_method;

	@Column(name = "result_ws_fr", length = 200)
	private String result_ws_fr;

	@Column(name = "result_ws_en", length = 200)
	private String result_ws_en;





	public VAdmLogData clone() throws CloneNotSupportedException {
		return (VAdmLogData) super.clone();
	}
}
