package tn.com.st2i.Etablissement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.com.st2i.Etablissement.model.Classe;

import java.util.List;

@Repository
public interface IclasseRepository extends JpaRepository<Classe, Long> {

    // Remove the incorrect method
    // List<Classe> getClassePaginatedrepo(Long lim, Long off);

    @Query("SELECT c FROM Classe c WHERE c.id BETWEEN ?1 AND ?2")
    List<Classe> findClassesPaginated(Long startId, Long endId);

}