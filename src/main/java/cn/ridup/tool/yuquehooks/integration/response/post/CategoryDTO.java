package cn.ridup.tool.yuquehooks.integration.response.post;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Category output dto.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:19
 */
@Data
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private Integer id;

    private String name;

    private String slug;

    private String description;

    private String thumbnail;

    private Integer parentId;

    private String password;

    private Date createTime;

    private String fullPath;

    private Integer priority;
}
