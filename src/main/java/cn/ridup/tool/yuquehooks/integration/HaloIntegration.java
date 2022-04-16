package cn.ridup.tool.yuquehooks.integration;

import org.springframework.web.bind.annotation.PathVariable;

import cn.ridup.tool.yuquehooks.integration.request.HaloPostRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
import cn.ridup.tool.yuquehooks.integration.response.login.HaloLoginResponseDto;
import cn.ridup.tool.yuquehooks.integration.response.post.BasePostSimpleDTO;
import cn.ridup.tool.yuquehooks.integration.response.post.HaloPostResponseDto;
import cn.ridup.tool.yuquehooks.integration.support.Page;

/**
 * halo's remote invoke interface
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/4/7 15:01
 */
public interface HaloIntegration {

    HaloLoginResponseDto login(String username, String password);

    HaloPostResponseDto createPost(HaloPostRequestDto requestDto);

    HaloPostResponseDto updatePost(HaloPostRequestDto requestDto,Integer postId);

    Page<BasePostSimpleDTO> queryPostList(String title, int page, int size);

    BasePostSimpleDTO updateStatusBy(Integer postId,PostStatus status);
}
