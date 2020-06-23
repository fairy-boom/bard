package org.okboom.bard.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.okboom.bard.admin.domain.Dictionary;
import org.okboom.bard.admin.dto.DictDTO;
import org.okboom.bard.admin.service.DictionaryService;
import org.okboom.bard.admin.vo.DictionaryVO;
import org.okboom.bard.admin.vo.PluginVO;
import org.okboom.bard.admin.wrapper.DictionaryWrapper;
import org.okboom.yuumi.tool.data.ServiceResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author tookbra
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dictionaries")
public class DictionaryController {

    private final DictionaryService dictService;


    /**
     * 分页查询一级字典
     * @param name 字典名称
     * @param code 字典编号
     * @param page {@linkplain Page}
     * @return {@linkplain ServiceResult}
     */
    @GetMapping
    public ServiceResult queryParentList(final String name, final String code, final Page page) {
        IPage<Dictionary> pages = dictService.listByPage(name, code, page);
        IPage<PluginVO> result = DictionaryWrapper.INSTANCE.convertPageVO(page);
        return ServiceResult.success(result);
    }

    /**
     * 分页查询二级字典
     * @param parentId 父字典id
     * @return {@linkplain ServiceResult}
     */
    @GetMapping("/{parentId}")
    public ServiceResult queryChildList(@PathVariable("parentId") final Long parentId, final Page page) {
        IPage<Dictionary> pages = dictService.listChildByPage(parentId, page);
        IPage<PluginVO> result = DictionaryWrapper.INSTANCE.convertPageVO(page);
        return ServiceResult.success(result);
    }

    /**
     * 字典详情
     * @param id 字典id
     * @return {@linkplain ServiceResult}
     */
    @GetMapping("/children/{id}")
    public ServiceResult getChild(@PathVariable("id") final Long id) {
        DictionaryVO dictVO = DictionaryWrapper.INSTANCE.convertToTarget(dictService.findById(id));
        return ServiceResult.success(dictVO);
    }

    /**
     * 增加字典
     * @param dictDTO {@linkplain DictDTO}
     * @return {@linkplain ServiceResult}
     */
    @PostMapping
    public ServiceResult addDict(@RequestBody @Valid final DictDTO dictDTO) {
        dictService.save(dictDTO);
        if(dictService.save(dictDTO)) {
            return ServiceResult.success();
        }
        return ServiceResult.fail("");
    }

    /**
     * 修改字典
     * @param dictDTO {@linkplain DictDTO}
     * @return {@linkplain ServiceResult}
     */
    @PutMapping("/children/{id}")
    public ServiceResult updateDict(@PathVariable("id") final Long id, @RequestBody @Valid final DictDTO dictDTO) {
        dictDTO.setId(id);
        if(dictService.save(dictDTO)) {
            return ServiceResult.success();
        }
        return ServiceResult.fail("");
    }

    /**
     * 删除字典
     * @param id 字典id
     * @return
     */
    @Delete("/{id}")
    public ServiceResult delete(@PathVariable("id") final Long id) {
        dictService.delete(id);
        return ServiceResult.success();
    }

    /**
     * 删除字典
     * @param ids 字典id列表
     * @return
     */
    @Delete("/batch")
    public ServiceResult batchDelete(@RequestBody final List<Long> ids) {
        dictService.batchDelete(ids);
        return ServiceResult.success();
    }
}
