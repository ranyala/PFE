package tn.com.st2i.project.administration.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "adm_utilisateur_id_user_seq", name = "adm_utilisateur_id_user_seq")
    @GeneratedValue(generator = "adm_utilisateur_id_user_seq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_profes")
    private Long idProfes;

	@Column(name = "pwd", nullable = false, length = 100)
	private String pwd;

	@Column(name = "mail", nullable = false, length = 60)
	private String mail;

	@Column(name = "cin", length = 40)
	private String cin;

	@Column(name = "email_code")
	private String emailcode;

	@Column(name = "user_name", nullable = false, length = 300)
	private String username;

	@Column(name = "date_birth", length = 13)
	private Date dateBirth;

	@Column(name = "id_sex", nullable = false)
	private Integer idSex;

	@Column(name = "date_last_con", length = 13)
	private Date dateLastCon;

	@Column(name = "is_active", nullable = false, precision = 1, scale = 0)
	private Integer isActive;

	@Column(name = "f_expire", nullable = false, precision = 1, scale = 0)
	private Integer fExpire;

	@Column(name = "f_susp", nullable = false, precision = 1, scale = 0)
	private Integer fSusp;

	@Column(name = "date_expire", length = 13)
	private Date dateExpire;

	@Column(name = "date_last_update", length = 13)
	private Date dateLastUpdate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", length = 13)
	private Date dateCreate;

	@Column(name = "date_susp_start", length = 13)
	private Date dateSuspStart;

	@Column(name = "date_susp_end", length = 13)
	private Date dateSuspEnd;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", length = 13)
	private Date dateUpdate;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "id_district")
    private Long idDistrict;

    @Column(name = "id_cms_payment_agency")
    private Long idCmsPaymentAgency;

    @Column(name = "id_town")
    private Long idTown;

	@Transient
	private List<Long> profils;

    public AdmUser clone() throws CloneNotSupportedException {
        return (AdmUser) super.clone();
    }
}
