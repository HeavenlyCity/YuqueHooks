package cn.ridup.tool.yuquehooks.service.constant;

/**
 * DocDetailSerializerKey
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/6 18:36
 */
public class DocDetailSerializerKey {

    /** 文档编号 */
    public static final String ID = "id";
    /** 文档路径 */
    public static final String SLUG = "slug";
    /** 标题 */
    public static final String TITLE = "title";
    /** 仓库编号，就是 repo_id */
    public static final String BOOK_ID = "book_id";
    /** 仓库信息 <BookSerializer>，就是 repo 信息 {@link BookSerializerKey} */
    public static final String BOOK = "book";
    /** 用户/团队编号 */
    public static final String USER_ID = "user_id";
    /** 用户/团队信息 <UserSerializer>  {@link UserSerializerKey}*/
    public static final String USER = "user";
    /** 描述了正文的格式 [lake , markdown] */
    public static final String FORMAT = "format";
    /** 正文 Markdown 源代码 */
    public static final String BODY = "body";
    /** 草稿 Markdown 源代码 */
    public static final String BODY_DRAFT = "body_draft";
    /** 转换过后的正文 HTML （重大变更，详情请参考：https://www.yuque.com/yuque/developer/yr938f） */
    public static final String BODY_HTML = "body_html";
    /** 语雀 lake 格式的文档内容 */
    public static final String BODY_LAKE = "body_lake";
    /** 文档创建人 User Id */
    public static final String CREATOR_ID = "creator_id";
    /** 公开级别 [0 - 私密, 1 - 公开] */
    public static final String PUBLIC = "public";
    /** 状态 [0 - 草稿, 1 - 发布] */
    public static final String STATUS = "status";
    /** 赞数量 */
    public static final String LIKES_COUNT = "likes_count";
    /** 评论数量 */
    public static final String COMMENTS_COUNT = "comments_count";
    /** 文档内容更新时间 */
    public static final String CONTENT_UPDATED_AT = "content_updated_at";
    /** 删除时间，未删除为 null */
    public static final String DELETED_AT = "deleted_at";
    /** 创建时间 */
    public static final String CREATED_AT = "created_at";
    /** 更新时间 */
    public static final String UPDATED_AT = "updated_at";

}
