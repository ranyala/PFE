package tn.com.st2i.Etablissement.service;




import org.json.JSONException;
import tn.com.st2i.Etablissement.model.Region;
import tn.com.st2i.Etablissement.model.tool.SendObject;


import java.util.List;

public interface IregionService {
    public List<Region> getList();

    public Region findById(Long id);

    public Boolean deleteById(Long id);

    public SendObject getListRegionWs()throws JSONException;

    public SendObject saveRegionWs(Region entity)throws JSONException ;

    public SendObject deleteRegionByIdWs(Long id)throws JSONException ;

    public SendObject updateRegionWs(Region entity)throws JSONException ;

    SendObject findRegionByIdWs(Long id);

    SendObject saveOrUpdateRegionWs(Region entity);
}