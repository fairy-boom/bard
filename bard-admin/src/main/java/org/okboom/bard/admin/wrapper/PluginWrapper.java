package org.okboom.bard.admin.wrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.okboom.bard.admin.domain.Plugin;
import org.okboom.bard.admin.vo.PluginVO;
import org.okboom.yuumi.mybatis.base.BaseWrapper;

/**
 * plugin convert
 * @author tookbra
 */
@Mapper
public interface PluginWrapper extends BaseWrapper<Plugin, PluginVO> {

    PluginWrapper INSTANCE = Mappers.getMapper(PluginWrapper.class);
}
