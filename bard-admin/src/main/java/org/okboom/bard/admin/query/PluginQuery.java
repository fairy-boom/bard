package org.okboom.bard.admin.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 插件查询参数
 * @author tookbra
 */
@Data
public class PluginQuery implements Serializable {

    private static final long serialVersionUID = -7689003321165164911L;

    /**
     * 插件名
     */
    private String pluginName;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 分页参数
     */
    private Page page;
}
