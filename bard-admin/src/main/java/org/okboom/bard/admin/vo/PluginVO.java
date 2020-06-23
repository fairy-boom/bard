package org.okboom.bard.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * plugin vo
 * @author tookbra
 */
@Data
@AllArgsConstructor
@ToString
public class PluginVO implements Serializable {
    private static final long serialVersionUID = -7450418145762291580L;

    private Long id;
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
