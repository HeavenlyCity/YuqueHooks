package cn.ridup.tool.yuquehooks.integration.response;

import lombok.Data;

/**
 * halo common response dto
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/4/7 15:41
 */
@Data
public class HaloCommonDto<T> implements java.io.Serializable {
    private static final long serialVersionUID = -8298179928174111901L;

    private Integer status;

    private String message;

    private String devMessage;

    private T data;

}
