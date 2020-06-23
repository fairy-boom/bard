package org.okboom.bard.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.okboom.bard.admin.domain.Dictionary;
import org.okboom.bard.admin.dto.DictDTO;
import org.okboom.bard.admin.mapper.DictMapper;
import org.okboom.bard.admin.service.DictionaryService;
import org.okboom.bard.admin.wrapper.DictionaryWrapper;
import org.okboom.yuumi.mybatis.base.BaseServiceImpl;
import org.okboom.yuumi.tool.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tookbra
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<DictMapper, Dictionary> implements DictionaryService {

    @Override
    public IPage<Dictionary> listByPage(final String name, final String code, final Page page) {
        return page(page,
                new QueryWrapper<Dictionary>().lambda()
                        .like(Dictionary::getDictName, name)
                        .like(Dictionary::getCode, code)
                        .eq(Dictionary::getParentId, 0)
                        .orderByDesc(Dictionary::getSort));
    }

    @Override
    public IPage<Dictionary> listChildByPage(Long parentId, Page page) {
        return page(page,
                new QueryWrapper<Dictionary>().lambda()
                        .eq(Dictionary::getParentId, parentId)
                        .orderByDesc(Dictionary::getSort));
    }

    @Override
    public Dictionary findById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictDTO dictDTO) {
        int count = count(
                new QueryWrapper<Dictionary>().lambda()
                        .eq(Dictionary::getCode, dictDTO.getCode())
                        .eq(Dictionary::getDictKey, dictDTO.getDictKey()));
        if(count > 0) {
            throw new ServiceException("当前键值已存在");
        }
        Dictionary dict = DictionaryWrapper.INSTANCE.convert(dictDTO);
        return saveOrUpdate(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids) {
        removeByIds(ids);
    }
}
