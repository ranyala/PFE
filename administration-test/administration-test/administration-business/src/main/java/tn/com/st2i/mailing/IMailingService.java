package tn.com.st2i.mailing;

import tn.com.st2i.project.tools.model.EmailModal;
import tn.com.st2i.project.tools.model.SendObject;

public interface IMailingService {
	
	public SendObject sendEmailWs(EmailModal email);

}
