package tn.com.st2i.project.administration.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.model.AdmUser;
import tn.com.st2i.project.administration.model.AdmUserProfil;
import tn.com.st2i.project.administration.repository.IAdmUserProfilRepository;
import tn.com.st2i.project.administration.repository.IAdmUserRepository;
import tn.com.st2i.project.administration.service.IAdmUserProfilService;
import tn.com.st2i.project.administration.service.IAdmUserService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdmUserService implements IAdmUserService {

    private static final Logger logger = LogManager.getLogger(AdmUserService.class);

    @Autowired
    private IAdmUserRepository admUserRepository;

    @Autowired
    private IAdmUserProfilRepository admUserProfilRepository;

    @Autowired
    private IAdmUserProfilService admUserProfilService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private UtilsWs utilsWs;


    @Override
    public List<AdmUser> getList() {
        try {
            return admUserRepository.findAll();
        } catch (Exception e) {
            logger.error("Error AdmUserService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public AdmUser findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new AdmUser(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (AdmUser) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error AdmUserService in method findById :: " + e.toString());
            return null;
        }
    }

    @Override
    public AdmUser saveOrUpdate(AdmUser entity) {
        try {
            // Hash the user's password before saving
            if (entity.getPwd() != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hashedPassword = encoder.encode(entity.getPwd());
                entity.setPwd(hashedPassword);
            }
            entity.setDateUpdate(new Date());
            AdmUser result = admUserRepository.save(entity);
            // Save the profile list
            if (result.getId() != null) {
                for (Long id_profil : entity.getProfils()) {
                    AdmUserProfil profileEntity = new AdmUserProfil(entity.getId(), id_profil);
                    try {
                        admUserProfilRepository.save(profileEntity);
                    } catch (Exception e) {
                        logger.error("Error AdmUserService in method saveOrUpdate :: " + e.toString());
                        return null;
                    }
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("Error AdmUserService in method saveOrUpdate :: " + e.toString());
            return null;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            if (id == null)
                return false;
            admUserRepository.delete(this.findById(id));
            return true;
        } catch (Exception e) {
            logger.error("Error AdmUserService in method deleteById :: " + e.toString());
            return false;
        }
    }

    @Override
    public SendObject findAdmUserByIdWs(Long id) {
        try {
            if (id == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
            AdmUser entity = this.findById(id);
            if (entity == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_NOT_EXISTS_ROW_DATA_BASE, new JSONObject());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(entity));
        } catch (Exception e) {
            logger.error("Error AdmUserService in method findAdmUserByIdWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject getListAdmUserWs() {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error AdmUserService in method getListAdmUserWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject saveOrUpdateAdmUserWs(AdmUser entity) {
        try {
            if (entity.getId() == null) {
                // Check unique when adding new user
                if (!admUserRepository.uniqueAdmUserByCode(entity.getCode()))
                    return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_UNIQUE_CODE, new JSONObject());

                if (!admUserRepository.uniqueAdmUserByEmail(entity.getMail()))
                    return utilsWs.resultWs(ConstanteService._CODE_SERVICE_LOGIN_EXISTS, new JSONObject());
            } else {
                // Check if code has changed before editing
                Optional<AdmUser> user = admUserRepository.findById(entity.getId());
                if (user.isPresent()) {
                    AdmUser foundUser = user.get();
                    if (!foundUser.getCode().equals(entity.getCode())) {
                        // Check if code is unique
                        if (!admUserRepository.uniqueAdmUserByCode(entity.getCode())) {
                            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_UNIQUE_CODE, new JSONObject());
                        }
                    }
                    if (!foundUser.getMail().equals(entity.getMail())) {
                        // Check if unique login
                        if (!admUserRepository.uniqueAdmUserByEmail(entity.getMail()))
                            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_LOGIN_EXISTS, new JSONObject());
                    }
                }

            }
            // Remove previous user's profiles
            List<AdmUserProfil> userProfils = admUserProfilRepository.getListUserProfilesByIdUser(entity.getId());
            if (!userProfils.isEmpty()) {
                for (AdmUserProfil profil : userProfils) {
                    Boolean resultDelete = admUserProfilService.deleteById(profil.getId());
                    if (!resultDelete)
                        return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_SAVE_OR_UPDATE, new JSONObject());
                }
            }

            entity = this.saveOrUpdate(entity);
            if (entity == null)
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_SAVE_OR_UPDATE, new JSONObject());
            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject(entity));
        } catch (Exception e) {
            logger.error("Error AdmUserService in method saveOrUpdateAdmUserWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject deleteAdmUserByIdWs(Long id) {
        try {
            // Remove previous user's profiles
            List<AdmUserProfil> userProfils = admUserProfilRepository.getListUserProfilesByIdUser(id);
            if (!userProfils.isEmpty()) {
                for (AdmUserProfil profil : userProfils) {
                    Boolean resultDelete = admUserProfilService.deleteById(profil.getId());
                    if (!resultDelete)
                        return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_SAVE_OR_UPDATE, new JSONObject());
                }
            }

            Boolean resultDelete = this.deleteById(id);
            if (!resultDelete)
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error AdmUserService in method getListAdmUser " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject registerWs(AdmUser user) {
        try {

            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(user));
        } catch (Exception argEx) {
            logger.error("Error AdmUserService in method registerWs :: " + argEx.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject updateUserWs(AdmUser user) {
        try {

            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(user));
        } catch (Exception argEx) {
            logger.error("Error AdmUserService in method updateUserWs :: " + argEx.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

	@Override
	public SendObject getAgentsByIdCmsPaymentAgency(Long idCmsPaymentAgency) {
		 try {

	            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(admUserRepository.getAgentsByIdCmsPaymentAgency(idCmsPaymentAgency)));
	        } catch (Exception argEx) {
	            logger.error("Error AdmUserService in method registerWs :: " + argEx.toString());
	            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
	        }}

}
