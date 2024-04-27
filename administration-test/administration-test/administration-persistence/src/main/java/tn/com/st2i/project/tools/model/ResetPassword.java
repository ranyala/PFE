package tn.com.st2i.project.tools.model;

import lombok.Data;

@Data
public class ResetPassword {

	private String newPassword;
	private String confrimPassword;
	private String oldPassword;
	
}
