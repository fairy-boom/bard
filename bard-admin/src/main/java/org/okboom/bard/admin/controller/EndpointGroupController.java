package org.okboom.bard.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.okboom.bard.admin.domain.EndpointGroup;
import org.okboom.bard.admin.dto.EndpointGroupDTO;
import org.okboom.bard.admin.dto.PluginDTO;
import org.okboom.bard.admin.query.EndpointGroupQuery;
import org.okboom.bard.admin.query.PluginQuery;
import org.okboom.bard.admin.service.EndpointGroupService;
import org.okboom.bard.admin.vo.EndpointGroupVO;
import org.okboom.bard.admin.vo.PluginVO;
import org.okboom.bard.admin.wrapper.EndpointGroupWrapper;
import org.okboom.bard.admin.wrapper.PluginWrapper;
import org.okboom.yuumi.tool.data.ServiceResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author tookbra
 */
@RestController
@AllArgsConstructor
@RequestMapping("/endpointGroups")
public class EndpointGroupController {

    private final EndpointGroupService endpointGroupService;

    /**
     * 分页查询api分组
     * @param endpointGroupQuery {@linkplain EndpointGroupQuery}
     * @return {@linkplain ServiceResult}
     */
    @GetMapping
    public ServiceResult queryEndpointGroup(@RequestBody final EndpointGroupQuery endpointGroupQuery) {
        IPage<EndpointGroup> page = endpointGroupService.listByPage(endpointGroupQuery);
        IPage<EndpointGroupVO> result = EndpointGroupWrapper.INSTANCE.convertPageVO(page);
        return ServiceResult.success(result);
    }

    /**
     * 获取api分组详情
     * @param id 查询id
     * @return {@linkplain ServiceResult }
     */
    @GetMapping("/{id}")
    public ServiceResult getEndpointGroup(@PathVariable("id") final Long id) {
        EndpointGroupVO endpointGroupVO = EndpointGroupWrapper.INSTANCE.convertToTarget(endpointGroupService.findById(id));
        return ServiceResult.success(endpointGroupVO);
    }

    /**
     * 创建api分组
     * @param endpointGroupDTO {@linkplain EndpointGroupDTO }
     * @return {@linkplain ServiceResult }
     */
    @PostMapping
    public ServiceResult addEndpointGroup(@RequestBody @Valid final EndpointGroupDTO endpointGroupDTO) {
        if(endpointGroupService.save(endpointGroupDTO)) {
            return ServiceResult.success();
        }
        return ServiceResult.fail("");
    }

    /**
     * 修改api分组
     * @param id id
     * @param endpointGroupDTO {@linkplain EndpointGroupDTO }
     * @return {@linkplain ServiceResult }
     */
    @PutMapping("/{id}")
    public ServiceResult updateEndpointGroup(@PathVariable("id") final Long id,
                                             @RequestBody @Valid final EndpointGroupDTO endpointGroupDTO) {
        endpointGroupDTO.setId(id);
        if(endpointGroupService.save(endpointGroupDTO)) {
            return ServiceResult.success();
        }
        return ServiceResult.fail("");
    }

    /**
     * 删除api分组
     * @param id id
     * @return {@linkplain ServiceResult }
     */
    @DeleteMapping("/{id}")
    public ServiceResult delete(@PathVariable("id") final Long id) {
        endpointGroupService.delete(id);
        return ServiceResult.success();
    }


    /**
     * 删除api分组
     * @param ids
     * @return {@linkplain ServiceResult }
     */
    @DeleteMapping("/batch")
    public ServiceResult batchDelete(@RequestBody final List<Long> ids) {
        endpointGroupService.batchDelete(ids);
        return ServiceResult.success();
    }
}
