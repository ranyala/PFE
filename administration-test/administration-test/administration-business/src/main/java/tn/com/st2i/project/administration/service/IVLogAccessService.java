package tn.com.st2i.project.administration.service;

import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmLogAcces;

import java.util.List;

public interface IVLogAccessService {
    public List<VAdmLogAcces> getList();

    public VAdmLogAcces findById(Long id);



    public SendObject findVLogAccessByIdWs(Long id);




}
