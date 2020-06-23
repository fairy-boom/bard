package org.okboom.bard.admin.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * api分组传输类
 * @author tookbra
 */
@Data
@ToString
public class EndpointGroupDTO implements Serializable {
    private static final long serialVersionUID = 3139775791788622322L;

    /**
     * id
     */
    private Long id;
    /**
     * api分组名称
     */
    private String name;
    /**
     * 服务地址
     */
    private String serverUrl;
    /**
     * url前缀
     */
    private String prefixUrl;
    /**
     * 描述
     */
    private String description;
}
