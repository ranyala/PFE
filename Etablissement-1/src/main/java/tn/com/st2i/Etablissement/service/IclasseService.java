package tn.com.st2i.Etablissement.service;

import org.json.JSONException;
import tn.com.st2i.Etablissement.model.Classe;
import tn.com.st2i.Etablissement.model.tool.SendObject;


import java.util.List;

public interface IclasseService {


    public List<Classe> getList();

    public Classe findById(Long id);

    public Boolean deleteById(Long id);

    public SendObject getListClasseWs()throws JSONException;

    public SendObject saveClasseWs(Classe entity)throws JSONException;

    public SendObject deleteClasseByIdWs(Long id)throws JSONException;

    public SendObject updateClasseWs(Classe entity)throws JSONException;

    public Classe saveOrUpdate(Classe entity) throws JSONException ;
    public SendObject saveOrUpdateClasseWs(Classe entity) throws JSONException;

    public SendObject getClassePaginated(Long lim, Long page) throws JSONException;
}
