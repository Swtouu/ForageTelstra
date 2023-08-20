package au.com.telstra.simcardactivator.app.service;

import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateResponse;

public interface SimCardService {

    SimCardActivateResponse simCardActivate(SimCardActivateRequest simCardActivateRequest);
}
