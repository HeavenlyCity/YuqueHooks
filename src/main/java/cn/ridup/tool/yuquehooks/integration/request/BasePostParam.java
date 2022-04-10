package cn.ridup.tool.yuquehooks.integration.request;

import java.util.Date;

import lombok.Data;

/**
 * Base Post Param
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:06
 */
@Data
public abstract class BasePostParam {

    protected String title;

    protected PostStatus status = PostStatus.DRAFT;

    protected String slug;

    protected String password;

    protected PostEditorType editorType;

    /**
     * 格式化后的内容
     */
    protected String content;

    /**
     * Markdown content
     */
    protected String originalContent;

    /**
     * 前150字符
     */
    protected String summary;

    protected String thumbnail;

    protected Boolean disallowComment = false;

    protected String template;

    protected Integer topPriority = 0;

    protected long createTime;

    protected String metaKeywords;

    protected String metaDescription;

    /**
     * if {@code true}, it means is that do not let the back-end render the original content
     * because the content has been rendered, and you only need to store the original content.
     */
    protected Boolean keepRaw = false;

}
