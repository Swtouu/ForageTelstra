package au.com.telstra.simcardactivator.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimCardModel {

    private Long id;

    private String iccid;

    private String customerEmail;

    private boolean active;

}
