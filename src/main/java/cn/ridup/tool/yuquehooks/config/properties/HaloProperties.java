package cn.ridup.tool.yuquehooks.config.properties;

import cn.ridup.tool.yuquehooks.service.enumeration.EnumPublishOn;
import lombok.Data;

/**
 * halo configuration properties.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
@Data
public class HaloProperties {

    private String username;

    private String password;

    private String host;

    private String port;

    /** {@link EnumPublishOn } */
    private String publishOn = "publish";

}
