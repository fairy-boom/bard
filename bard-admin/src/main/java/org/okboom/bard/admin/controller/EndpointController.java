package org.okboom.bard.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.okboom.bard.admin.domain.Endpoint;
import org.okboom.bard.admin.query.EndpointQuery;
import org.okboom.bard.admin.service.EndpointService;
import org.okboom.bard.admin.vo.EndpointVO;
import org.okboom.bard.admin.wrapper.EndpointWrapper;
import org.okboom.yuumi.tool.data.ServiceResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tookbra
 */
@RestController
@AllArgsConstructor
@RequestMapping("/endpoints")
public class EndpointController {

    private final EndpointService endpointService;

    /**
     * 分页查询api
     * @param endpointQuery {@linkplain EndpointQuery}
     * @return {@linkplain ServiceResult}
     */
    @GetMapping
    public ServiceResult queryEndpoint(@RequestBody final EndpointQuery endpointQuery) {
        IPage<Endpoint> page = endpointService.listByPage(endpointQuery);
        IPage<EndpointVO> result = EndpointWrapper.INSTANCE.convertPageVO(page);
        return ServiceResult.success(result);
    }
}
