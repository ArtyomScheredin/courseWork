package spbstu.deans_office.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "secure";
    private long validityInMS = 180000L;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getValidityInMS() {
        return validityInMS;
    }

    public void setValidityInMS(long validityInMS) {
        this.validityInMS = validityInMS;
    }
}
