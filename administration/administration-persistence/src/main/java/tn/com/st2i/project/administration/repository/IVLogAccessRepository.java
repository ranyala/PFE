package tn.com.st2i.project.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.com.st2i.project.view.model.VAdmLogAcces;

@Repository
public interface IVLogAccessRepository extends JpaRepository<VAdmLogAcces, Long>{

}
