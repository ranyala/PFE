package tn.com.st2i.Etablissement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.st2i.Etablissement.model.Etablissement;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.ISendWsService;
import tn.com.st2i.Etablissement.service.IetablissementService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;

@RestController
@RequestMapping("/Etablissement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EtablissementController {

    private static final Logger logger = LogManager.getLogger(EtablissementController.class);
    @Autowired
    private IetablissementService  EtablissementService;

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
    public ResponseEntity<?> getListEtablissementWs(HttpServletRequest request) {
        try {
            return sendWsService.sendResult(request, EtablissementService.getListetablissementWs() );
        } catch (Exception argEx) {
            logger.error("Error EtablissementController in method getListEtablissementWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Recuperation un objet d'apres son id")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEtablissementByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        try {
            return sendWsService.sendResult(request, EtablissementService.findEtablissementByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error EtablissementController in method getEtablissementByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Insert un nouveau objet")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pushEtablissement(HttpServletRequest request, @RequestBody Etablissement entity) {
        try {
            return sendWsService.sendResult(request, EtablissementService.saveOrUpdateEtablissementWs(entity));
        } catch (Exception argEx) {
            logger.error("Error EtablissementController in method pushEtablissementWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Mise a jour l'objet")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putEtablissementWs(HttpServletRequest request, @RequestBody Etablissement entity) {
        try {
            return sendWsService.sendResult(request, EtablissementService.saveOrUpdateEtablissementWs(entity));
        } catch (Exception argEx) {
            logger.error("Error EtablissementController in method putEtablissementWs :: " + argEx.toString());
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
    public ResponseEntity<?> deleteEtablissementByIdWs(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            return sendWsService.sendResult(request, EtablissementService.deleteetablissementByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error EtablissementController in method deleteEtablissementByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }





}
