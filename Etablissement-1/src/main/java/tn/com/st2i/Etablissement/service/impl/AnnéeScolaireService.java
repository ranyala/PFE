package tn.com.st2i.Etablissement.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.Etablissement.model.AnnéeScolaire;
import tn.com.st2i.Etablissement.model.tool.SendObject;
import tn.com.st2i.Etablissement.repository.IannéeScolaireRepository;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.IannéeScolaireService;
import tn.com.st2i.Etablissement.tools.ConstanteService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;
import tn.com.st2i.Etablissement.tools.UtilsWs;


import java.util.List;
import java.util.Optional;
@Service
public class AnnéeScolaireService implements IannéeScolaireService {

    private static final Logger logger = LogManager.getLogger(AnnéeScolaireService.class);

    @Autowired
    private IannéeScolaireRepository AnnéeScolaireRepository;

    @Autowired
    private ICommonService commonService ;

    @Autowired
    private UtilsWs utilsWs;
    @Override
    public List<AnnéeScolaire> getList() {
        try {
            return AnnéeScolaireRepository.findAll();
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public AnnéeScolaire findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new AnnéeScolaire(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (AnnéeScolaire) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method findById :: " + e.toString());
            return null;
        }
    }



    @Override
    public Boolean deleteById(Long id) {
        try {
            if (id == null)
                return false;
            AnnéeScolaireRepository.delete(this.findById(id));
            return true;
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method deleteById :: " + e.toString());
            return false;
        }
    }

    @Override
    public SendObject getListannéeScolaireWs() throws JSONException {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method getListClasseWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject saveannéeScolaireWs(AnnéeScolaire entity) throws JSONException{
        try {

            //  boolean isNameUnique = annéeScolaireRepository.isNameUnique(entity.getName());

            // if (!isNameUnique) {
            //   return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            //}
            entity.setAnnéeDébut(entity.getAnnéeDébut());
            entity.setAnnéeFin(entity.getAnnéeFin());


            AnnéeScolaire savedEntity = AnnéeScolaireRepository.save(entity);


            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method saveOrUpdateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }


    @Override

    public SendObject deleteannéeScolaireByIdWs(Long id) throws JSONException {
        try {


            Boolean resultDelete = this.deleteById(id);

            if (!resultDelete) {
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
            }

            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method deleteclasseByIdWs " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject updateannéeScolaireWs(AnnéeScolaire entity) throws JSONException{

        try {
            Optional<AnnéeScolaire> annéeScolaire = AnnéeScolaireRepository.findById(entity.getId());
            if (annéeScolaire.isPresent()) {
                AnnéeScolaire existingEntity = annéeScolaire.get();
                existingEntity.setAnnéeDébut(entity.getAnnéeDébut());
                existingEntity.setAnnéeFin(entity.getAnnéeFin());



                AnnéeScolaire updatedEntity = AnnéeScolaireRepository.save(existingEntity);



                return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
            } else {
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            }
        } catch (Exception e) {
            logger.error("Error annéeScolaireService in method updateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject findAnnéeScolaireByIdWs(Long id) {
        return null;
    }

    @Override
    public SendObject saveOrUpdateAnnéeScolaireWs(AnnéeScolaire entity) {
        return null;
    }
}





