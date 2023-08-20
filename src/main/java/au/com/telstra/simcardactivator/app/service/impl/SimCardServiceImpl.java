package au.com.telstra.simcardactivator.app.service.impl;

import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateResponse;
import au.com.telstra.simcardactivator.app.exception.SimCardErrorCode;
import au.com.telstra.simcardactivator.app.exception.SimCardServiceException;
import au.com.telstra.simcardactivator.app.model.SimCardModel;
import au.com.telstra.simcardactivator.app.repository.SimActivationRepository;
import au.com.telstra.simcardactivator.app.service.SimCardService;
import au.com.telstra.simcardactivator.core.logging.LoggingIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class SimCardServiceImpl implements SimCardService {

    @Resource
    private LoggingIndex loggingIndex;

    private final String ACTUATOR_URL = "http://localhost:8444/actuate";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SimActivationRepository simActivationRepository;

    @Override
    public SimCardActivateResponse simCardActivate (SimCardActivateRequest request) {
        try {
            if (Objects.isNull(request)) {
                log.error("Request is null.");
                throw new SimCardServiceException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        SimCardErrorCode.INTERNAL_SERVER_ERROR,
                        "Error Sim Card Activate message: %s");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String payload = "{\"iccid\":\"" + request.getIccid() + "\"}";
            HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<SimCardActivateResponse> response = restTemplate.postForEntity(ACTUATOR_URL, entity, SimCardActivateResponse.class);

            Boolean isSuccess = response.getBody().getSuccess();

            SimCardModel model = SimCardModel.builder().id(request.getId())
                    .iccid(request.getIccid())
                    .customerEmail(request.getCustomerEmail())
                    .active(isSuccess)
                    .build();
            this.simActivationRepository.saveAndFlush(model);

            return response.getBody();
        } catch (Exception ex) {
            log.error(String.format("Error sim card activate by request: [%s] message: %s", request, ex.getMessage()), this.loggingIndex.kv());
            throw new SimCardServiceException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    SimCardErrorCode.INTERNAL_SERVER_ERROR,
                    ex,
                    "Error Sim Card Activate message: %s",
                    ex.getMessage());
        }
    }

    @Override
    public SimCardModel simCardInquiry (String id) {
        try {
            return this.simActivationRepository.findById(Long.valueOf(id)).orElse(null);
        } catch (Exception ex) {
            log.error(String.format("Error sim card inquiry by id: [%s] message: %s", id, ex.getMessage()), this.loggingIndex.kv());
            throw new SimCardServiceException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    SimCardErrorCode.INTERNAL_SERVER_ERROR,
                    ex,
                    "Error Sim Card inquiry message: %s",
                    ex.getMessage());
        }
    }
}
