package cn.ridup.tool.yuquehooks.service.dto;

import java.io.Serializable;
import java.util.Date;

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
    private Integer id;

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
    private Integer userId;

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
    private Integer creatorId;

    /**
     *  公开状态 [1 - 公开, 0 - 私密]
     */
    private Integer publicFlag;

    /**
     * 喜欢数量
     */
    private Integer likesCount;

    /**
     * 订阅数量
     */
    private Integer watchesCount;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}
