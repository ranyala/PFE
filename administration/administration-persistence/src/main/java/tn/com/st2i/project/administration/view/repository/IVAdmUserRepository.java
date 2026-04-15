package tn.com.st2i.project.administration.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.com.st2i.project.view.model.VAdmUser;

@Repository
public interface IVAdmUserRepository extends JpaRepository<VAdmUser, Long> {

	@Query("select v from VAdmUser v where v.id=:id ")
	public VAdmUser findVAdmUserById(@Param("id") Long id);

	@Query("select v from VAdmUser v where v.mail=:mail ")
	public VAdmUser findVAdmUserByMail(@Param("mail") String mail);

}
