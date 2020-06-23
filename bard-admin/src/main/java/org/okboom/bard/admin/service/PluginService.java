package org.okboom.bard.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.Plugin;
import org.okboom.bard.admin.query.PluginQuery;

import java.util.List;

/**
 * @author tookbra
 */
public interface PluginService {

    /**
     * 插件分页查询
     * @param pluginQuery {@linkplain PluginQuery}
     * @return {@linkplain IPage}
     */
    IPage<Plugin> listByPage(PluginQuery pluginQuery);

    /**
     * 查询插件
     * @param id 插件id
     * @return {@linkplain Plugin}
     */
    Plugin findById(Long id);

    /**
     * 删除插件
     * @param id 插件id
     */
    void delete(Long id);

    /**
     * 批量删除插件
     * @param ids
     */
    void batchDelete(List<Long> ids);
}
