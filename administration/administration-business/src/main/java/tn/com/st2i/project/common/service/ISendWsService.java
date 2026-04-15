package tn.com.st2i.project.common.service;

import org.apache.maven.artifact.repository.Authentication;
import org.springframework.http.ResponseEntity;
import tn.com.st2i.project.tools.model.SendObject;
import tn.com.st2i.project.view.model.VAdmUser;

import javax.servlet.http.HttpServletRequest;

public interface ISendWsService {

	

	public ResponseEntity<?> sendResultException(HttpServletRequest request);

	public ResponseEntity<?> sendResultPublic(HttpServletRequest request, SendObject so);
	
	public Long getIdCurrentUser(HttpServletRequest request);

    public VAdmUser getCurrentUser(HttpServletRequest request);

	public ResponseEntity<?> sendResultException(Authentication authentication, String string, String codeGet);

	ResponseEntity<?> downloadFile(HttpServletRequest request, SendObject so);

	public ResponseEntity<?> sendResult(HttpServletRequest request, SendObject listPaginator);


}
