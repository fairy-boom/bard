package org.okboom.bard.admin.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
public class EndpointGroupQuery implements Serializable {
    private static final long serialVersionUID = -2262207314899246218L;

    /**
     * 分组名
     */
    private String name;
    /**
     * url前缀
     */
    private Integer prefixUrl;
    /**
     * 分页参数
     */
    private Page page;
}
