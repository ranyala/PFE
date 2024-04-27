package tn.com.st2i.Etablissement.model.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogEvent {

	private String ipAddress;
	private String token;
	private String uri;
	private String httpEvent;
	private String nameService;
	private String httpCodeUser;
	
}
