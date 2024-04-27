package tn.com.st2i.project.tools.model;

import lombok.Data;

@Data
public class EmailModal {

	private String title;
	private String body;
	private String emailSend;
	private String emailFrom;
	private String template;
	private Object emailEvent;
	private String lang;
	
}
