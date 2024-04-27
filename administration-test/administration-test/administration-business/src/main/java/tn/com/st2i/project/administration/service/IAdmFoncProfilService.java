package tn.com.st2i.project.administration.service;
import java.util.List;

import tn.com.st2i.project.administration.model.AdmFoncProfil;
import tn.com.st2i.project.tools.model.SendObject;

public interface IAdmFoncProfilService {
	public List<AdmFoncProfil> getList();

	public AdmFoncProfil findById(Long id);

	public AdmFoncProfil saveOrUpdate(AdmFoncProfil entity);

	public Boolean deleteById(Long id);

	public SendObject findAdmFoncProfilByIdWs(Long id);

	public SendObject getListAdmFoncProfilWs();

	public SendObject saveOrUpdateAdmFoncProfilWs(AdmFoncProfil entity);

	public SendObject deleteAdmFoncProfilByIdWs(Long id);
	
	List<AdmFoncProfil> getListAdmFoncProfilByIdProfil(Long idProfil);

}
