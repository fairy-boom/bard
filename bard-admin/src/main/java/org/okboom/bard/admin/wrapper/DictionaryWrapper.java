package org.okboom.bard.admin.wrapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.okboom.bard.admin.domain.Dictionary;
import org.okboom.bard.admin.dto.DictDTO;
import org.okboom.bard.admin.vo.DictionaryVO;
import org.okboom.yuumi.mybatis.base.BaseWrapper;

/**
 * dict wrapper
 * @author tookbra
 */
@Mapper
public interface DictionaryWrapper extends BaseWrapper<Dictionary, DictionaryVO> {

    DictionaryWrapper INSTANCE = Mappers.getMapper(DictionaryWrapper.class);

    /**
     * dto to bo
     * @param dictDTO {@linkplain DictDTO}
     * @return {@linkplain Dictionary}
     */
    @Mappings({})
    Dictionary convert(DictDTO dictDTO);
}
