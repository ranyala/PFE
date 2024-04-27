package tn.com.st2i.project.administration.service.impl;
import tn.com.st2i.project.administration.service.IAdmProfilService;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.st2i.project.administration.model.AdmFoncProfil;
import tn.com.st2i.project.administration.model.AdmProfil;
import tn.com.st2i.project.administration.repository.IAdmFoncProfilRepository;
import tn.com.st2i.project.administration.repository.IAdmProfilRepository;
import tn.com.st2i.project.administration.service.IAdmFoncProfilService;
import tn.com.st2i.project.administration.service.IAdmProfilService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.ArrayList;
@Service
public class AdmProfilService implements IAdmProfilService {
	private static final Logger logger = LogManager.getLogger(AdmProfilService.class);

	@Autowired
	private IAdmProfilRepository admProfilRepository;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private UtilsWs utilsWs;

	@Autowired
	private IAdmFoncProfilService admFoncProfilService;
	
	@Autowired
	private IAdmFoncProfilRepository admFoncProfilRepository;


	@Override
	public List<AdmProfil> getList() {
		try {
			return admProfilRepository.findAll();
		} catch (Exception e) {
			logger.error("Error AdmProfilService in method getList :: " + e.toString());
			return null;
		}
	}

	@Override
	public AdmProfil findById(Long id) {
		try {
			SendObject sendObject = commonService.getObjectById(new AdmProfil(), id.toString(), false);
			if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return (AdmProfil) sendObject.getPayload();
			return null;
		} catch (Exception e) {
			logger.error("Error AdmProfilService in method findById :: " + e.toString());
			return null;
		}
	}



	@Override
	public Boolean deleteById(Long id) {
		try {
			if (id == null)
				return false;
			admProfilRepository.delete(this.findById(id));
			return true;
		} catch (Exception e) {
			logger.error("Error AdmProfilService in method deleteById :: " + e.toString());
			return false;
		}
	}

	@Override
	public SendObject getListAdmProfilWs() {
		try {
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
		} catch (Exception e) {
			logger.error("Error AdmProfilService in method getListAdmProfilWs() :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject saveAdmProfilWs(AdmProfil entity) {
		 try {
		      
		        boolean isCodeUnique = admProfilRepository.isCodeUnique(entity.getCode());
		       
		        if (!isCodeUnique) {
		            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
		        }
		        entity.setDateCreate(new Date());
		        entity.setDateUpdate(new Date());
	        AdmProfil savedEntity = admProfilRepository.save(entity);
	        List<Long> listAdmFoncIds = entity.getListAdmFoncIds();
	        
	        List<AdmFoncProfil> admFoncProfils = new ArrayList<>(); 
	        
	        for (Long menuId : listAdmFoncIds) {
	            AdmFoncProfil admFoncProfil = new AdmFoncProfil();
	            admFoncProfil.setIdProfil(savedEntity.getId());
	            admFoncProfil.setIdFonc(menuId);
	            admFoncProfils.add(admFoncProfil); 
	        }

	        admFoncProfilRepository.saveAll(admFoncProfils); 
	        
	        return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
	    } catch (Exception e) {
	        logger.error("Error AdmProfilService in method saveOrUpdateAdmProfilWs :: " + e.toString());
	        return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
	    }
	}


	@Override
	
	public SendObject deleteAdmProfilByIdWs(Long id) {
	    try {
	        
	        admFoncProfilRepository.deleteByIdAdmProfil(id);

	        
	        Boolean resultDelete = this.deleteById(id);

	        if (!resultDelete) {
	            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
	        }

	        return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
	    } catch (Exception e) {
	        logger.error("Error AdmProfilService in method deleteAdmProfilByIdWs " + e.toString());
	        return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
	    }
	}



	@Override
	public SendObject updateAdmProfilWs(AdmProfil entity) {
	 
			try {
	        Optional<AdmProfil> profil = admProfilRepository.findById(entity.getId());
	        if (profil.isPresent()) {
	            AdmProfil existingEntity = profil.get();
	            existingEntity.setCode(entity.getCode());
	            existingEntity.setDesFr(entity.getDesFr());
	            existingEntity.setDesEn(entity.getDesEn());
	            existingEntity.setIsActive(entity.getIsActive());
	            existingEntity.setDateUpdate(new Date());
	            
	            

	            AdmProfil updatedEntity = admProfilRepository.save(existingEntity);

	            List<Long> listAdmFoncIds = entity.getListAdmFoncIds();
	            List<AdmFoncProfil> admFoncProfils = new ArrayList<>();

	            admFoncProfilRepository.deleteByIdAdmProfil(updatedEntity.getId());
	            for (Long menuId : listAdmFoncIds) {
	                AdmFoncProfil admFoncProfil = new AdmFoncProfil();
	                admFoncProfil.setIdProfil(updatedEntity.getId());
	                admFoncProfil.setIdFonc(menuId);
	                admFoncProfils.add(admFoncProfil);
	            }

	            admFoncProfilRepository.saveAll(admFoncProfils);

	            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
	        } else {
	            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
	        }
	    } catch (Exception e) {
	        logger.error("Error AdmProfilService in method updateAdmProfilWs :: " + e.toString());
	        return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
	    }
	}






}
