package tn.com.st2i.project.administration.service;
import tn.com.st2i.project.administration.model.AdmProfil;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;

public interface IAdmProfilService {
	
	public List<AdmProfil> getList();

	public AdmProfil findById(Long id);


	public Boolean deleteById(Long id);

	public SendObject getListAdmProfilWs();

	public SendObject saveAdmProfilWs(AdmProfil entity);

	public SendObject deleteAdmProfilByIdWs(Long id);
	
	public SendObject updateAdmProfilWs(AdmProfil entity);

	

}
