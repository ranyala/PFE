package tn.com.st2i.project.administration.view.service;

import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmUser;

public interface IVAdmUserService {

    public SendObject getVAdmUserByIdWs(Long id);

    public SendObject getVAdmUserByEmailWs(String email);

    public VAdmUser getVAdmUserById(Long id);

    public SendObject getListAdmUserWs() ;

}
