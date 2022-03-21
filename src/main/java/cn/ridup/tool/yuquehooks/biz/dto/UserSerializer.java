package cn.ridup.tool.yuquehooks.biz.dto;

import java.io.Serializable;

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

    /**
     * 用户编号
     */
    private String id;

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
    private String avatar_url;

    /**
     * 创建时间
     */
    private String created_at;

    /**
     * 更新时间
     */
    private String updated_at;

}
