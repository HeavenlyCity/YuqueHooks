package cn.ridup.tool.yuquehooks.biz.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 文档详情对象
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 16:57
 */
@Data
public class DocDetailDto implements Serializable {
    private static final long serialVersionUID = 3436112718845802296L;

    private DocDetailSerializer data;

}
