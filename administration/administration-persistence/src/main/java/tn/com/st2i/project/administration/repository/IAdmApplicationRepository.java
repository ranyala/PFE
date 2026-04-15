package tn.com.st2i.project.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.st2i.project.administration.model.AdmApplication;

@Repository
public interface IAdmApplicationRepository extends JpaRepository<AdmApplication, Long> {

}
