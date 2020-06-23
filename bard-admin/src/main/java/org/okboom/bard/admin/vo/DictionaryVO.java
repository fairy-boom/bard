package org.okboom.bard.admin.vo;

import java.io.Serializable;

/**
 * dict vo
 * @author tookbra
 */
public class DictionaryVO implements Serializable {
    private static final long serialVersionUID = 6202685163778171417L;

    /**
     * id
     */
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 字典值
     */
    private Integer dictKey;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}
