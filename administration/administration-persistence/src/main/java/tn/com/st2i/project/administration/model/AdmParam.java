package tn.com.st2i.project.administration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "adm_param", uniqueConstraints = @UniqueConstraint(columnNames = "cod_param"))
public class AdmParam implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_param_id_seq", name = "adm_param_id_seq")
	@GeneratedValue(generator = "adm_param_id_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "cod_param", unique = true, length = 50)
	private String codParam;

	@Column(name = "val_param", length = 256)
	private String valParam;

	public AdmParam clone() throws CloneNotSupportedException {
		return (AdmParam) super.clone();
	}
}
