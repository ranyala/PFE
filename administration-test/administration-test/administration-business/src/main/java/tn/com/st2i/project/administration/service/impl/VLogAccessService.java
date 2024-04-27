package tn.com.st2i.project.administration.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.st2i.project.administration.repository.IVLogAccessRepository;
import tn.com.st2i.project.administration.service.IVLogAccessService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmLogAcces;

import java.util.List;

@Service
public class VLogAccessService implements IVLogAccessService {

    private static final Logger logger = LogManager.getLogger(VLogAccessService.class);

    @Autowired
    private IVLogAccessRepository vLogAccessRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private UtilsWs utilsWs;

    @Override
    public List<VAdmLogAcces> getList() {
        try {
            return vLogAccessRepository.findAll();
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public VAdmLogAcces findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new VAdmLogAcces(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (VAdmLogAcces) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method findById :: " + e.toString());
            return null;
        }
    }




    @Override
    public SendObject findVLogAccessByIdWs(Long id) {
        try {
            if (id == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
            VAdmLogAcces entity = this.findById(id);
            if (entity == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_NOT_EXISTS_ROW_DATA_BASE, new JSONObject());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(entity));
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method findVLogAccessByIdWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }





}
