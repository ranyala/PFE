package tn.com.st2i.project.administration.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.com.st2i.project.view.model.VAdmProfil;

@Repository
public interface IVAdmProfilRepository extends JpaRepository<VAdmProfil, Long>  {

}
