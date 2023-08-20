package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.app.controller.SimCardActivatorController;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateResponse;
import au.com.telstra.simcardactivator.app.model.SimCardModel;
import au.com.telstra.simcardactivator.app.service.SimCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SimCardActivatorControllerTest {
    @InjectMocks
    private SimCardActivatorController simCardActivatorController = new SimCardActivatorController();

    @Mock
    private SimCardService simCardService;

    private SimCardActivateRequest simCardActivateRequest;

    private SimCardActivateResponse simCardActivateResponse;


    @Before
    public void setup() {
        this.simCardActivateRequest = SimCardActivateRequest.builder()
                .iccid("123")
                .customerEmail("123@gmail.com")
                .build();
    }

    @Test
    public void SimCardActivateSuccess() {
        when(this.simCardService.simCardActivate(any())).thenReturn(SimCardActivateResponse.builder().success(true).build());
        ResponseEntity<SimCardActivateResponse> result = this.simCardActivatorController.simCardActivate(this.simCardActivateRequest);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void SimCardActivateFail() {
        when(this.simCardService.simCardActivate(any())).thenReturn(SimCardActivateResponse.builder().success(false).build());
        ResponseEntity<SimCardActivateResponse> result = this.simCardActivatorController.simCardActivate(this.simCardActivateRequest);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void SimCardActivateNull() {
        when(this.simCardService.simCardActivate(any())).thenReturn(null);
        ResponseEntity<SimCardActivateResponse> result = this.simCardActivatorController.simCardActivate(this.simCardActivateRequest);
        assertThat(result.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void SimCardInquirySuccess() {
        when(this.simCardService.simCardInquiry(any())).thenReturn(SimCardModel.builder().build());
        ResponseEntity<SimCardModel> result = this.simCardActivatorController.simCardInquiry(any());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void SimCardInquiryFail() {
        when(this.simCardService.simCardInquiry(any())).thenReturn(null);
        ResponseEntity<SimCardModel> result = this.simCardActivatorController.simCardInquiry(any());
        assertThat(result.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
