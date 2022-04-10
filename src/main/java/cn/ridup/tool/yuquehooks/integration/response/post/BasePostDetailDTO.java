package cn.ridup.tool.yuquehooks.integration.response.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base post detail output dto.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:19
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasePostDetailDTO extends BasePostSimpleDTO {

    private String originalContent;

    private String content;

    private Long commentCount;

}
