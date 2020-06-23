package org.okboom.bard.admin.wrapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.okboom.bard.admin.domain.EndpointGroup;
import org.okboom.bard.admin.dto.EndpointGroupDTO;
import org.okboom.bard.admin.vo.EndpointGroupVO;
import org.okboom.yuumi.mybatis.base.BaseWrapper;

/**
 * endpoint group wrapper
 * @author tookbra
 */
@Mapper
public interface EndpointGroupWrapper extends BaseWrapper<EndpointGroup, EndpointGroupVO> {

    EndpointGroupWrapper INSTANCE = Mappers.getMapper(EndpointGroupWrapper.class);


    /**
     * dto to bo
     * @param endpointGroupDTO {@linkplain EndpointGroupDTO}
     * @return {@linkplain EndpointGroup}
     */
    @Mappings({})
    EndpointGroup convert(EndpointGroupDTO endpointGroupDTO);
}
