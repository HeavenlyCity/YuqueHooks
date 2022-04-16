package cn.ridup.tool.yuquehooks.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.ridup.tool.yuquehooks.config.properties.YuqueProperties;
import cn.ridup.tool.yuquehooks.integration.HaloIntegration;
import cn.ridup.tool.yuquehooks.integration.request.HaloPostRequestDto;
import cn.ridup.tool.yuquehooks.integration.request.PostParam;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
import cn.ridup.tool.yuquehooks.integration.response.post.BasePostSimpleDTO;
import cn.ridup.tool.yuquehooks.integration.support.Page;
import cn.ridup.tool.yuquehooks.service.YuqueHooksService;
import cn.ridup.tool.yuquehooks.service.convertor.DocDetailConvertor;
import cn.ridup.tool.yuquehooks.service.convertor.HaloDataConvertor;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailSerializer;
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
    private HaloIntegration haloIntegration;

    @Resource
    private YuqueProperties yuqueProperties;

    @Override
    public void hooks(YuqueHooksDto yuqueHooksDto) {
        DocDetailDto docDetailDto = DocDetailConvertor.convertToDocDetail(yuqueHooksDto);
        log.info("the docDetailDto is {}", docDetailDto);
        Assert.notNull(docDetailDto, "yuque doc detail must not be null");
        DocDetailSerializer data = docDetailDto.getData();
        Assert.notNull(data, "yuque doc detail data must not be null");
        Page<BasePostSimpleDTO> postList = haloIntegration.queryPostList(data.getTitle(), 0, 1000);
        boolean isExist = false;
        Integer postId = null;
        if (postList.getTotal() > 0) {
            isExist = postList.getContent()
                .stream()
                .anyMatch(basePostSimpleDTO -> basePostSimpleDTO.getSlug()
                    .equals(data.getSlug()));
            if(isExist){
                BasePostSimpleDTO first = postList.getContent()
                    .stream()
                    .filter(basePostSimpleDTO -> basePostSimpleDTO.getSlug()
                        .equals(data.getSlug()))
                    .findFirst()
                    .orElseThrow();
                postId = first.getId();
            }
        }

        PostParam postParam = HaloDataConvertor.convert(data, yuqueProperties.getHalo());

        switch(data.getWebhookSubjectType()){
            case NEW_REVIEW:
            case COMPLETE_REVIEW:
            case CANCEL_REVIEW:
                haloIntegration.updateStatusBy(postId, postParam.getStatus());
                break;
            case PUBLISH:
            case UPDATE:
            default:
                HaloPostRequestDto requestDto = new HaloPostRequestDto();
                requestDto.setPostParam(postParam);
                if (isExist) {
                    haloIntegration.updatePost(requestDto,postId );
                } else {
                    haloIntegration.createPost(requestDto);
                }
        }


    }

}
