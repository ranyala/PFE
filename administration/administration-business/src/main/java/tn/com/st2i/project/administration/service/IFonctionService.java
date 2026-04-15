package tn.com.st2i.project.administration.service;

import tn.com.st2i.project.administration.model.AdmFonc;
import tn.com.st2i.project.administration.model.AdmProfession;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;


public interface IFonctionService {

    public List<AdmFonc> getList();
    public SendObject getListFonctionWs();

    public AdmFonc findById(Long id);

    public SendObject findFonctionIdWs(Long id);
    public Boolean deleteById(Long id);

    public SendObject deleteFonctionByIdWs(Long id);

    SendObject saveOrUpdateFonction(AdmFonc entity);
}


