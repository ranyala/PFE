package tn.com.st2i.project.administration.view.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.view.repository.IVAdmUserRepository;
import tn.com.st2i.project.administration.view.service.IVAdmUserService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmUser;

import java.util.List;

@Service
public class VAdmUserService implements IVAdmUserService {

    private static final Logger logger = LogManager.getLogger(VAdmUserService.class);

    @Autowired
    private IVAdmUserRepository vAdmUserRepository;

    @Autowired
    private UtilsWs utilsWs;

    @Override
    public SendObject getVAdmUserByIdWs(Long id) {
        try {
            if (id == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());

            VAdmUser admUser = this.getVAdmUserById(id);
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(admUser));
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getVAdmUserByIdWs :: " + e);
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public VAdmUser getVAdmUserById(Long id) {
        try {
            if (id == null)
                return new VAdmUser();
            VAdmUser user = vAdmUserRepository.findVAdmUserById(id);
            return user == null ? new VAdmUser() : user;
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getVAdmUserById :: " + e);
            return new VAdmUser();
        }
    }

    @Override
    public SendObject getVAdmUserByEmailWs(String email) {
        try {
            if (email == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
            VAdmUser admUser = this.getVAdmUserByEmail(email);

            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, admUser);
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getVAdmUserByEmailWs :: " + e);
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    public VAdmUser getVAdmUserByEmail(String email) {
        try {
            if (email == null)
                return new VAdmUser();
            VAdmUser user = vAdmUserRepository.findVAdmUserByMail(email);
            return user == null ? new VAdmUser() : user;
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getVAdmUserByEmail :: " + e);
            return new VAdmUser();
        }
    }

    public List<VAdmUser> getList() {
        try {
            return vAdmUserRepository.findAll();
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getList :: " + e);
            return null;
        }
    }

    @Override
    public SendObject getListAdmUserWs() {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error VAdmUserService in method getListAdmUserWs() :: " + e);
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

}
