package tn.com.st2i.project.controller.administration;

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
import tn.com.st2i.project.administration.model.AdmUser;
import tn.com.st2i.project.administration.service.IAdmUserService;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.common.service.ISendWsService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.model.SearchObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admUser")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdmUserController {
    private static final Logger logger = LogManager.getLogger(AdmUserController.class);

    @Autowired
    private IAdmUserService admUserService;

    @Autowired
    private ISendWsService sendWsService;

    @Autowired
    private ICommonService commonService;

    @Operation(summary = "Get all users list data without search filter")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service Error", content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListAdmUserWs(HttpServletRequest request) {
        try {
            return sendWsService.sendResult(request, admUserService.getListAdmUserWs());
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method getListAdmUserWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdmUserByIdWs(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        try {
            return sendWsService.sendResult(request, admUserService.findAdmUserByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method getAdmUserByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @Operation(summary = "Insert a new user object")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pushAdmUser(HttpServletRequest request, @RequestBody AdmUser entity) {
        try {
            return sendWsService.sendResult(request, admUserService.saveOrUpdateAdmUserWs(entity));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method pushAdmUserWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @Operation(summary = "Update User object")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putAdmUserWs(HttpServletRequest request, @RequestBody AdmUser entity) {
        try {
            return sendWsService.sendResult(request, admUserService.saveOrUpdateAdmUserWs(entity));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method putAdmUserWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @Operation(summary = "Delete a user object")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_DELETE_ROW, description = "Cannot delete object because it is a reference", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAdmUserByIdWs(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            return sendWsService.sendResult(request, admUserService.deleteAdmUserByIdWs(id));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method deleteAdmUserByIdWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

    @Operation(summary = "Get list of users with search filter")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataAdmUserWs(HttpServletRequest request, @RequestBody SearchObject obj) {
        try {
            return sendWsService.sendResult(request, commonService.getListPaginator(obj, new AdmUser(), null));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method getDataAdmUserWs :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }

//    @Operation(summary = "Register a User")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content) })
//    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveUser(HttpServletRequest request, @RequestBody AdmUser user) throws Exception {
//        try {
//            return sendWsService.sendResult(request,
//                    admUserService.registerWs(user));
//        } catch (Exception argEx) {
//            logger.error("Error AdmUserController in method saveUser :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
//
//    @Operation(summary = "Update a user")
//    @ApiResponses(value = { @ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
//            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content) })
//    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody AdmUser user) throws Exception {
//        try {
//            return sendWsService.sendResult(request, admUserService.updateUserWs(user));
//        } catch (Exception argEx) {
//            logger.error("Error AdmUserController in method updateUser :: " + argEx.toString());
//            return sendWsService.sendResultException(request);
//        }
//    }
    
   
    @Operation(summary = "Get an Agent By idCmsPaymentAgency")
    @ApiResponses(value = {@ApiResponse(responseCode = ConstanteWs._CODE_WS_SUCCESS, description = "Success"),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_ALIAS_PARAM, description = "One or many parameter(s) is null", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR_IN_METHOD, description = "Method error", content = @Content),
            @ApiResponse(responseCode = ConstanteWs._CODE_WS_ERROR, description = "Service error", content = @Content)})
    @GetMapping(value = "/byCmsPaymentAgency/{idCmsPaymentAgency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAgentsByCmsPaymentAgency(HttpServletRequest request, @PathVariable(name = "idCmsPaymentAgency") Long idCmsPaymentAgency) {
        try {
            return sendWsService.sendResult(request, admUserService.getAgentsByIdCmsPaymentAgency(idCmsPaymentAgency));
        } catch (Exception argEx) {
            logger.error("Error AdmUserController in method getAgentsByCmsPaymentAgency :: " + argEx.toString());
            return sendWsService.sendResultException(request);
        }
    }
 

}
