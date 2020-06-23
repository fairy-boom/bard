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
@TableName("endpoint")
public class Endpoint extends BaseDomain {

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
