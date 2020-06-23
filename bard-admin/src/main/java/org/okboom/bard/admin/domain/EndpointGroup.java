package org.okboom.bard.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.okboom.yuumi.mybatis.base.BaseDomain;

/**
 * @author tookbra
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("endpoint_group")
public class EndpointGroup extends BaseDomain {
    private static final long serialVersionUID = 5272285915747282651L;

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
