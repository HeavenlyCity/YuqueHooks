package cn.ridup.tool.yuquehooks.service.convertor;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            Document document = Jsoup.parse(bodyHtml);
            Elements neQuotes = document.getElementsByClass("ne-quote");
            neQuotes.forEach(element -> {
                element.tagName("blockquote");
            });

            Elements pres = document.getElementsByTag("pre");
            pres.forEach(element -> {
                element.removeClass("ne-codeblock");
                Element code = new Element("code");
                element.classNames()
                    .forEach(code::addClass);
                code.insertChildren(0, element.childNodes());
                element.empty();
                element.appendChild(code);
            });

            document.getElementsByClass("ne-thirdparty")
                .forEach(element -> {
                    if (element.childNodes()
                        .size() > 0) {
                        String musicUrl = element.childNodes()
                            .get(0)
                            .attr("href");
                        Element musicIframe = new Element("iframe");
                        musicIframe.attr("frameborder", "no");
                        musicIframe.attr("border", "0");
                        musicIframe.attr("marginwidth", "0");
                        musicIframe.attr("marginheight", "0");
                        musicIframe.attr("style", "width: -webkit-fill-available;");
                        musicIframe.attr("height", "86");
                        musicIframe.attr("src", musicUrl);
                        element.after(musicIframe);
                        element.remove();
                    }
                });

            document.getElementsByClass("ne-hr")
                .forEach(element -> {
                    element.attr("style", "width: 100%;");
                });

            document.getElementsByClass("ne-tli-symbol")
                .forEach(element -> {
                    Element input = new Element("input");
                    input.attr("class", "task-list-item-checkbox");
                    input.attr("disabled", "");
                    input.attr("type", "checkbox");
                    element.empty();
                    element.insertChildren(0, input);
                });

            // for the image add a gallery
            document.getElementsByClass("ne-p")
                .forEach(element -> {
                    if (element.childNode(0)
                        .nodeName()
                        .equals("img")) {
                        String src = element.childNode(0)
                            .attr("src");
                        Element gallery = new Element("span");
                        gallery.attr("data-fancybox", "gallery");
                        gallery.attr("href", src);
                        gallery.attr("style", "display: block;");
                        element.childNode(0)
                            .attr("class", "lazyload");
                        element.childNode(0)
                            .attr("data-src", src);
                        element.childNode(0)
                            .attr("data-loaded", "true");
                        gallery.insertChildren(0, element.childNode(0).clone());
                        element.childNode(0)
                            .remove();
                        element.insertChildren(0, gallery);
                    }

                });

            //  support the svg img
            document.getElementsByClass("ne-image")
                .forEach(element -> {
                    if (element.nodeName()
                        .equals("img")) {
                        String src = element.childNode(0)
                            .attr("src");
                        Element gallery = new Element("span");
                        gallery.attr("data-fancybox", "gallery");
                        gallery.attr("href", src);
                        gallery.attr("style", "display: block;");
                        element.childNode(0)
                            .attr("class", "lazyload");
                        element.childNode(0)
                            .attr("data-src", src);
                        element.childNode(0)
                            .attr("data-loaded", "true");
                        gallery.insertChildren(0, element.childNode(0).clone());
                        element.childNode(0)
                            .remove();
                        element.insertChildren(0, gallery);
                    }
                });

            postParam.setContent(document.body()
                .html());

            if (StringUtils.isNotBlank(document.body()
                .html())) {
                postParam.setSummary(document.body()
                    .html()
                    .substring(0, Math.min(dto.getBody()
                        .length(), 200)));
            }

            Elements imgList = document.getElementsByTag("img");
            if (!imgList.isEmpty()) {
                // thumbnail
                postParam.setThumbnail(imgList.get(0)
                    .attr("src"));
            }

        }

        postParam.setOriginalContent(dto.getBody());

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
