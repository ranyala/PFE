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
@Table(name = "adm_log_data")

public class AdmLogData implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_log_data_id_log_seq", name = "adm_log_data_id_log_seq")
	@GeneratedValue(generator = "adm_log_data_id_log_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long idLog;

	@Column(name = "date_log", length = 13)
	private Date dateLog;

	@Column(name = "http_method", nullable = false, length = 50)
	private String httpMethod;

	@Column(name = "id_user")
	private Long idAdmUser;

	@Column(name = "uri", nullable = false)
	private String uri;

	@Column(name = "ip_address", length = 40)
	private String ipAddress;

	@Column(name = "result_ws", nullable = false)
	private String resultWs;

	@Column(name = "name_service", nullable = false)
	private String nameService;

	public AdmLogData clone() throws CloneNotSupportedException {
		return (AdmLogData) super.clone();
	}

}
