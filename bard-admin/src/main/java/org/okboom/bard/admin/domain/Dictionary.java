package org.okboom.bard.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.okboom.yuumi.mybatis.base.BaseDomain;

/**
 * @author tookbra
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dictionary")
public class Dictionary extends BaseDomain {
    private static final long serialVersionUID = 7690121101573970405L;

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
