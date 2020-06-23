package org.okboom.bard.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.okboom.bard.admin.domain.EndpointGroup;
import org.okboom.bard.admin.dto.EndpointGroupDTO;
import org.okboom.bard.admin.query.EndpointGroupQuery;

import java.util.List;

/**
 * @author tookbra
 */
public interface EndpointGroupService {

    /**
     * 分页查询api分组
     * @param endpointGroupQuery {@linkplain EndpointGroupQuery}
     * @return {@linkplain IPage}
     */
    IPage<EndpointGroup> listByPage(EndpointGroupQuery endpointGroupQuery);

    /**
     * 获取api分组详情
     * @param id 分组id
     * @return {@linkplain EndpointGroup}
     */
    EndpointGroup findById(Long id);

    /**
     * 新增或修改aoi分组
     * @param endpointGroupDTO {@linkplain EndpointGroupDTO}
     * @return true or false
     */
    boolean save(EndpointGroupDTO endpointGroupDTO);

    /**
     * 删除api分组
     * @param id 分组id
     */
    void delete(Long id);

    /**
     * 批量删除api分组
     * @param ids api分组id列表
     */
    void batchDelete(List<Long> ids);
}
