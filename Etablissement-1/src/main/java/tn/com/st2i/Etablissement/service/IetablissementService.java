package tn.com.st2i.Etablissement.service;

import org.json.JSONException;
import tn.com.st2i.Etablissement.model.Etablissement;
import tn.com.st2i.Etablissement.model.tool.SendObject;


import java.util.List;

public interface IetablissementService {

    public List<Etablissement> getList();

    public Etablissement findById(Long id);

    public Boolean deleteById(Long id);

    public SendObject getListetablissementWs()throws JSONException;

    public SendObject saveetablissementWs(Etablissement entity)throws JSONException;

    public SendObject deleteetablissementByIdWs(Long id)throws JSONException;

    public SendObject updateetablissementWs(Etablissement entity)throws JSONException;

    SendObject findEtablissementByIdWs(Long id);

    SendObject saveOrUpdateEtablissementWs(Etablissement entity);
}
