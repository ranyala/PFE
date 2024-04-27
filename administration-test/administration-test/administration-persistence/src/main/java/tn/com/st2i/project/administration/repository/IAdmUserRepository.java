package tn.com.st2i.project.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.com.st2i.project.administration.model.AdmUser;

@Repository
public interface IAdmUserRepository extends JpaRepository<AdmUser, Long> {

    @Query("select case when count(u) > 0 then false else true end from AdmUser u where u.mail=:mail")
    public Boolean uniqueAdmUserByEmail(@Param("mail") String mail);

    @Query("select case when count(u) > 0 then false else true end from AdmUser u where u.code=:code ")
    public Boolean uniqueAdmUserByCode(@Param("code") String code);
    
    @Query("select u from AdmUser u where u.idCmsPaymentAgency=:idCmsPaymentAgency")
    public List<AdmUser> getAgentsByIdCmsPaymentAgency(@Param("idCmsPaymentAgency") Long idCmsPaymentAgency);
}
