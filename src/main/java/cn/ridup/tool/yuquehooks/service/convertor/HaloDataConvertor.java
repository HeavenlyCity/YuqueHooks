package cn.ridup.tool.yuquehooks.service.convertor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.ridup.tool.yuquehooks.config.properties.HaloProperties;
import cn.ridup.tool.yuquehooks.integration.request.PostEditorType;
import cn.ridup.tool.yuquehooks.integration.request.PostParam;
import cn.ridup.tool.yuquehooks.integration.request.PostStatus;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailSerializer;
import cn.ridup.tool.yuquehooks.service.enumeration.EnumPublishOn;
import cn.ridup.tool.yuquehooks.service.enumeration.ValueEnum;
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

    public static PostParam convert(DocDetailSerializer dto, HaloProperties halo) {

        if (dto == null) {
            return null;
        }
        PostParam postParam = new PostParam();
        // postParam.setTagIds();
        // postParam.setCategoryIds();
        postParam.setTitle(dto.getTitle());

        EnumPublishOn publishOn = ValueEnum.valueToEnum(EnumPublishOn.class, halo.getPublishOn());
        // webhook_subject_type
        if (publishOn == EnumPublishOn.PUBLISH) {
            if (Integer.valueOf(1)
                .equals(dto.getStatus()) && Integer.valueOf(1)
                .equals(dto.getPublicFlag())) {
                switch (dto.getWebhookSubjectType()) {
                    case PUBLISH:
                        postParam.setStatus(PostStatus.PUBLISHED);
                        break;
                    case UPDATE:
                        postParam.setStatus(PostStatus.PUBLISHED);
                        break;
                    default:
                        postParam.setStatus(PostStatus.DRAFT);
                        log.error("unknown webhook subject type: {}", dto.getWebhookSubjectType());
                }
            } else {
                postParam.setStatus(PostStatus.DRAFT);
            }
        }

        if (publishOn == EnumPublishOn.REVIEW) {
            switch (dto.getWebhookSubjectType()) {
                case NEW_REVIEW:
                    postParam.setStatus(PostStatus.DRAFT);
                    break;
                case COMPLETE_REVIEW:
                    postParam.setStatus(PostStatus.PUBLISHED);
                    break;
                case CANCEL_REVIEW:
                    postParam.setStatus(PostStatus.DRAFT);
                    break;
                default:
                    postParam.setStatus(PostStatus.DRAFT);
                    log.error("unknown webhook subject type: {}", dto.getWebhookSubjectType());
            }
        }

        postParam.setSlug(dto.getSlug());
        // postParam.setPassword();
        postParam.setEditorType(PostEditorType.MARKDOWN);
        if (StringUtils.isNotBlank(dto.getBodyHtml())) {
            String bodyHtml = dto.getBodyHtml();

            Pattern p = Pattern.compile("<div [^>]*class=\"ne-thirdparty\"(<div[^>]*>.*?</div>|.)*?</div>");
            Matcher matcher = p.matcher(bodyHtml);
            if (matcher.find()) {
                String musicDiv = matcher.group();
                Pattern musicPattern = Pattern.compile("https://music.163.com[^\"]*");
                Matcher musicMatcher = musicPattern.matcher(musicDiv);
                if (musicMatcher.find()) {
                    String musicUrl = musicMatcher.group();
                    bodyHtml = bodyHtml.replace(musicDiv,
                        "<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" height=86 src=\""
                            + musicUrl + "\" style=\"width: -webkit-fill-available;\"></iframe>\n");
                }
            }

            postParam.setContent(bodyHtml);
        }

        postParam.setOriginalContent(dto.getBody());
        if (StringUtils.isNotBlank(dto.getBody())) {
            postParam.setSummary(dto.getBody()
                .substring(0, Math.min(dto.getBody()
                    .length(), 150)));
        }
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
