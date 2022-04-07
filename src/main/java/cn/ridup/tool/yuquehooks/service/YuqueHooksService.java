package cn.ridup.tool.yuquehooks.service;

import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import cn.ridup.tool.yuquehooks.service.dto.YuqueHooksDto;

/**
 * webhooks 处理
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/5 23:42
 */
public interface YuqueHooksService {

    void hooks(YuqueHooksDto yuqueHooksDto);

}
