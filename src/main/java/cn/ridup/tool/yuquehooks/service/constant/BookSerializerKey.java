package cn.ridup.tool.yuquehooks.service.constant;

/**
 * BookSerializerKey
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/6 18:42
 */
public class BookSerializerKey {
    /** 仓库编号 */
    public static final String ID = "id";
    /** 类型 [Book - 文档] */
    public static final String TYPE = "type";
    /** 仓库路径 */
    public static final String SLUG = "slug";
    /** 名称 */
    public static final String NAME = "name";
    /** 仓库完整路径 user.login/book.slug */
    public static final String NAMESPACE = "namespace";
    /** 所属的团队/用户编号 */
    public static final String USER_ID = "user_id";
    /** <UserSerializer>  {@link UserSerializerKey} */
    public static final String USER = "user";
    /** 介绍 */
    public static final String DESCRIPTION = "description";
    /** 创建人 User Id */
    public static final String CREATOR_ID = "creator_id";
    /** 公开状态 [1 - 公开, 0 - 私密] */
    public static final String PUBLIC = "public";
    /** 喜欢数量 */
    public static final String LIKES_COUNT = "likes_count";
    /** 订阅数量 */
    public static final String WATCHES_COUNT = "watches_count";
    /** 创建时间 */
    public static final String CREATED_AT = "created_at";
    /** 更新时间 */
    public static final String UPDATED_AT = "updated_at";
}
