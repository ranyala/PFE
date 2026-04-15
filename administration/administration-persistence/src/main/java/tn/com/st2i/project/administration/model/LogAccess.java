package tn.com.st2i.project.administration.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "adm_log_access")

public class LogAccess implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "seq_log_access", name = "seq_log_access")
	@GeneratedValue(generator = "seq_log_access", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code_access", length = 13)
	private String codeAccess;

	@Column(name = "date_auth", length = 15)
	private Date dateAuth;

	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "ip_address")
	private String ipAdress;

	@Column(name = "login")
	private String login;

	public LogAccess clone() throws CloneNotSupportedException {
		return (LogAccess) super.clone();
	}
}
