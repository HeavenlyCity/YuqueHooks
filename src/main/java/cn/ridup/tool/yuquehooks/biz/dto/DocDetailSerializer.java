package cn.ridup.tool.yuquehooks.biz.dto;

import java.io.Serializable;

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
    private String book_id;

    /**
     * 仓库信息 <BookSerializer>，就是 repo 信息
     */
    private BookSerializer book;

    /**
     * 用户/团队编号
     */
    private String user_id;

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
    private String body_draft;

    /**
     * 转换过后的正文 HTML （重大变更，详情请参考：https://www.yuque.com/yuque/developer/yr938f）
     */
    private String body_html;

    /**
     * 语雀 lake 格式的文档内容
     */
    private String body_lake;

    /**
     * 文档创建人 User Id
     */
    private String creator_id;

    /**
     *  公开级别 [0 - 私密, 1 - 公开]
     */
    // private String public;

    /**
     * 状态 [0 - 草稿, 1 - 发布]
     */
    private String status;

    /**
     * 赞数量
     */
    private String likes_count;

    /**
     * 评论数量
     */
    private String comments_count;

    /**
     * 文档内容更新时间
     */
    private String content_updated_at;

    /**
     * 删除时间，未删除为 null
     */
    private String deleted_at;

    /**
     * 创建时间
     */
    private String created_at;

    /**
     * 更新时private String
     */
    private String updated_at;

}

