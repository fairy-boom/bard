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
@TableName("endpoint")
public class Endpoint extends BaseDomain {
}
