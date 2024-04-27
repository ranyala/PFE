package tn.com.st2i.Etablissement.service.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.Etablissement.model.Classe;
import tn.com.st2i.Etablissement.model.tool.SendObject;
import tn.com.st2i.Etablissement.repository.IclasseRepository;
import tn.com.st2i.Etablissement.repository.IetablissementRepository;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.IclasseService;
import tn.com.st2i.Etablissement.tools.ConstanteService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;
import tn.com.st2i.Etablissement.tools.UtilsWs;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class ClasseService implements IclasseService {
    private static final Logger logger = LogManager.getLogger(ClasseService.class);
@Autowired
private IetablissementRepository EtablissementRepository;
    @Autowired
    private static IclasseRepository ClasseRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private static UtilsWs utilsWs;

    @Override
    public Classe saveOrUpdate(Classe entity) {
        try {
            return ClasseRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error ClasseService in method saveOrUpdate :: " + e.toString());
            return null;
        }
    }
    @Override
    public  SendObject saveOrUpdateClasseWs(Classe entity) throws JSONException {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS,ClasseRepository.save(entity) );
        } catch (Exception e) {
            logger.error("Error ClasseService in method saveOrUpdateAdmProfilWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }


    @Override
    public List<Classe> getList() {
        try {
            return ClasseRepository.findAll();
        } catch (Exception e) {
            logger.error("Error classeService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public Classe findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new Classe(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (Classe) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error classeService in method findById :: " + e.toString());
            return null;
        }
    }



    @Override
    public Boolean deleteById(Long id) {
        try {
            if (id == null)
                return false;
            ClasseRepository.delete(this.findById(id));
            return true;
        } catch (Exception e) {
            logger.error("Error classeService in method deleteById :: " + e.toString());
            return false;
        }
    }

    @Override
    public SendObject getListClasseWs() throws JSONException {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error classeService in method getListClasseWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject saveClasseWs(Classe entity) throws JSONException{
        try {

          //  boolean isNameUnique = classeRepository.isNameUnique(entity.getName());

           // if (!isNameUnique) {
             //   return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            //}
            entity.setName(entity.getName());
            entity.setNbr(entity.getNbr());
            Classe savedEntity = ClasseRepository.save(entity);


            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error classeService in method saveOrUpdateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }


    @Override

    public SendObject deleteClasseByIdWs(Long id) throws JSONException {
        try {


            Boolean resultDelete = this.deleteById(id);

            if (!resultDelete) {
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
            }

            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error classeService in method deleteclasseByIdWs " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject updateClasseWs(Classe entity) throws JSONException{

        try {
            Optional<Classe> classe = ClasseRepository.findById(entity.getId());
            if (classe.isPresent()) {
                Classe existingEntity = classe.get();
                existingEntity.setName(entity.getName());
                existingEntity.setNbr(entity.getNbr());

                Classe updatedEntity = ClasseRepository.save(existingEntity);



                return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
            } else {
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            }
        } catch (Exception e) {
            logger.error("Error classeService in method updateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public  SendObject getClassePaginated(Long lim, Long page) throws JSONException {

        try {
            Long off = page;

            List<Classe> listparent = ClasseRepository.findClassesPaginated(lim, off);
            //Long count = ClasseRepository.getCountP();

            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONArray(listparent));
        } catch (Exception e) {
            logger.error("Error ClasseService in method getClassePaginated " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }

    }









}
