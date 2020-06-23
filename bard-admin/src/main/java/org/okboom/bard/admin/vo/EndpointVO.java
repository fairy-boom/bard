package org.okboom.bard.admin.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class EndpointVO implements Serializable {

    private static final long serialVersionUID = -1620505569184470795L;
    /**
     * api名称
     */
    private String name;
    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 路径
     */
    private String url;
    /**
     * http请求方法
     */
    private String method;
    /**
     * 版本
     */
    private String version;

}
