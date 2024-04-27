package tn.com.st2i.Etablissement.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.com.st2i.Etablissement.model.tool.LogEvent;
import tn.com.st2i.Etablissement.model.tool.SendObject;
import tn.com.st2i.Etablissement.service.ISendWsService;
import tn.com.st2i.Etablissement.tools.ConstanteService;
import tn.com.st2i.Etablissement.tools.ConstanteWs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@Service

public class SendWsService implements ISendWsService {

    private static final Logger logger = LogManager.getLogger(SendWsService.class);

    @Value("${spring.application.name}")
    private String nameService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> sendResult(HttpServletRequest request, SendObject so) {
        try {
            LogEvent logEvent = new LogEvent(request.getRemoteAddr() + ":" + request.getRemotePort(),
                    request.getHeader("Authorization"), request.getRequestURI(), request.getMethod(), nameService,
                    so.getCode());
            try {
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    public void run() {
                        restTemplate.postForEntity("lb://gateway-service/intern/logData/traceability", logEvent,
                                SendObject.class);
                    }
                });
            } catch (Exception e) {
                logger.error("Error SendWsService in method sendResult/send to gateway  :: " + e.toString());
            }
            return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
        } catch (Exception argEx) {
            logger.error("Error SendWsService in method sendResult :: " + argEx.toString());
            return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
        }
    }

    @Override
    @SuppressWarnings("finally")
    public ResponseEntity<?> sendResultException(HttpServletRequest request) {
        try {
            LogEvent logEvent = new LogEvent(request.getRemoteAddr() + ":" + request.getRemotePort(),
                    request.getHeader("Authorization"), request.getRequestURI(), request.getMethod(), nameService,
                    ConstanteWs._CODE_WS_ERROR);
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                public void run() {
                    restTemplate.postForEntity("lb://gateway-service/intern/logData/traceability", logEvent,
                            SendObject.class);
                }
            });
        } catch (Exception argEx) {
            logger.error("Error SendWsService in method sendResult :: " + argEx.toString());
        } finally {
            return new ResponseEntity<>(new JSONObject(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> sendResultPublic(HttpServletRequest request, SendObject so) {
        return new ResponseEntity<>(so.getPayload().toString(), new HttpHeaders(), so.getHttp());
    }

    @Override
    public Long getIdCurrentUser(HttpServletRequest request) {
        try {
            ResponseEntity<SendObject> os = restTemplate.postForEntity(
                    "lb://gateway-service/gateway/intern/current/user/id", request.getHeader("Authorization"),
                    SendObject.class);
            SendObject so = os.getBody();
            if (!so.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return null;
            return Long.valueOf(so.getPayload().toString());
        } catch (Exception argEx) {
            logger.error("Error SendWsService in method getCurrentUserId :: " + argEx.toString());
            return null;
        }
    }

//    @Override
//    public VAdmUser getCurrentUser(HttpServletRequest request) {
//        VAdmUser user = new VAdmUser();
//        try {
//            ResponseEntity<SendObject> os = restTemplate.postForEntity(
//                    "lb://gateway-service/gateway/intern/current/user", request.getHeader("Authorization"),
//                    SendObject.class);
//            SendObject so = os.getBody();
//            if (!so.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
//                return null;
//            ObjectMapper mapper = new ObjectMapper();
//            user = mapper.convertValue(so.getPayload(), VAdmUser.class);
//            return user;
//        } catch (Exception argEx) {
//            logger.error("Error SendWsService in method getCurrentUserId :: " + argEx.toString());
//            return null;
//        }
//    }

    @Override
    public ResponseEntity<?> downloadFile(HttpServletRequest request, SendObject so) {
        File file = null;
        try {
            if (!so.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
                return ResponseEntity.status(500).body(so.getPayload());

            file = (File) so.getPayload();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (FileNotFoundException e) {
            logger.error("Error SendWsService in method getCurrentUserId :: " + e.toString());
            return null;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    @Override
    public ResponseEntity<?> sendResultException(Authentication authentication, String string, String codeGet) {
        // TODO Auto-generated method stub
        return null;
    }

}
