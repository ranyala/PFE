package tn.com.st2i.project.administration.service;

import tn.com.st2i.project.administration.model.AdmLogData;
import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;

public interface IAdmLogDataService {


    public List<AdmLogData> getList();

    public AdmLogData findById(Long id);





    public SendObject findAdmLogDataByIdWs(Long id);

    public SendObject getListAdmLogDataWs();


}
