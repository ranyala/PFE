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
import tn.com.st2i.project.administration.model.AdmLogData;
import tn.com.st2i.project.administration.service.IAdmLogDataService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.common.service.ISendWsService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.model.SearchObject;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admLogData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdmLogDataController {


    private static final Logger logger = LogManager.getLogger(AdmLogDataController.class);

    @Autowired
    private IAdmLogDataService admLogDataService;

    @Autowired
    private ISendWsService sendWsService;

    @Autowired
    private ICommonService commonService;


    @Operation(summary = "Recuperation liste de donnees sans filtre de recherche")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListAdmLogDataWs(HttpServletRequest request) {
        try {
            return sendWsService.sendResult(request, admLogDataService.getListAdmLogDataWs());
        } catch (Exception argEx) {
            logger.error("Error AdmLogDataController in method getListAdmLogDataWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }


    @Operation(summary = "Recuperation un objet d'apres son id")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdmLogDataByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        try {
            return sendWsService.sendResult(request, admLogDataService.findAdmLogDataByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AdmLogDataController in method getAdmLogDataByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }




    @Operation(summary = "Recuperation liste de donnees avec filtre de recherche")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataAdmLogDataWs(HttpServletRequest request, @RequestBody SearchObject obj) {
        try {
            return sendWsService.sendResult(request, commonService.getListPaginator(obj, new AdmLogData(), null));
        } catch (Exception argEx) {
            logger.error("Error AdmLogDataController in method getDataAdmLogDataWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }



}
