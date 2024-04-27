package tn.com.st2i.Etablissement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.st2i.Etablissement.model.AnnéeScolaire;

@Repository
public interface IannéeScolaireRepository extends JpaRepository<AnnéeScolaire, Long> {
}
