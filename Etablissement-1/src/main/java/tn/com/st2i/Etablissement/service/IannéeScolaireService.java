package tn.com.st2i.Etablissement.service;

import org.json.JSONException;
import tn.com.st2i.Etablissement.model.AnnéeScolaire;
import tn.com.st2i.Etablissement.model.tool.SendObject;



import java.util.List;

public interface IannéeScolaireService {
    public List<AnnéeScolaire> getList();

    public AnnéeScolaire findById(Long id);

    public Boolean deleteById(Long id);

    public SendObject getListannéeScolaireWs()throws JSONException;

    public SendObject saveannéeScolaireWs(AnnéeScolaire entity)throws JSONException;

    public SendObject deleteannéeScolaireByIdWs(Long id)throws JSONException;

    public SendObject updateannéeScolaireWs(AnnéeScolaire entity)throws JSONException;

    SendObject findAnnéeScolaireByIdWs(Long id);

    SendObject saveOrUpdateAnnéeScolaireWs(AnnéeScolaire entity);
}
