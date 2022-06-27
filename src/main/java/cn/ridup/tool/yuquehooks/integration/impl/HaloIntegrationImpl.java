package cn.ridup.tool.yuquehooks.integration.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import cn.ridup.tool.yuquehooks.cache.AbstractStringCacheStore;
import cn.ridup.tool.yuquehooks.config.properties.YuqueProperties;
import cn.ridup.tool.yuquehooks.integration.HaloIntegration;
import cn.ridup.tool.yuquehooks.integration.request.HaloLoginRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.HaloPostRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
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

    @Autowired
    private YuqueProperties yuqueProperties;

    private static final String USERNAME = "ridup";

    private static final String PASSWORD = "";

    private static final String HALO_HOST = "https://ridup.cn";

    private static final String HALO_ADMIN_TOKEN_CACHE_KEY = "halo_admin_token_cache_key";

    private static final String HALO_REQUEST_HEADER_TOKEN_KEY = "ADMIN-Authorization";

    /** <a href="https://api.halo.run/admin-api.html#operation/authUsingPOST">Creates a post</a> */
    private static final String HALO_LOGIN = "/api/admin/login";

    /** <a href="https://api.halo.run/admin-api.html#operation/createByUsingPOST_7">Creates a post</a> */
    private static final String CREATES_POST = "/api/admin/posts";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateByUsingPUT_8">Updates a post</a> */
    private static final String UPDATES_POST = "/api/admin/posts/";

    /** <a href="https://api.halo.run/admin-api.html#operation/updateStatusByUsingPUT_2">Updates post status</a> :/api/admin/posts/{postId}/status/{status} */
    private static final String UPDATES_POST_STATUS = "/api/admin/posts/";

    /** <a href="https://api.halo.run/admin-api.html#operation/pageByUsingGET_6"> Lists posts </a> */
    private static final String LISTS_POSTS = "/api/admin/posts";

    public HaloIntegrationImpl(AbstractStringCacheStore cacheStore) {this.cacheStore = cacheStore;}

    public <E, T> HaloCommonDto<T> fetchForEntityWithHeader(String path, HttpMethod method, E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        Assert.notNull(yuqueProperties.getHalo()
            .getHost(), "halo host is null");
        String url = yuqueProperties.getHalo()
            .getHost() + path;
        HttpEntity<E> httpEntity = new HttpEntity<>(request, getDefaultHttpHeader(!HALO_LOGIN.equals(path)));
        ResponseEntity<HaloCommonDto<T>> haloCommonDtoResponseEntity = restTemplate.exchange(url, method, httpEntity,
            type);
        if (null != haloCommonDtoResponseEntity.getBody() && haloCommonDtoResponseEntity.getBody()
            .getStatus()
            .equals(HttpStatus.UNAUTHORIZED.value())) {
            login(yuqueProperties.getHalo()
                .getUsername(), yuqueProperties.getHalo()
                .getPassword());
        }
        return haloCommonDtoResponseEntity.getBody();
    }

    public <E, T> HaloCommonDto<T> postForEntityWithHeader(String path, E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path, HttpMethod.POST, request, type);
    }

    public <E, T> HaloCommonDto<T> putForEntityWithHeader(String path, E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path, HttpMethod.PUT, request, type);
    }

    public <E, T> HaloCommonDto<T> getForEntityWithHeader(String path, E request,
        ParameterizedTypeReference<HaloCommonDto<T>> type) {
        return fetchForEntityWithHeader(path, HttpMethod.GET, request, type);
    }

    /**
     * 获取默认的请求头
     *
     * @param tokenCheck 是否需要检查token
     * @return HttpHeaders 请求头
     */
    public HttpHeaders getDefaultHttpHeader(boolean tokenCheck) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (tokenCheck) {
            if (cacheStore.get(HALO_ADMIN_TOKEN_CACHE_KEY)
                .isEmpty()) {
                Assert.notNull(yuqueProperties.getHalo()
                    .getUsername(), "halo  username is null");
                Assert.notNull(yuqueProperties.getHalo()
                    .getPassword(), "halo  password is null");
                login(yuqueProperties.getHalo()
                    .getUsername(), yuqueProperties.getHalo()
                    .getPassword());
            }
            headers.add(HALO_REQUEST_HEADER_TOKEN_KEY, cacheStore.get(HALO_ADMIN_TOKEN_CACHE_KEY)
                .get());
        }
        return headers;
    }

    @Override
    public HaloLoginResponseDto login(String username, String password) {
        HaloLoginRequestDto haloLoginRequestDto = new HaloLoginRequestDto();
        haloLoginRequestDto.setUsername(username);
        haloLoginRequestDto.setPassword(password);
        HaloCommonDto<HaloLoginResponseDto> haloLoginResponseDtoHaloCommonDto = postForEntityWithHeader(HALO_LOGIN,
            haloLoginRequestDto, new ParameterizedTypeReference<HaloCommonDto<HaloLoginResponseDto>>() {
            });
        HaloLoginResponseDto data = haloLoginResponseDtoHaloCommonDto.getData();
        cacheStore.put(HALO_ADMIN_TOKEN_CACHE_KEY, data.getAccessToken(), data.getExpiredIn(), TimeUnit.SECONDS);
        return haloLoginResponseDtoHaloCommonDto.getData();
    }

    @Override
    public HaloPostResponseDto createPost(HaloPostRequestDto requestDto) {
        HaloCommonDto<HaloPostResponseDto> createPostResponseDto = postForEntityWithHeader(CREATES_POST,
            requestDto.getPostParam(), new ParameterizedTypeReference<HaloCommonDto<HaloPostResponseDto>>() {
            });
        return createPostResponseDto.getData();
    }

    @Override
    public HaloPostResponseDto updatePost(HaloPostRequestDto requestDto, Integer postId) {
        HaloCommonDto<HaloPostResponseDto> updatePostResponseDto = putForEntityWithHeader(UPDATES_POST + postId,
            requestDto.getPostParam(), new ParameterizedTypeReference<HaloCommonDto<HaloPostResponseDto>>() {
            });
        return updatePostResponseDto.getData();
    }

    @Override
    public Page<BasePostSimpleDTO> queryPostList(String title, int page, int size) {
        QueryPostListRequestDto requestDto = new QueryPostListRequestDto();
        requestDto.setKeyword(title);
        requestDto.setPage(page);
        requestDto.setSize(size);
        HaloCommonDto<Page<BasePostSimpleDTO>> createPostResponseDto = getForEntityWithHeader(LISTS_POSTS+"?size="+size+"&page="+page, requestDto,
            new ParameterizedTypeReference<HaloCommonDto<Page<BasePostSimpleDTO>>>() {
            });
        return createPostResponseDto.getData();
    }

    @Override
    public BasePostSimpleDTO updateStatusBy(Integer postId, PostStatus status) {
        Assert.notNull(postId, "updateStatusBy's postId is null");
        HaloCommonDto<BasePostSimpleDTO> updatePostResponseDto = putForEntityWithHeader(
            UPDATES_POST_STATUS + postId + "/status/" + status.toString(), null,
            new ParameterizedTypeReference<HaloCommonDto<BasePostSimpleDTO>>() {
            });
        return updatePostResponseDto.getData();
    }
}
