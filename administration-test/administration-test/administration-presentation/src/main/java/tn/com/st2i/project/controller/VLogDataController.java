package tn.com.st2i.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.com.st2i.project.administration.service.IVLogDataService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.common.service.ISendWsService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.model.SearchObject;

import tn.com.st2i.project.view.model.VAdmLogAcces;
import tn.com.st2i.project.view.model.VAdmLogData;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/VAdmLogData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VLogDataController {
    private static final Logger logger = LogManager.getLogger(VLogDataController.class);
    @Autowired
    private ISendWsService sendWsService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    IVLogDataService vLogDataService;


    @Operation(summary = "Recuperation un objet d'apres son id")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdmUtilisateurByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        try {
            return sendWsService.sendResult(request, vLogDataService.findVLogDataByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error VAdmUserController in method getAdmUtilisateurByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataVLogDataWs(HttpServletRequest request, @RequestBody SearchObject obj) {
        try {
            return sendWsService.sendResult(request, commonService.getListPaginator(obj, new VAdmLogData(), null));
        } catch (Exception argEx) {
            logger.error("Error AdministrationController in method getDataVLogAccessWs :: " + argEx.toString());
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
            return sendWsService.downloadFile(request, commonService.exportDataWs(obj, new VAdmLogData(), null));
        } catch (Exception argEx) {
            logger.error("Error VAdmLogAccesController in method exportDataWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
}
