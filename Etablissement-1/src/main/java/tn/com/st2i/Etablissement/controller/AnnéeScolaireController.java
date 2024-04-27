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

import tn.com.st2i.Etablissement.model.AnnéeScolaire;
import tn.com.st2i.Etablissement.service.ICommonService;
import tn.com.st2i.Etablissement.service.ISendWsService;
import tn.com.st2i.Etablissement.service.IannéeScolaireService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;

@RestController
@RequestMapping("/AnnéeScolaireController")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnéeScolaireController {

    private static final Logger logger = LogManager.getLogger(AnnéeScolaireController.class);
    @Autowired
    private IannéeScolaireService AnnéeScolaireService;

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
    public ResponseEntity<?> getListAnnéeScolaireWs(HttpServletRequest request) {
        try {
            return sendWsService.sendResult(request, AnnéeScolaireService.getListannéeScolaireWs() );
        } catch (Exception argEx) {
            logger.error("Error AnnéeScolaireController in method getListAnnéeScolaireWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Recuperation un objet d'apres son id")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnnéeScolaireByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        try {
            return sendWsService.sendResult(request, AnnéeScolaireService.findAnnéeScolaireByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AnnéeScolaireController in method getAnnéeScolaireByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Insert un nouveau objet")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pushAnnéeScolaire(HttpServletRequest request, @RequestBody AnnéeScolaire entity) {
        try {
            return sendWsService.sendResult(request, AnnéeScolaireService.saveOrUpdateAnnéeScolaireWs(entity));
        } catch (Exception argEx) {
            logger.error("Error AnnéeScolaireController in method pushAnnéeScolaireWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    @Operation(summary = "Mise a jour l'objet")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putAnnéeScolaireWs(HttpServletRequest request, @RequestBody AnnéeScolaire entity) {
        try {
            return sendWsService.sendResult(request, AnnéeScolaireService.saveOrUpdateAnnéeScolaireWs(entity));
        } catch (Exception argEx) {
            logger.error("Error AnnéeScolaireController in method putAnnéeScolaireWs :: " + argEx.toString());
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
    public ResponseEntity<?> deleteAnnéeScolaireByIdWs(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            return sendWsService.sendResult(request, AnnéeScolaireService.deleteannéeScolaireByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AnnéeScolaireController in method deleteAnnéeScolaireByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }





}
