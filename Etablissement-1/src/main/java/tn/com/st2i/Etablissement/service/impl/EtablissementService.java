package tn.com.st2i.Etablissement.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.Etablissement.model.Etablissement;
import tn.com.st2i.Etablissement.model.tool.SendObject;
import tn.com.st2i.Etablissement.repository.IetablissementRepository;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.IetablissementService;
import tn.com.st2i.Etablissement.tools.ConstanteService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;
import tn.com.st2i.Etablissement.tools.UtilsWs;
import tn.com.st2i.Etablissement.repository.IetablissementRepository;


import java.util.List;
import java.util.Optional;
@Service
public class EtablissementService implements IetablissementService {
    private static final Logger logger = LogManager.getLogger(EtablissementService.class);
    @Autowired
    private IetablissementRepository EtablissementRepository;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private UtilsWs utilsWs;


    @Override
    public List<Etablissement> getList() {
        try {
            return EtablissementRepository.findAll();
        } catch (Exception e) {
            logger.error("Error etablissementService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public Etablissement findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new Etablissement(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (Etablissement) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error etablissementService in method findById :: " + e.toString());
            return null;
        }
    }



    @Override
    public Boolean deleteById(Long id) {
        try {
            if (id == null)
                return false;
            EtablissementRepository.delete(this.findById(id));
            return true;
        } catch (Exception e) {
            logger.error("Error etablissementService in method deleteById :: " + e.toString());
            return false;
        }
    }

    @Override
    public SendObject getListetablissementWs() throws JSONException {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error etablissementService in method getListetablissementWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject saveetablissementWs(Etablissement entity) throws JSONException{
        try {

            //  boolean isNameUnique = etablissementRepository.isNameUnique(entity.getName());

            // if (!isNameUnique) {
            //   return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            //}
            entity.setName(entity.getName());
            entity.setAdresse(entity.getAdresse());
            Etablissement savedEntity = EtablissementRepository.save(entity);


            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error etablissementService in method saveOrUpdateetablissementWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }


    @Override

    public SendObject deleteetablissementByIdWs(Long id) throws JSONException {
        try {


            Boolean resultDelete = this.deleteById(id);

            if (!resultDelete) {
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
            }

            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error etablissementService in method deleteetablissementByIdWs " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject updateetablissementWs(Etablissement entity) throws JSONException{

        try {
            Optional<Etablissement> Etablissement = EtablissementRepository.findById(entity.getId());
            if (Etablissement.isPresent()) {
                Etablissement existingEntity = Etablissement.get();
                existingEntity.setName(entity.getName());
                existingEntity.setAdresse(entity.getAdresse());

                Etablissement updatedEntity = EtablissementRepository.save(existingEntity);



                return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
            } else {
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            }
        } catch (Exception e) {
            logger.error("Error etablissementService in method updateetablissementWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject findEtablissementByIdWs(Long id) {
        return null;
    }

    @Override
    public SendObject saveOrUpdateEtablissementWs(Etablissement entity) {
        return null;
    }
}