package tn.com.st2i.project.administration.service;

import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.model.AdmUserProfil;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;

@Service
public interface IAdmUserProfilService {

    public List<AdmUserProfil> getList();

    public AdmUserProfil findById(Long id);

    public AdmUserProfil saveOrUpdate(AdmUserProfil entity);

    public Boolean deleteById(Long id);

    public SendObject findAdmUserProfilByIdWs(Long id);

    public SendObject getListAdmUserProfilWs();

    public SendObject saveOrUpdateAdmUserProfilWs(AdmUserProfil entity);

    public SendObject deleteAdmUserProfilByIdWs(Long id);

    public List<AdmUserProfil> getListUserProfilesByIdUser(Long iduser);
}
