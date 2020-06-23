package org.okboom.bard.admin.wrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.okboom.bard.admin.domain.Endpoint;
import org.okboom.bard.admin.vo.EndpointVO;
import org.okboom.yuumi.mybatis.base.BaseWrapper;

/**
 * endpoint wrapper
 * @author tookbra
 */
@Mapper
public interface EndpointWrapper extends BaseWrapper<Endpoint, EndpointVO> {

    EndpointWrapper INSTANCE = Mappers.getMapper(EndpointWrapper.class);
}
