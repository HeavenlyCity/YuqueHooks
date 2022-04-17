package cn.ridup.tool.yuquehooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ridup.tool.yuquehooks.YuqueHooksApplication;
import cn.ridup.tool.yuquehooks.service.YuqueHooksService;
import cn.ridup.tool.yuquehooks.service.dto.YuqueHooksDto;
import lombok.extern.slf4j.Slf4j;

/**
 * webhooks
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 16:38
 */
@RestController
@RequestMapping(value = "/yuque")
@Slf4j
public class YuqueHooksController {

    @Autowired
    private YuqueHooksService yuqueHooksService;

    @PostMapping("/hooks")
    public void hooks(@RequestBody YuqueHooksDto dto) {
        yuqueHooksService.hooks(dto);
    }



}
