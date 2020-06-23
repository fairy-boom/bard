package org.okboom.bard.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.Plugin;
import org.okboom.bard.admin.mapper.PluginMapper;
import org.okboom.bard.admin.query.PluginQuery;
import org.okboom.bard.admin.service.PluginService;
import org.okboom.yuumi.mybatis.base.BaseDomain;
import org.okboom.yuumi.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 插件 service
 * @author tookbra
 */
@Service
public class PluginServiceImpl extends BaseServiceImpl<PluginMapper, Plugin> implements PluginService {

    @Override
    public IPage<Plugin> listByPage(PluginQuery pluginQuery) {
        return page(pluginQuery.getPage(),
                new QueryWrapper<Plugin>().lambda()
                        .eq(Plugin::getName, pluginQuery.getPluginName())
                        .eq(Plugin::getCategoryId, pluginQuery.getCategoryId())
                        .orderByDesc(BaseDomain::getModifyTime));
    }

    @Override
    public Plugin findById(Long id) {
        return getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        removeById(id);
        // todo notify
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids) {
        removeByIds(ids);
        // todo notify
    }
}
