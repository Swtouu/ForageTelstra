package au.com.telstra.simcardactivator.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimCardActivateRequest {

    String iccid;
    String customerEmail;

}
