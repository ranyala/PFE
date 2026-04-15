package tn.com.st2i.project.administration.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.com.st2i.project.administration.model.AdmProfil;

@Repository
public interface IAdmProfilRepository extends JpaRepository<AdmProfil, Long> {

	


	@Query(value = "SELECT * FROM  administration.adm_profil a where EXISTS ( SELECT 1"
			+ "FROM administration.adm_utilisateur_profil b WHERE   a.id = b.id_adm_profil"
			+ "AND b.id_adm_utilisateur = :idUser)", nativeQuery = true)
	List<AdmProfil> getListProfilesByIdUser(@Param("idUser") Long idUser);
	
	
	 @Query  (value=" select * from administration.adm_profil a  limit :lim offset :offs " ,nativeQuery=true)
	 public List<AdmProfil> getAdmProfilPaginatedrepo(@Param("lim")Long lim ,@Param("offs")Long offs);

	 @Query(value=" select count(*) from administration.adm_profil a" ,nativeQuery=true)
	 public Long getCountP();
	 
	 @Query(value = " select * from administration.adm_profil a where a.dt_ajout IN(:dt_ajoutList) ", nativeQuery = true)
	 public Set<AdmProfil> findListByListId( @Param("dt_ajoutList") Set<Long> idList);


	 @Query("SELECT COUNT(p) = 0 FROM AdmProfil p WHERE UPPER(p.code) = UPPER(:code)")
	    boolean isCodeUnique(@Param("code") String code);

		
		
	
}