package tn.com.st2i.project.administration.model;

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
@Table(name = "adm_application")

public class AdmApplication implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_application_id_app_seq", name = "adm_application_id_app_seq")
	@GeneratedValue(generator = "adm_application_id_app_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id_app", unique = true, nullable = false)
	private Long idApp;

	@Column(name = "cod_app", nullable = false, length = 40)
	private String codApp;

	@Column(name = "des_app", nullable = false, length = 300)
	private String desApp;

	@Column(name = "des_app_ar", nullable = false, length = 300)
	private String desAppAr;

	public AdmApplication clone() throws CloneNotSupportedException {
		return (AdmApplication) super.clone();
	}
}
