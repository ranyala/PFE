package tn.com.st2i.project.administration.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.model.AdmUser;
import tn.com.st2i.project.tools.model.SendObject;

@Service
public interface IAdmUserService {

	public List<AdmUser> getList();

	public AdmUser findById(Long id);

	public AdmUser saveOrUpdate(AdmUser entity);

	public Boolean deleteById(Long id);

	public SendObject findAdmUserByIdWs(Long id);

	public SendObject getListAdmUserWs();

	public SendObject saveOrUpdateAdmUserWs(AdmUser entity);

	public SendObject deleteAdmUserByIdWs(Long id);

	public SendObject registerWs(AdmUser user) ;

	public SendObject updateUserWs(AdmUser user) ;

	public SendObject getAgentsByIdCmsPaymentAgency(Long idCmsPaymentAgency);


}
