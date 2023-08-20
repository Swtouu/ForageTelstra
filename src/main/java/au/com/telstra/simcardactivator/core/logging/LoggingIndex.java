package au.com.telstra.simcardactivator.core.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
public class LoggingIndex {
    String iccid;
    String customerEmail;
    public StructuredArgument kv() {
        HashMap<String, Object> map = new HashMap<>();

        if (iccid != null) map.put("iccid", iccid);
        if (customerEmail != null) map.put("customer_email", customerEmail);

        return StructuredArguments.kv("sc_data", map);
    }
}
