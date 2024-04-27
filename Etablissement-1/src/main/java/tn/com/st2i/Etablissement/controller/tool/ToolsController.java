package tn.com.st2i.Etablissement.controller.tool;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.ISendWsService;


@RestController
@RequestMapping("/tools")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ToolsController {

	private static final Logger logger = LogManager.getLogger(ToolsController.class);

	@Autowired
	private ISendWsService sendWsService;

	@Autowired
	private ICommonService commonService;

	@GetMapping(value = "/dateNow", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDateNow(HttpServletRequest request) {
		try {
			return sendWsService.sendResultPublic(request, commonService.getDateSystemNowWs());
		} catch (Exception argEx) {
			logger.error("Error ToolsController in method getDateNow :: " + argEx.toString());
			return sendWsService.sendResultException(request);
		}
	}

}
