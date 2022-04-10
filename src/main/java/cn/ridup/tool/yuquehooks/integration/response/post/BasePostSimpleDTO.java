package cn.ridup.tool.yuquehooks.integration.response.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base page simple output dto.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/10 20:31
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasePostSimpleDTO extends BasePostMinimalDTO {

    private String summary;

    private String thumbnail;

    private Long visits;

    private Boolean disallowComment;

    private String password;

    private String template;

    private Integer topPriority;

    private Long likes;

    private Long wordCount;

    private Boolean inProgress;

}
