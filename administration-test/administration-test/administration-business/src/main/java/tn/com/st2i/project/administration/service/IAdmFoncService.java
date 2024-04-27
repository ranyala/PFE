package tn.com.st2i.project.administration.service;

import java.util.List;

import tn.com.st2i.project.tools.model.SendObject;

public interface IAdmFoncService {
	SendObject getAllMenus();
    SendObject getMenusForIdProfil(List<Long> idProfil);
	SendObject getAllMenusChecked(Long idProfil);

}
