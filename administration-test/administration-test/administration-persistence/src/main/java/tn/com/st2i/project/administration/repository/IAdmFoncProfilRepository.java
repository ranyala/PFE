package tn.com.st2i.project.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.com.st2i.project.administration.model.AdmFoncProfil;

@Repository
public interface IAdmFoncProfilRepository extends JpaRepository<AdmFoncProfil, Long> {

	@Query("select a from AdmFoncProfil a where a.idProfil=:idProfil")
	List<AdmFoncProfil> getListAdmFoncProfilByIdProfil(@Param(value = "idProfil") Long idProfil);

	@Transactional
	@Modifying
	@Query("delete from AdmFoncProfil a where a.idProfil=:idProfil")
	void deleteByIdAdmProfil(@Param(value = "idProfil") Long idProfil);
	
	 @Modifying
	    @Query("DELETE FROM AdmFoncProfil afp WHERE afp.idProfil = :idProfil")
	    void deleteByProfilId(@Param("idProfil") Long idProfil);
}
