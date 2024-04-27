package tn.com.st2i.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tn.com.st2i.project.administration.model.AdmProfil;
import tn.com.st2i.project.view.model.VAdmProfil;
import tn.com.st2i.project.administration.service.IAdmFoncService;
import tn.com.st2i.project.administration.service.IAdmProfilService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.common.service.ISendWsService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.model.CriteriaSearch;
import tn.com.st2i.project.tools.model.SearchObject;

@RestController
@RequestMapping("/admProfil")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdmProfilController {

	private static final Logger logger = LogManager.getLogger(AdmProfilController.class);

	@Autowired
	private IAdmFoncService admFoncService;

	@Autowired
	private ISendWsService sendWsService;

	@Autowired
	private ICommonService commonService;
	

	@Autowired
	private IAdmProfilService admProfilService;


	@Operation(summary = "Recuperation liste de donnees avec filtre de recherche")
	@ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
	@PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDataAdmProfilWs(HttpServletRequest request, @RequestBody SearchObject obj) {
		try {
			if (obj.getDataSearch()!=null && !obj.getDataSearch().isEmpty()) {
				
			
			for (CriteriaSearch oneSearchElement : obj.getDataSearch()) {
				if (oneSearchElement.getValue().toString().contains("'")) {
					oneSearchElement.setValue(oneSearchElement.getValue().toString().replace("'", "''"));					
				}
			}}
			
			return sendWsService.sendResult(request, commonService.getListPaginator(obj, new AdmProfil(), null));
		} catch (Exception argEx) {
			logger.error("Error AdmProfilController in method getDataAdmProfilWs :: " + argEx.toString());
			return sendWsService.sendResultException(request);
		}
	}
	@Operation(summary = "Insert un nouveau objet")
	@ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pushAdmProfil(HttpServletRequest request, @RequestBody AdmProfil entity) {
		
		 try {
		        if (entity != null) {
		            return sendWsService.sendResult(request, admProfilService.saveAdmProfilWs(entity));
		        } else {
		           
		            return ResponseEntity.badRequest().body("Entity is null");
		        }
		    } catch (Exception argEx) {
		        logger.error("Error AdmProfilController in method pushAdmProfilWs :: " + argEx.toString());
		        return sendWsService.sendResultException(request);
		    }
	}

	@Operation(summary = "Mise a jour l'objet")
	@ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> putAdmProfilWs(HttpServletRequest request, @RequestBody AdmProfil entity) {
		try {
			return sendWsService.sendResult(request, admProfilService.updateAdmProfilWs(entity));
		} catch (Exception argEx) {
			logger.error("Error AdmProfilController in method putAdmProfilWs :: " + argEx.toString());
			return sendWsService.sendResultException(request);
		}
	}
	@Operation(summary = "Supprimer l'objet ")
	@ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_DELETE_ROW, description = "Impossible de supprimer l'objet car il est reference ", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteAdmProfilByIdWs(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			return sendWsService.sendResult(request, admProfilService.deleteAdmProfilByIdWs(id));
		} catch (Exception argEx) {
			logger.error("Error AdmProfilController in method deleteAdmProfilByIdWs :: " + argEx.toString());
			return sendWsService.sendResultException(request);
		}
	}

	@GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMenudWs(HttpServletRequest request, @RequestParam("idProfil") List<Long> idProfil) {
	    try {
	        if (idProfil == null || idProfil.isEmpty()  ) {
	        	 
	            
	            return sendWsService.sendResult(request, admFoncService.getAllMenus());
	        } else {
	           
	            return sendWsService.sendResult(request, admFoncService.getMenusForIdProfil(idProfil));
	        }
	    } catch (Exception argEx) {
	        logger.error("Error AdmFoncController in method getMenudWs :: " + argEx.toString());
	        return sendWsService.sendResultException(request);
	    }
	}

	
	@GetMapping(value = "/menuprofil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMenuProfil(HttpServletRequest request, @RequestParam("idProfil") Long idProfil) {
	    try {
	    	
	        	 return sendWsService.sendResult(request, admFoncService.getAllMenusChecked(idProfil));
		        
	        
	    } catch (Exception argEx) {
	        logger.error("Error AdmFoncController in method getMenudWs :: " + argEx.toString());
	        return sendWsService.sendResultException(request);
	    }
	}

	
	@Operation(summary = "Export data")
	@ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
			@ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content)})
	@PostMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> exportDataWs(HttpServletRequest request, @RequestBody SearchObject obj) {
		try {
			return sendWsService.downloadFile(request, commonService.exportDataWs(obj, new VAdmProfil(), null));
		} catch (Exception argEx) {
			logger.error("Error AdmProfilController in method exportDataWs :: " + argEx.toString());
			return sendWsService.sendResultException(request);
		}
	}
	
	
	
	
	
	


}

