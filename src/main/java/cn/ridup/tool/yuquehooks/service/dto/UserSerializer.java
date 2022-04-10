package cn.ridup.tool.yuquehooks.service.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 一般在列表的场景返回的用户信息。
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 18:23
 */
@Data
public class UserSerializer implements Serializable {

    private static final long serialVersionUID = 8187094795049536829L;

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 类型 [`User`  - 用户, Group - 团队]
     */
    private String type;

    /**
     * 用户个人路径
     */
    private String login;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像 URL
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}
