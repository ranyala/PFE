//package tn.com.st2i.Etablissement.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.servlet.http.HttpServletRequest;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tn.com.st2i.Etablissement.model.Classe;
//
//import tn.com.st2i.Etablissement.service.ICommonService;
//import tn.com.st2i.Etablissement.service.ISendWsService;
//import tn.com.st2i.Etablissement.service.IclasseService;
//import tn.com.st2i.Etablissement.service.impl.ClasseService;
//import tn.com.st2i.Etablissement.tools.ConstanteWs;
//
//@RestController
//@RequestMapping("/classe")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class ClasseController {
//
//    private static final Logger logger = LogManager.getLogger(ClasseController.class);
//
//
//    @Autowired
//    private IclasseService classService ;
//
//    @Autowired
//    private ISendWsService sendWsService;
//
//
//
//    @Autowired
//    private ICommonService commonService;
//
//
//
//    @Operation(summary = "Recuperation liste de donnees sans filtre de recherche")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
//    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getListClasseWs(HttpServletRequest request) {
//        try {
//            return sendWsService.sendResult(request, ClasseService.getListClasseWs() );
//        } catch (Exception argEx) {
//            logger.error("Error ClasseController in method getListClasseWs :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//    @Operation(summary = "Recuperation un objet d'apres son id")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getClasseByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
//        try {
//            return sendWsService.sendResult(request, ClasseService.findClasseByIdWs(id));
//        } catch (Exception argEx) {
//            logger.error("Error ClasseController in method getClasseByIdWs :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//    @Operation(summary = "Insert un nouveau objet")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
//    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> pushClasse(HttpServletRequest request, @RequestBody Classe entity) {
//        try {
//            return sendWsService.sendResult(request, ClasseService.saveOrUpdateClasseWs(entity));
//        } catch (Exception argEx) {
//            logger.error("Error ClasseController in method pushClasseWs :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//    @Operation(summary = "Mise a jour l'objet")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
//    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> putClasseWs(HttpServletRequest request, @RequestBody Classe entity) {
//        try {
//            return sendWsService.sendResult(request, ClasseService.saveOrUpdateClasseWs(entity));
//        } catch (Exception argEx) {
//            logger.error("Error ClasseController in method putClasseWs :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//
//
//    @Operation(summary = "Supprimer l'objet ")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_DELETE_ROW, description = "Impossible de supprimer l'objet car il est reference ", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deleteClasseByIdWs(HttpServletRequest request, @PathVariable("id") Long id) {
//        try {
//            return sendWsService.sendResult(request, ClasseService.deleteClasseByIdWs(id));
//        } catch (Exception argEx) {
//            logger.error("Error ClasseController in method deleteClasseByIdWs :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//}
