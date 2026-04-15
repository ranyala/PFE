package tn.com.st2i.project.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.com.st2i.project.administration.model.AdmFonc;


@Repository
public interface IAdmFoncRepository extends JpaRepository<AdmFonc, Long> {

	 @Query(value = "SELECT * FROM adm_fonc a WHERE EXISTS(SELECT 1 FROM adm_fonc_profil b WHERE a.id=b.id_fonc AND b.id_profil IN(:idProfil)) ORDER BY a.code", nativeQuery = true)
	    List<AdmFonc> getMenusForIdProfil(@Param("idProfil") List<Long> idProfil);
	 
	 @Query(value = "SELECT * FROM adm_fonc ORDER BY id", nativeQuery = true)
	    List<AdmFonc> getAllMenus();
	 
	 
	 
	 
	 
	 
	 @Query(value = "SELECT * FROM adm_fonc a WHERE EXISTS(SELECT 1 FROM adm_fonc_profil b WHERE a.id=b.id_fonc AND b.id_profil = :idProfil) ORDER BY a.id", nativeQuery = true)
	    List<AdmFonc> getAllMenusCheck(@Param("idProfil") Long idProfil);
}
