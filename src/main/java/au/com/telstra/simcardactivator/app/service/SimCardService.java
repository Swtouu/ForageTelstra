package au.com.telstra.simcardactivator.app.service;

import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateResponse;
import au.com.telstra.simcardactivator.app.model.SimCardModel;

public interface SimCardService {

    SimCardActivateResponse simCardActivate(SimCardActivateRequest simCardActivateRequest);
    SimCardModel simCardInquiry(String id);

}
