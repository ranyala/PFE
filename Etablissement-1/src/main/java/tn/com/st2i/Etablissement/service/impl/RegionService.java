package tn.com.st2i.Etablissement.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.st2i.Etablissement.model.Region;
import tn.com.st2i.Etablissement.model.tool.SendObject;
import tn.com.st2i.Etablissement.repository.IregionRepository;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.IregionService;
import tn.com.st2i.Etablissement.tools.ConstanteService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;
import tn.com.st2i.Etablissement.tools.UtilsWs;


import java.util.List;
import java.util.Optional;
@Service
public class RegionService implements IregionService {
    private static final Logger logger = LogManager.getLogger(RegionService.class);

    @Autowired
    private IregionRepository RegionRepository;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private UtilsWs utilsWs;


    @Override
    public List<Region> getList() {
        try {
            return RegionRepository.findAll();
        } catch (Exception e) {
            logger.error("Error regionService in method getList :: " + e.toString());
            return null;
        }
    }

    @Override
    public Region findById(Long id) {
        try {
            SendObject sendObject = commonService.getObjectById(new Region(), id.toString(), false);
            if (sendObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return (Region) sendObject.getPayload();
            return null;
        } catch (Exception e) {
            logger.error("Error regionService in method findById :: " + e.toString());
            return null;
        }
    }



    @Override
    public Boolean deleteById(Long id) {
        try {
            if (id == null)
                return false;
            RegionRepository.delete(this.findById(id));
            return true;
        } catch (Exception e) {
            logger.error("Error regionService in method deleteById :: " + e.toString());
            return false;
        }
    }

    @Override
    public SendObject getListRegionWs() throws JSONException {
        try {
            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONArray(this.getList()));
        } catch (Exception e) {
            logger.error("Error regionService in method getListClasseWs() :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject saveRegionWs(Region entity) throws JSONException{
        try {

            //  boolean isNameUnique = regionRepository.isNameUnique(entity.getName());

            // if (!isNameUnique) {
            //   return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            //}
            entity.setName(entity.getName());

            Region savedEntity = RegionRepository.save(entity);


            return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error regionService in method saveOrUpdateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }


    @Override

    public SendObject deleteRegionByIdWs(Long id) throws JSONException {
        try {


            Boolean resultDelete = this.deleteById(id);

            if (!resultDelete) {
                return utilsWs.resultWs(ConstanteService._CODE_SERVICE_ERROR_DELETE_ROW, new JSONObject());
            }

            return utilsWs.resultWs(ConstanteService._CODE_SERVICE_SUCCESS, new JSONObject());
        } catch (Exception e) {
            logger.error("Error regionService in method deleteclasseByIdWs " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject updateRegionWs(Region entity) throws JSONException{

        try {
            Optional<Region> Region = RegionRepository.findById(entity.getId());
            if (Region.isPresent()) {
                Region existingEntity = Region.get();
                existingEntity.setName(entity.getName());


                Region updatedEntity = RegionRepository.save(existingEntity);



                return utilsWs.resultWs(ConstanteWs._CODE_WS_SUCCESS, new JSONObject());
            } else {
                return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
            }
        } catch (Exception e) {
            logger.error("Error regionService in method updateclasseWs :: " + e.toString());
            return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
        }
    }

    @Override
    public SendObject findRegionByIdWs(Long id) {
        return null;
    }

    @Override
    public SendObject saveOrUpdateRegionWs(Region entity) {
        return null;
    }
}
