package org.okboom.bard.admin.service.impl;

import org.okboom.bard.admin.domain.Endpoint;
import org.okboom.bard.admin.mapper.EndpointMapper;
import org.okboom.bard.admin.service.EndpointService;
import org.okboom.yuumi.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author tookbra
 */
@Service
public class EndpointServiceImpl extends BaseServiceImpl<EndpointMapper, Endpoint> implements EndpointService {
}
