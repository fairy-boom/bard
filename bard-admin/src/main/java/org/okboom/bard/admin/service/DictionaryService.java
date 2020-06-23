package org.okboom.bard.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.okboom.bard.admin.domain.Dictionary;
import org.okboom.bard.admin.dto.DictDTO;

import java.util.List;

/**
 * @author tookbra
 */
public interface DictionaryService {

    /**
     * 分页查询字典
     * @param name 字典名称
     * @param code 字典编码
     * @param page {@linkplain Page}
     * @return {@linkplain IPage}
     */
    IPage<Dictionary> listByPage(final String name, final String code, final Page page);

    /**
     * 分页查询二级字典
     * @param parentId 父字典id
     * @param page {@linkplain Page}
     * @return {@linkplain IPage}
     */
    IPage<Dictionary> listChildByPage(final Long parentId, final Page page);

    /**
     * 查询字典详情
     * @param id 字典id
     * @return {@linkplain IPage}
     */
    Dictionary findById(final Long id);

    /**
     * 增加或修改字典
     * @param dictDTO {@linkplain DictDTO}
     * @return true or false
     */
    boolean save(DictDTO dictDTO);

    /**
     * 删除字典
     * @param id 字典id
     */
    void delete(Long id);

    /**
     * 批量删除字典
     * @param ids 字典id列表
     */
    void batchDelete(List<Long> ids);
}
