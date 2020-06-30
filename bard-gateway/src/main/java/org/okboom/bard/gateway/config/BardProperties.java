package org.okboom.bard.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tookbra
 */
@Data
@ConfigurationProperties(prefix = "bard.server")
public class BardProperties {

    /**
     * 网关端口
     */
    private Integer port;
}
