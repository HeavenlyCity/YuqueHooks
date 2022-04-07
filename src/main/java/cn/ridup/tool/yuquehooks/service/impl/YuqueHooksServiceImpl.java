package cn.ridup.tool.yuquehooks.service.impl;

import javax.annotation.Resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.ridup.tool.yuquehooks.service.YuqueHooksService;
import cn.ridup.tool.yuquehooks.service.convertor.DocDetailConvertor;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import cn.ridup.tool.yuquehooks.service.dto.YuqueHooksDto;

/**
 * the implements of webhooks
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/5 23:43
 */
@Service
public class YuqueHooksServiceImpl implements YuqueHooksService {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void hooks(YuqueHooksDto yuqueHooksDto) {
        DocDetailDto docDetailDto = DocDetailConvertor.convertToDocDetail(yuqueHooksDto);
        System.out.println("docDetailDto:" + docDetailDto.toString());

    }

    // public ResponseEntity<QRCodeResponse> postForEntityWithHeader(String content) {
    //     String url = MXN_HOST + "qrcode/create/logo";
    //     FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/cat.png"));
    //     MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();      //只能是MultiValueMap，不能是Map
    //     params.add("content", content);
    //     params.add("size", 400);
    //     params.add("logo_size", 120);
    //     params.add("type", 0);
    //     params.add("logo_img", resource);
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("app_id", MXN_APP_ID);
    //     headers.add("app_secret", MXN_APP_SECRET);
    //     HttpEntity httpEntity = new HttpEntity(params, headers);      //将Post的body和header塞进HttpEntity
    //     return restTemplate.postForEntity(url, httpEntity, QRCodeResponse.class);
    // }

}
