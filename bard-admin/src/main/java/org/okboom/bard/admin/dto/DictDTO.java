package org.okboom.bard.admin.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 字典传输类
 * @author tookbra
 */
@Data
@ToString
public class DictDTO implements Serializable {
    private static final long serialVersionUID = 2816685053682952928L;

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
