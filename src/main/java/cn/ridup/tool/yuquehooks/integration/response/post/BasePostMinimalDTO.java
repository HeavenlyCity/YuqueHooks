package cn.ridup.tool.yuquehooks.integration.response.post;

import java.util.Date;

import cn.ridup.tool.yuquehooks.integration.request.PostEditorType;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Base post minimal output dto.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:19
 */
@Data
@ToString
@EqualsAndHashCode
public class BasePostMinimalDTO {

    private Integer id;

    private String title;

    private PostStatus status;

    private String slug;

    private PostEditorType editorType;

    private Date updateTime;

    private Date createTime;

    private Date editTime;

    private String metaKeywords;

    private String metaDescription;

    private String fullPath;
}
