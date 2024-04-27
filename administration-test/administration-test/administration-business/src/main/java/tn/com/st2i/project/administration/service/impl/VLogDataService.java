package tn.com.st2i.project.administration.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.project.administration.repository.IVLogDataRepository;
import tn.com.st2i.project.administration.service.IVLogDataService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmLogData;

import java.util.List;
@Service
public class VLogDataService implements IVLogDataService {
    private static final Logger logger = LogManager.getLogger(VLogAccessService.class);
private IVLogDataRepository vLogDataRepository;
    @Autowired
    private ICommonService commonService;

    @Autowired
    private UtilsWs utilsWs;


    @Override
    public List<VAdmLogData> getList() {
        try {
            return vLogDataRepository.findAll();
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public VAdmLogData findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new VAdmLogData(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (VAdmLogData) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method findById :: " + e.toString());
            return null;
        }
    }

    @Override
    public SendObject findVLogDataByIdWs(Long id) {
        try {
            if (id == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, new JSONObject());
            VAdmLogData entity = this.findById(id);
            if (entity == null)
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_NOT_EXISTS_ROW_DATA_BASE, new JSONObject());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject(entity));
        } catch (Exception e) {
            logger.error("Error VLogAccessService in method findVLogAccessByIdWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }
}
