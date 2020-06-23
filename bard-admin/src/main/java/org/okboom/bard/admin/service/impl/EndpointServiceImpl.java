package org.okboom.bard.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.Endpoint;
import org.okboom.bard.admin.mapper.EndpointMapper;
import org.okboom.bard.admin.query.EndpointQuery;
import org.okboom.bard.admin.service.EndpointService;
import org.okboom.yuumi.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author tookbra
 */
@Service
public class EndpointServiceImpl extends BaseServiceImpl<EndpointMapper, Endpoint> implements EndpointService {

    @Override
    public IPage<Endpoint> listByPage(EndpointQuery endpointQuery) {
        return page(endpointQuery.getPage(),
                new QueryWrapper<Endpoint>().lambda()
                        .like(Endpoint::getName, endpointQuery.getName())
                        .like(Endpoint::getUrl, endpointQuery.getUrl())
                        .orderByDesc(Endpoint::getModifyTime));
    }
}
