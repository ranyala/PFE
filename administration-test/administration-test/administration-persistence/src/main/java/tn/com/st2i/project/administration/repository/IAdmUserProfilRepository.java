package tn.com.st2i.project.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.com.st2i.project.administration.model.AdmUserProfil;

@Repository
public interface IAdmUserProfilRepository extends JpaRepository<AdmUserProfil, Long> {

	@Query(value = "SELECT * FROM adm_user_profil a WHERE a.id_user =:idUser", nativeQuery = true)
	List<AdmUserProfil> getListUserProfilesByIdUser(@Param("idUser") Long idUser);

}
