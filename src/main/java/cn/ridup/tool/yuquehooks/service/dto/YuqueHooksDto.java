package cn.ridup.tool.yuquehooks.service.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * yuque's hooks request body
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 16:57
 */
@Data
public class YuqueHooksDto implements Serializable {

    private static final long serialVersionUID = 1415784951643342811L;

    private Map<String, Object> data;

}
