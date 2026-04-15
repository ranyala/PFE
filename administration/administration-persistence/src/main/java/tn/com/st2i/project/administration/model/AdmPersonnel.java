package tn.com.st2i.project.administration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "adm_personnel", schema = "administration")

public class AdmPersonnel implements java.io.Serializable, Cloneable {
	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "administration.seq_adm_personnel", name = "administration.seq_adm_personnel")
	@GeneratedValue(generator = "administration.seq_adm_personnel", strategy = GenerationType.SEQUENCE)
	@Id

	@Column(name = "id", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;

	@Column(name = "id_nm_sexe")
	private Long idNmSexe;

	@Column(name = "id_rc_organisme")
	private Long idRcOrganisme;

	@Column(name = "id_rc_organisme_parent", length = 50)
	private Long idRcOrganismeParent;


	@Column(name = "prenom", nullable = false, length = 50)
	private String prenom;

	@Column(name = "nom", nullable = false, length = 50)
	private String nom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dt_naissance", length = 13)
	private Date dtNaissance;

	@Column(name = "matricule", length = 50)
	private String matricule;

	@Column(name = "mail", nullable = false, length = 50)
	private String mail;

	@Column(name = "num_tel", precision = 22, scale = 0)
	private Long numTel;

	@Column(name = "cin", length = 8)
	private String cin;

	@Column(name = "dt_maj", nullable = false, length = 13)
	private Date dtMaj;
	

	
	@Transient
	private List<Long> listEntities ;
	

	public AdmPersonnel clone() throws CloneNotSupportedException {
		return (AdmPersonnel) super.clone();
	}
}
