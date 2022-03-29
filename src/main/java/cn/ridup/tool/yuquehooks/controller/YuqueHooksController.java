package cn.ridup.tool.yuquehooks.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import lombok.extern.slf4j.Slf4j;

/**
 * webhooks 入口
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 16:38
 */
@RestController
@RequestMapping(value ="/yuque")
@Slf4j
public class YuqueHooksController {

    @PostMapping("/hooks")
    public void hooks(@RequestBody DocDetailDto o){
        System.out.println("DocDetailDto="+o.toString());
    }

}
