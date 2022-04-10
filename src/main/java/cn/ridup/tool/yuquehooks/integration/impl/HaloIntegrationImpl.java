package cn.ridup.tool.yuquehooks.integration.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import cn.ridup.tool.yuquehooks.cache.AbstractStringCacheStore;
import cn.ridup.tool.yuquehooks.integration.HaloIntegration;
import cn.ridup.tool.yuquehooks.integration.request.HaloPostRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.HaloLoginRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.QueryPostListRequestDto;
import cn.ridup.tool.yuquehooks.integration.response.HaloCommonDto;
import cn.ridup.tool.yuquehooks.integration.response.login.HaloLoginResponseDto;
import cn.ridup.tool.yuquehooks.integration.response.post.BasePostSimpleDTO;
import cn.ridup.tool.yuquehooks.integration.response.post.HaloPostResponseDto;
import cn.ridup.tool.yuquehooks.integration.support.Page;

/**
 * halo's remote invoke interface
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 17:35
 */
@Repository
public class HaloIntegrationImpl implements HaloIntegration {

    @Resource
    private RestTemplate restTemplate;

    private final AbstractStringCacheStore cacheStore;

    private static final String USERNAME = "ridup";

    private static final String PASSWORD = "";

    private static final String HALO_ADMIN_TOKEN_CACHE_KEY = "halo_admin_token_cache_key";

    private static final String HALO_REQUEST_HEADER_TOKEN_KEY = "ADMIN-Authorization";

    private static final String HALO_HOST = "https://ridup.cn";

    /** <a href="https://api.halo.run/admin-api.html#operation/authUsingPOST">Creates a post</a> */
    private static final String HALO_LOGIN = "/api/admin/login";

    /** <a href="https://api.halo.run/admin-api.html#operation/createByUsingPOST_7">Creates a post</a> */
    private static final String CREATES_POST = "/api/admin/posts";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateByUsingPUT_8">Updates a post</a> */
    private static final String UPDATES_POST = "/api/admin/posts/";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateStatusByUsingPUT_2">Updates post status</a> */
    private static final String UPDATES_POST_STATUS = "/api/admin/posts/{postId}/status/{status}";

    /** <a href="https://api.halo.run/admin-api.html#operation/pageByUsingGET_6"> Lists posts </a> */
    private static final String LISTS_POSTS = "/api/admin/posts";

    public HaloIntegrationImpl(AbstractStringCacheStore cacheStore) {this.cacheStore = cacheStore;}

    public <E, T> HaloCommonDto<T> fetchForEntityWithHeader(String path,HttpMethod method,E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        String url = HALO_HOST + path;
        HttpEntity<E> httpEntity = new HttpEntity<>(request, getDefaultHttpHeader(!HALO_LOGIN.equals(path)));
        ResponseEntity<HaloCommonDto<T>> haloCommonDtoResponseEntity = restTemplate.exchange(url, method,
            httpEntity, type);
        return haloCommonDtoResponseEntity.getBody();
    }

    public <E, T> HaloCommonDto<T> postForEntityWithHeader(String path,E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path,HttpMethod.POST,request,type);
    }

    public <E, T> HaloCommonDto<T> putForEntityWithHeader(String path,E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path,HttpMethod.PUT,request,type);
    }

    public <E, T> HaloCommonDto<T> getForEntityWithHeader(String path,E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path,HttpMethod.GET,request,type);
    }

    /**
     * 获取默认的请求头
     * @param tokenCheck 是否需要检查token
     * @return HttpHeaders 请求头
     */
    public HttpHeaders getDefaultHttpHeader(boolean tokenCheck) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        if(tokenCheck){
            if(cacheStore.get(HALO_ADMIN_TOKEN_CACHE_KEY)
                .isEmpty()) {
                login(USERNAME,PASSWORD);
            }
            headers.add(HALO_REQUEST_HEADER_TOKEN_KEY, cacheStore.get(HALO_ADMIN_TOKEN_CACHE_KEY).get());
        }
        return headers;
    }

    @Override
    public HaloLoginResponseDto login(String username, String password) {
        HaloLoginRequestDto haloLoginRequestDto = new HaloLoginRequestDto();
        haloLoginRequestDto.setUsername(username);
        haloLoginRequestDto.setPassword(password);
        HaloCommonDto<HaloLoginResponseDto> haloLoginResponseDtoHaloCommonDto = postForEntityWithHeader(
            HALO_LOGIN,haloLoginRequestDto, new ParameterizedTypeReference<>() {
            });
        HaloLoginResponseDto data = haloLoginResponseDtoHaloCommonDto.getData();
        cacheStore.put(HALO_ADMIN_TOKEN_CACHE_KEY, data.getAccessToken(), data.getExpiredIn(), TimeUnit.SECONDS);
        return haloLoginResponseDtoHaloCommonDto.getData();
    }

    @Override
    public HaloPostResponseDto createPost(HaloPostRequestDto requestDto) {
        HaloCommonDto<HaloPostResponseDto> createPostResponseDto = postForEntityWithHeader(
            CREATES_POST,requestDto.getPostParam(), new ParameterizedTypeReference<>() {
            });
        return createPostResponseDto.getData();
    }

    @Override
    public HaloPostResponseDto updatePost(HaloPostRequestDto requestDto,Integer postId) {
        HaloCommonDto<HaloPostResponseDto> updatePostResponseDto = putForEntityWithHeader(
            UPDATES_POST+postId,requestDto.getPostParam(), new ParameterizedTypeReference<>() {
            });
        return updatePostResponseDto.getData();
    }

    @Override
    public Page<BasePostSimpleDTO> queryPostList(String title,int page,int size) {
        QueryPostListRequestDto requestDto = new QueryPostListRequestDto();
        requestDto.setKeyword(title);
        requestDto.setPage(page);
        requestDto.setSize(size);
        HaloCommonDto<Page<BasePostSimpleDTO>> createPostResponseDto = getForEntityWithHeader(
            LISTS_POSTS,requestDto, new ParameterizedTypeReference<>() {
            });
        return createPostResponseDto.getData();
    }

}
