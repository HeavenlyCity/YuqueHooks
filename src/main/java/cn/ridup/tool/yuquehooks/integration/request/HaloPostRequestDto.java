package cn.ridup.tool.yuquehooks.integration.request;

import java.io.Serializable;

import lombok.Data;

/**
 * halo login params
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/4/7 15:39
 */
@Data
public class HaloPostRequestDto implements Serializable {
    private static final long serialVersionUID = -5472762198469103099L;

    private PostParam postParam;

}
