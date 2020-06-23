package org.okboom.bard.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.EndpointGroup;
import org.okboom.bard.admin.dto.EndpointGroupDTO;
import org.okboom.bard.admin.mapper.EndpointGroupMapper;
import org.okboom.bard.admin.query.EndpointGroupQuery;
import org.okboom.bard.admin.service.EndpointGroupService;
import org.okboom.bard.admin.wrapper.EndpointGroupWrapper;
import org.okboom.yuumi.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tookbra
 */
@Service
public class EndpointGroupServiceImpl extends BaseServiceImpl<EndpointGroupMapper, EndpointGroup> implements EndpointGroupService {

    @Override
    public IPage<EndpointGroup> listByPage(EndpointGroupQuery endpointGroupQuery) {
        return page(endpointGroupQuery.getPage(),
                new QueryWrapper<EndpointGroup>().lambda()
                        .eq(EndpointGroup::getName, endpointGroupQuery.getName())
                        .like(EndpointGroup::getPrefixUrl, endpointGroupQuery.getPrefixUrl())
                        .orderByDesc(EndpointGroup::getModifyTime));
    }

    @Override
    public EndpointGroup findById(Long id) {
        return getById(id);
    }

    @Override
    public boolean save(EndpointGroupDTO endpointGroupDTO) {
        EndpointGroup endpointGroup = EndpointGroupWrapper.INSTANCE.convert(endpointGroupDTO);
        return saveOrUpdate(endpointGroup);
    }

    @Override
    public void delete(Long id) {
        removeById(id);
        // todo notify
    }

    @Override
    public void batchDelete(List<Long> ids) {
        removeByIds(ids);
        // todo notify
    }
}
