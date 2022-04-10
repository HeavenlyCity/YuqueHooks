package cn.ridup.tool.yuquehooks.integration.request;

import java.util.Set;

import lombok.Data;

/**
 * Post query
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/10 20:35
 */
@Data
public class QueryPostListRequestDto {

    /**
     * Keyword.
     */
    private String keyword;

    /**
     * Post status.
     */
    @Deprecated(forRemoval = true, since = "1.5.0")
    private PostStatus status;

    /**
     * Post statuses.
     */
    private Set<PostStatus> statuses;

    /**
     * Category id.
     */
    private Integer categoryId;

    private Integer page;

    private Integer size;

}
