package tn.com.st2i.project.administration.service;

import org.springframework.http.ResponseEntity;
import tn.com.st2i.project.administration.model.AdmProfession;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;


public interface IFonctionService {

    public List<AdmProfession> getList();
    public SendObject getListFonctionWs();

    public AdmProfession findById(Long id);

    public SendObject findFonctionIdWs(Long id);
    public Boolean deleteById(Long id);

    public SendObject deleteFonctionByIdWs(Long id);

    SendObject saveOrUpdateFonction(AdmProfession entity);
}


