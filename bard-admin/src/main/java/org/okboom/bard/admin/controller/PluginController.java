package org.okboom.bard.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.okboom.bard.admin.domain.Plugin;
import org.okboom.bard.admin.dto.PluginDTO;
import org.okboom.bard.admin.query.PluginQuery;
import org.okboom.bard.admin.service.PluginService;
import org.okboom.bard.admin.vo.PluginVO;
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
@RequestMapping("/plugins")
public class PluginController {

    private final PluginService pluginService;

    /**
     * query plugin
     * @param pluginQuery
     * @return {@linkplain ServiceResult }
     */
    @GetMapping
    public ServiceResult queryPlugins(@RequestBody final PluginQuery pluginQuery) {
        IPage<Plugin> page = pluginService.listByPage(pluginQuery);
        IPage<PluginVO> result = PluginWrapper.INSTANCE.convertPageVO(page);
        return ServiceResult.success(result);
    }

    /**
     * 获取插件详情
     * @param id 查询id
     * @return {@linkplain ServiceResult }
     */
    @GetMapping("/{id}")
    public ServiceResult getPlugin(@PathVariable("id") final Long id) {
        PluginVO pluginVO = PluginWrapper.INSTANCE.convertToTarget(pluginService.findById(id));
        return ServiceResult.success(pluginVO);
    }

    /**
     * 创建插件
     * @param pluginDTO {@linkplain PluginDTO }
     * @return {@linkplain ServiceResult }
     */
    @PostMapping
    public ServiceResult addPlugin(@RequestBody @Valid final PluginDTO pluginDTO) {
        // todo add plugin
        return ServiceResult.success();
    }

    /**
     * 修改插件信息
     * @param id 插件id
     * @param pluginDTO {@linkplain PluginDTO }
     * @return {@linkplain ServiceResult }
     */
    @PutMapping("/{id}")
    public ServiceResult updatePlugin(@PathVariable("id") final Long id, @RequestBody @Valid final PluginDTO pluginDTO) {
        // todo update plugin
        return ServiceResult.success();
    }

    /**
     * 删除插件
     * @param id 插件id
     * @return {@linkplain ServiceResult }
     */
    @DeleteMapping("/{id}")
    public ServiceResult deletePlugin(@PathVariable("id") final Long id) {
        pluginService.delete(id);
        return ServiceResult.success();
    }


    /**
     * 删除插件
     * @param ids
     * @return {@linkplain ServiceResult }
     */
    @DeleteMapping("/batch")
    public ServiceResult batchDeletePlugins(@RequestBody final List<Long> ids) {
        pluginService.batchDelete(ids);
        return ServiceResult.success();
    }
}
