package org.okboom.bard.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.Endpoint;
import org.okboom.bard.admin.query.EndpointQuery;

/**
 * @author tookbra
 */
public interface EndpointService {

    /**
     * 分页查询api
     * @param endpointQuery {@linkplain EndpointQuery}
     * @return {@linkplain IPage}
     */
    IPage<Endpoint> listByPage(EndpointQuery endpointQuery);
}
