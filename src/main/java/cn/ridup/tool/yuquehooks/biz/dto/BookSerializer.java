package cn.ridup.tool.yuquehooks.biz.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 一般在列表的场景返回的仓库信息。
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 18:26
 */
@Data
public class BookSerializer implements Serializable {
    private static final long serialVersionUID = -2644927913171649446L;

    /**
     * 仓库编号
     */
    private String id;

    /**
     * 类型 [Book - 文档]
     */
    private String type;

    /**
     * 仓库路径
     */
    private String slug;

    /**
     * 名称
     */
    private String name;

    /**
     * 仓库完整路径 user.login/book.slug
     */
    private String namespace;

    /**
     * 所属的团队/用户编号
     */
    private String user_id;

    /**
     * <UserSerializer>
     */
    private UserSerializer user;

    /**
     * 介绍
     */
    private String description;

    /**
     * 创建人 User Id
     */
    private String creator_id;

    /**
     *  公开状态 [1 - 公开, 0 - 私密]
     */
    // private String public;

    /**
     * 喜欢数量
     */
    private String likes_count;

    /**
     * 订阅数量
     */
    private String watches_count;

    /**
     * 创建时间
     */
    private String created_at;

    /**
     * 更新时间
     */
    private String updated_at;

}
