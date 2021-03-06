package org.okboom.bard.admin.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * endpoint group vo
 * @author tookbra
 */
@Data
@ToString
public class EndpointGroupVO implements Serializable {
    private static final long serialVersionUID = 1609795602868494033L;

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
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}
