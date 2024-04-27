package tn.com.st2i.project.controller;

        import java.util.List;

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
        import tn.com.st2i.project.administration.model.AdmProfession;
        import tn.com.st2i.project.administration.service.IAdmFoncService;
        import tn.com.st2i.project.administration.service.IFonctionService;
        import tn.com.st2i.project.common.service.ICommonService;
        import tn.com.st2i.project.common.service.ISendWsService;
        import tn.com.st2i.project.tools.ConstanteWs;
        import tn.com.st2i.project.tools.model.CriteriaSearch;
        import tn.com.st2i.project.tools.model.SearchObject;
        import tn.com.st2i.project.tools.model.SendObject;
        import tn.com.st2i.project.view.model.VAdmUser;

        import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin("*")
@RequestMapping("fonction")
public class FoncController {

    private static final Logger logger = LogManager.getLogger(FoncController.class);

    @Autowired
    private IAdmFoncService admFoncService;

    @Autowired
    private ISendWsService sendWsService;

    @Autowired
    private ICommonService commonService;
    @Autowired
    private IFonctionService fonctionService;



    @Operation(summary = "Recuperation liste de donnees avec filtre de recherche")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataAdmProfessionWs(HttpServletRequest request, @RequestBody SearchObject obj) {
        try {
            if (obj.getDataSearch()!=null && !obj.getDataSearch().isEmpty()) {


                for (CriteriaSearch oneSearchElement : obj.getDataSearch()) {
                    if (oneSearchElement.getValue().toString().contains("'")) {
                        oneSearchElement.setValue(oneSearchElement.getValue().toString().replace("'", "''"));
                    }
                }}

            return sendWsService.sendResult(request, commonService.getListPaginator(obj, new AdmProfession(), null));
        } catch (Exception argEx) {
            logger.error("Error AdmProfessionController in method getDataAdmProfessionWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }



    @Operation(summary = "Recuperation liste de donnees avec filtre de recherche")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity FindById(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            return sendWsService.sendResult(request,fonctionService.findFonctionIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AdmProfessionController in method getDataAdmProfessionWs :: " + argEx.toString());
            return null;
        }
    }



    @Operation(summary = "Delete data by ID with search filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or more parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)
    })
    @DeleteMapping(value = "/deleteById", produces = MediaType.APPLICATION_JSON_VALUE)
    public SendObject deleteById(HttpServletRequest request, @RequestParam("idFonction") Long idFonction) {
        try {
            return  fonctionService.deleteFonctionByIdWs(idFonction);


        } catch (Exception argEx) {
            logger.error("Error AdmProfessionController in method deleteDataAdmProfessionWs :: " + argEx.toString());
            return null;
        }
    }

    @Operation(summary = "Insert a new object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or more parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFonction(HttpServletRequest request,@RequestBody AdmProfession entity) {
        try {
            // Assuming admFoncService and sendWsService are properly defined
            return sendWsService.sendResult(request, fonctionService.saveOrUpdateFonction(entity));
            //return  fonctionService.addFonction(obj);
        } catch (Exception argEx) {
            logger.error("Error in addFonction method: " + argEx.toString());
            return null;
        }
    }

    @Operation(summary = "Mise a jour l'objet")
    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "Un ou plus param�tre(s) est null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Erreur du methode", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Erreur du service", content = @Content) })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editFonction(HttpServletRequest request, @RequestBody AdmProfession entity) {
        try {
            return sendWsService.sendResult(request, fonctionService.saveOrUpdateFonction(entity));
        } catch (Exception argEx) {
            logger.error("Error NmCategoriPlainteWsController in method putNmCategoriPlainteWs :: " + argEx.toString());
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
            return sendWsService.downloadFile(request, commonService.exportDataWs(obj, new AdmProfession(), null));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method exportDataWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
    }



