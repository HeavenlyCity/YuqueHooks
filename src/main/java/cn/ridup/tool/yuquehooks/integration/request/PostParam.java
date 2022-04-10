package cn.ridup.tool.yuquehooks.integration.request;

import java.util.Set;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Post param.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostParam extends BasePostParam {

    private Set<Integer> tagIds;

    private Set<Integer> categoryIds;


}
