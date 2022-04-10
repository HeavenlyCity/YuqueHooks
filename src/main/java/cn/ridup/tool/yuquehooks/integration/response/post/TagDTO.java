package cn.ridup.tool.yuquehooks.integration.response.post;

import java.util.Date;

import lombok.Data;

/**
 * Tag output dto.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:18
 */
@Data
public class TagDTO {

    private Integer id;

    private String name;

    private String slug;

    private String color;

    private String thumbnail;

    private Date createTime;

    private String fullPath;
}
