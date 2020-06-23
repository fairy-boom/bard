package org.okboom.bard.admin.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 插件前端传输类
 * @author tookbra
 */
@Data
@ToString
public class PluginDTO implements Serializable {
    private static final long serialVersionUID = 8488041586795929453L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 插件名称
     */
    private String name;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 状态
     */
    private Integer status;
}
