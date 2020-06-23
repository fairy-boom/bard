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
@TableName("plugin")
public class Plugin extends BaseDomain {

    /**
     * 插件名称
     */
    private String name;
    /**
     * 插件类别id
     */
    private Integer categoryId;
    /**
     * 插件类别名称
     */
    private String categoryName;
    /**
     * 版本号
     */
    private String version;
    /**
     * 状态[1:开启,2:关闭]
     */
    private Integer status;
}
