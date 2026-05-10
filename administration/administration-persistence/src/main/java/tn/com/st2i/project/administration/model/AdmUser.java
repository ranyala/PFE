package tn.com.st2i.project.administration.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "adm_user")
public class AdmUser implements java.io.Serializable, Cloneable {

	private transient static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, initialValue = 1,
			sequenceName = "adm_utilisateur_id_user_seq", name = "adm_utilisateur_id_user_seq")
	@GeneratedValue(generator = "adm_utilisateur_id_user_seq", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "pwd", nullable = false, length = 100)
	private String pwd;

	@Column(name = "mail", nullable = false, length = 60)
	private String mail;

	@Column(name = "cin", length = 40)
	private String cin;

	@Column(name = "user_name", nullable = false, length = 300)
	private String username;
    @Column(name = "name_ar", length = 300)
    private String nameAr;
	@Column(name = "id_sex", nullable = false)
	private Integer idSex;

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	@Column(name = "f_expire", nullable = false)
	private Integer fExpire;

	@Column(name = "f_susp", nullable = false)
	private Integer fSusp;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "email_code")
	private String emailcode;

	@Column(name = "date_birth")
	private Date dateBirth;

	@Column(name = "date_last_con")
	private Date dateLastCon;

	@Column(name = "date_expire")
	private Date dateExpire;

	@Column(name = "date_susp_start")
	private Date dateSuspStart;

	@Column(name = "date_susp_end")
	private Date dateSuspEnd;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create")
	private Date dateCreate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update")
	private Date dateUpdate;

	// ============================================
	// LIAISON ÉTABLISSEMENT / CRE
	// ============================================

	@Column(name = "type_agent", length = 20)
	private String typeAgent;
	// "ENSEIGNANT"    → idEtablissement renseigné
	// "ADMINISTRATIF" → idCre renseigné
	// "DIRECTEUR"     → idEtablissement renseigné

	@Column(name = "id_etablissement")
	private Long idEtablissement; // FK vers MS-Etablissement

	@Column(name = "id_cre")
	private Long idCre; // FK vers MS-Etablissement (table CRE)

	// ============================================

	@Transient
	private List<Long> profils;

	public AdmUser clone() throws CloneNotSupportedException {
		return (AdmUser) super.clone();
	}
}