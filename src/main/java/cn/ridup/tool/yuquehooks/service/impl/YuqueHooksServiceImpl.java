package cn.ridup.tool.yuquehooks.service.impl;

import javax.annotation.Resource;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.ridup.tool.yuquehooks.integration.request.HaloLoginRequestDto;
import cn.ridup.tool.yuquehooks.integration.response.HaloCommonDto;
import cn.ridup.tool.yuquehooks.integration.response.HaloLoginResponseDto;
import cn.ridup.tool.yuquehooks.service.YuqueHooksService;
import cn.ridup.tool.yuquehooks.service.convertor.DocDetailConvertor;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import cn.ridup.tool.yuquehooks.service.dto.YuqueHooksDto;
import lombok.extern.slf4j.Slf4j;

/**
 * the implements of webhooks
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/5 23:43
 */
@Service
@Slf4j
public class YuqueHooksServiceImpl implements YuqueHooksService {

    @Resource
    private RestTemplate restTemplate;

    private static final String HALO_HOST = "https://ridup.cn";

    /** <a href="https://api.halo.run/admin-api.html#operation/authUsingPOST">Creates a post</a> */
    private static final String HALO_LOGIN = "/api/admin/login";

    /** <a href="https://api.halo.run/admin-api.html#operation/createByUsingPOST_7">Creates a post</a> */
    private static final String CREATES_POST = "/api/admin/login";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateByUsingPUT_8">Updates a post</a> */
    private static final String UPDATES_POST = "/api/admin/posts/{postId}";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateStatusByUsingPUT_2">Updates post status</a> */
    private static final String UPDATES_POST_STATUS = "/api/admin/posts/{postId}/status/{status}";

    @Override
    public void hooks(YuqueHooksDto yuqueHooksDto) {
        DocDetailDto docDetailDto = DocDetailConvertor.convertToDocDetail(yuqueHooksDto);
        log.info("the docDetailDto is {}", docDetailDto);
        HaloLoginRequestDto haloLoginRequestDto = new HaloLoginRequestDto();
        haloLoginRequestDto.setUsername("ridup");
        HaloCommonDto<HaloLoginResponseDto> haloLoginResponseDtoHaloCommonDto = postForEntityWithHeader(
            haloLoginRequestDto, HaloLoginResponseDto.class);
        HaloLoginResponseDto data = haloLoginResponseDtoHaloCommonDto.getData();
    }

    public <E, T> HaloCommonDto<T> postForEntityWithHeader(E request, Class<T> entity) {
        String url = HALO_HOST + HALO_LOGIN;
        HttpEntity<E> httpEntity = new HttpEntity(request, getDefaultHttpHeader());
        ParameterizedTypeReference<HaloCommonDto<T>> type = new ParameterizedTypeReference<>() {};
        ResponseEntity<HaloCommonDto<T>> haloCommonDtoResponseEntity = restTemplate.exchange(url, HttpMethod.POST,
            httpEntity, type);
        return haloCommonDtoResponseEntity.getBody();
    }

    /**
     * 获取默认的请求头
     *
     * @return 请求头
     */
    public HttpHeaders getDefaultHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }

}
