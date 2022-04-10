package cn.ridup.tool.yuquehooks.service.convertor;

import cn.ridup.tool.yuquehooks.integration.request.PostEditorType;
import cn.ridup.tool.yuquehooks.integration.request.PostParam;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailSerializer;
import lombok.extern.slf4j.Slf4j;

/**
 * for convert the data from yuque to halo
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:23
 */
@Slf4j
public class HaloDataConvertor {

    public static PostParam convert(DocDetailSerializer dto) {

        if (dto == null) {
            return null;
        }
        PostParam postParam = new PostParam();
        // postParam.setTagIds();
        // postParam.setCategoryIds();
        postParam.setTitle(dto.getTitle());
        if (dto.getStatus() == 1 && dto.getPublicFlag() == 1) {
            postParam.setStatus(PostStatus.PUBLISHED);
        } else {
            postParam.setStatus(PostStatus.DRAFT);
        }

        postParam.setSlug(dto.getSlug());
        // postParam.setPassword();
        postParam.setEditorType(PostEditorType.MARKDOWN);
        postParam.setContent(dto.getBodyHtml());
        postParam.setOriginalContent(dto.getBody());
        postParam.setSummary(dto.getBody()
            .substring(0, Math.min(dto.getBody()
                .length(), 150)));
        // postParam.setThumbnail();
        // postParam.setDisallowComment();
        // postParam.setTemplate();
        // postParam.setTopPriority();
        postParam.setCreateTime(dto.getCreatedAt()
            .getTime());
        // postParam.setMetaKeywords();
        // postParam.setMetaDescription();
        postParam.setKeepRaw(true);
        return postParam;
    }
}
