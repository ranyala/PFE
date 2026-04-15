package tn.com.st2i.project.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.com.st2i.project.administration.model.AdmFonc;
import tn.com.st2i.project.administration.model.AdmProfession;

import java.util.List;

@Repository
public interface IAdmProfessionRepository extends JpaRepository<AdmProfession, Long> {


}
