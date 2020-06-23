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
public class EndpointQuery implements Serializable {
    private static final long serialVersionUID = -2262207314899246218L;

    /**
     * 分组名
     */
    private String name;
    /**
     * url
     */
    private String url;
    /**
     * 分页参数
     */
    private Page page;
}
