package tn.com.st2i.project.administration.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.model.AdmLogData;
import tn.com.st2i.project.administration.repository.IAdmLogDataRepository;
import tn.com.st2i.project.administration.service.IAdmLogDataService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;

@Service
public class AdmLogDataService implements IAdmLogDataService {
private static final Logger logger = LogManager.getLogger(AdmLogDataService.class);

    @Autowired
    private IAdmLogDataRepository admLogDataRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private UtilsWs utilsWs;


    @Override
    public List<AdmLogData> getList() {
        try {
            return admLogDataRepository.findAll();
        } catch (Exception e) {
            logger.error("Error AdmLogDataService in method getList :: " + e.toString());
            return null;
        }
    }


    @Override
    public AdmLogData findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new AdmLogData(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (AdmLogData) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error AdmLogDataService in method findById :: " + e.toString());
            return null;
        }
    }



    @Override
    public SendObject findAdmLogDataByIdWs(Long id) {
        try {
            if (id == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
            AdmLogData entity = this.findById(id);
            if (entity == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_NOT_EXISTS_ROW_DATA_BASE, new JSONObject());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(entity));
        } catch (Exception e) {
            logger.error("Error AdmLogDataService in method findAdmLogDataByIdWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject getListAdmLogDataWs() {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error AdmLogDataService in method getListAdmLogDataWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }





















}
