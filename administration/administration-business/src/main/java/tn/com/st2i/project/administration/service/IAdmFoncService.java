package tn.com.st2i.project.administration.service;

import tn.com.st2i.project.tools.model.SendObject;

import java.util.List;

public interface IAdmFoncService {
	SendObject getAllMenus();
    SendObject getMenusForIdProfil(List<Long> idProfil);
	SendObject getAllMenusChecked(Long idProfil);

}
