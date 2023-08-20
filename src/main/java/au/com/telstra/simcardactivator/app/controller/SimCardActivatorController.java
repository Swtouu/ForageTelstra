package au.com.telstra.simcardactivator.app.controller;

import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateResponse;
import au.com.telstra.simcardactivator.app.service.SimCardService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimCardActivatorController {

    @Autowired
    @Setter
    private SimCardService simCardService;

    @PostMapping("/simcard/activate")
    public ResponseEntity<SimCardActivateResponse> SimCardActivate (@RequestBody SimCardActivateRequest request) {
        SimCardActivateResponse response = this.simCardService.simCardActivate(request);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
