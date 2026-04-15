package tn.com.st2i.project.administration.service;

import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmLogData;

import java.util.List;

public interface IVLogDataService {

    public List<VAdmLogData> getList();

    public VAdmLogData findById(Long id);



    public SendObject findVLogDataByIdWs(Long id);

}
