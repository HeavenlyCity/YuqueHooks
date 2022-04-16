package cn.ridup.tool.yuquehooks.service.dto;

import java.io.Serializable;
import java.util.Date;

import cn.ridup.tool.yuquehooks.service.enumeration.EnumWebhookSubjectType;
import lombok.Data;

/**
 * 文档详细信息
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 16:57
 */
@Data
public class DocDetailSerializer implements Serializable {
    private static final long serialVersionUID = -2815673608883713968L;
    /**
     * 文档路径
     */
    private String slug;

    /**
     * 标题
     */
    private String title;

    /**
     * 仓库编号，就是 repo_id
     */
    private Integer bookId;

    /**
     * 仓库信息 <BookSerializer>，就是 repo 信息
     */
    private BookSerializer book;

    /**
     * 用户/团队编号
     */
    private Integer userId;

    /**
     * 用户/团队信息 <UserSerializer>
     */
    private UserSerializer user;

    /**
     * 描述了正文的格式 [lake , markdown]
     */
    private String format;

    /**
     * 正文 Markdown 源代码
     */
    private String body;

    /**
     * 草稿 Markdown 源代码
     */
    private String bodyDraft;

    /**
     * 转换过后的正文 HTML （重大变更，详情请参考：https://www.yuque.com/yuque/developer/yr938f）
     */
    private String bodyHtml;

    /**
     * 语雀 lake 格式的文档内容
     */
    private String bodyLake;

    /**
     * 文档创建人 User Id
     */
    private Integer creatorId;

    /**
     * 公开级别 [0 - 私密, 1 - 公开]
     */
    private Integer publicFlag;

    /**
     * 状态 [0 - 草稿, 1 - 发布]
     */
    private Integer status;

    /**
     * 赞数量
     */
    private Integer likesCount;

    /**
     * 评论数量
     */
    private Integer commentsCount;

    /**
     * 文档内容更新时间
     */
    private Date contentUpdatedAt;

    /**
     * 删除时间，未删除为 null
     */
    private Date deletedAt;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时private String
     */
    private Date updatedAt;

    /**
     * doc path
     */
    private String path;

    /**
     * action type
     */
    private EnumWebhookSubjectType actionType;
    /**
     * webhook subject type
     */
    private EnumWebhookSubjectType webhookSubjectType;

}

