package tn.com.st2i.project.administration.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.st2i.project.administration.model.AdmFoncProfil;
import tn.com.st2i.project.administration.repository.IAdmFoncProfilRepository;
import tn.com.st2i.project.administration.service.IAdmFoncProfilService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;

@Service
public class AdmFoncProfilService implements IAdmFoncProfilService {

	private static final Logger logger = LogManager.getLogger(AdmFoncProfilService.class);

	@Autowired
	private IAdmFoncProfilRepository admFoncProfilRepository;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private UtilsWs utilsWs;

	@Override
	public List<AdmFoncProfil> getList() {
		try {
			return admFoncProfilRepository.findAll();
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method getList :: " + e.toString());
			return null;
		}
	}

	@Override
	public AdmFoncProfil findById(Long id) {
		try {
			SendObject sendObject = commonService.getObjectById(new AdmFoncProfil(), id.toString(), false);
			if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return (AdmFoncProfil) sendObject.getPayload();
			return null;
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method findById :: " + e.toString());
			return null;
		}
	}

	@Override
	public AdmFoncProfil saveOrUpdate(AdmFoncProfil entity) {
		try {
			return admFoncProfilRepository.save(entity);
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method saveOrUpdate :: " + e.toString());
			return null;
		}
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			if (id == null)
				return false;
			admFoncProfilRepository.delete(this.findById(id));
			return true;
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method deleteById :: " + e.toString());
			return false;
		}
	}

	@Override
	public SendObject findAdmFoncProfilByIdWs(Long id) {
		try {
			if (id == null)
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
			AdmFoncProfil entity = this.findById(id);
			if (entity == null)
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_NOT_EXISTS_ROW_DATA_BASE, new JSONObject());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(entity));
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method findAdmFoncProfilByIdWs :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getListAdmFoncProfilWs() {
		try {
			return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method getListAdmFoncProfilWs() :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject saveOrUpdateAdmFoncProfilWs(AdmFoncProfil entity) {
		try {
			entity = this.saveOrUpdate(entity);
			if (entity == null)
				return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_SAVE_OR_UPDATE, new JSONObject());
			return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject(entity));
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method saveOrUpdateAdmFoncProfilWs :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject deleteAdmFoncProfilByIdWs(Long id) {
		try {
			Boolean resultDelete = this.deleteById(id);
			if (resultDelete == false)
				return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
			return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method getListAdmFoncProfil " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public List<AdmFoncProfil> getListAdmFoncProfilByIdProfil(Long idProfil) {
		try {
			return admFoncProfilRepository.getListAdmFoncProfilByIdProfil(idProfil);
		} catch (Exception e) {
			logger.error("Error AdmFoncProfilService in method getListAdmFoncProfilByIdProfil :: " + e.toString());
			return null;
		}
	}
}

