package cn.ridup.tool.yuquehooks.service.convertor;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;

import cn.ridup.tool.yuquehooks.service.constant.BookSerializerKey;
import cn.ridup.tool.yuquehooks.service.constant.DocDetailSerializerKey;
import cn.ridup.tool.yuquehooks.service.constant.UserSerializerKey;
import cn.ridup.tool.yuquehooks.service.dto.BookSerializer;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailDto;
import cn.ridup.tool.yuquehooks.service.dto.DocDetailSerializer;
import cn.ridup.tool.yuquehooks.service.dto.UserSerializer;
import cn.ridup.tool.yuquehooks.service.dto.YuqueHooksDto;
import cn.ridup.tool.yuquehooks.utils.DateUtils;
import cn.ridup.tool.yuquehooks.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * DocDetailConvertor
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/6 10:12
 */
@Slf4j
public class DocDetailConvertor {

    public static DocDetailDto convertToDocDetail(YuqueHooksDto dto) {
        if (dto == null || dto.getData() == null) {
            return null;
        }
        Map<String, Object> data = dto.getData();

        DocDetailDto result = new DocDetailDto();
        DocDetailSerializer docDetailSerializer = new DocDetailSerializer();
        docDetailSerializer.setSlug((String) data.get(DocDetailSerializerKey.SLUG));
        docDetailSerializer.setTitle((String) data.get(DocDetailSerializerKey.TITLE));

        docDetailSerializer.setBookId((Integer) data.get(DocDetailSerializerKey.BOOK_ID));
        docDetailSerializer.setBook(convertToBook(data.get(DocDetailSerializerKey.BOOK)));

        docDetailSerializer.setUserId((Integer) data.get(DocDetailSerializerKey.USER_ID));
        docDetailSerializer.setUser(convertToUser(data.get(DocDetailSerializerKey.USER)));

        docDetailSerializer.setFormat((String) data.get(DocDetailSerializerKey.FORMAT));
        docDetailSerializer.setBody((String) data.get(DocDetailSerializerKey.BODY));
        docDetailSerializer.setBodyDraft((String) data.get(DocDetailSerializerKey.BODY_DRAFT));
        docDetailSerializer.setBodyHtml((String) data.get(DocDetailSerializerKey.BODY_HTML));
        docDetailSerializer.setBodyLake((String) data.get(DocDetailSerializerKey.BODY_LAKE));
        docDetailSerializer.setCreatorId((Integer) data.get(DocDetailSerializerKey.CREATOR_ID));
        docDetailSerializer.setPublicFlag((Integer) data.get(DocDetailSerializerKey.PUBLIC));
        docDetailSerializer.setStatus((Integer) data.get(DocDetailSerializerKey.STATUS));
        docDetailSerializer.setLikesCount((Integer) data.get(DocDetailSerializerKey.LIKES_COUNT));
        docDetailSerializer.setCommentsCount((Integer) data.get(DocDetailSerializerKey.COMMENTS_COUNT));
        if (StringUtils.isNotBlank((String) data.get(DocDetailSerializerKey.CONTENT_UPDATED_AT))) {
            docDetailSerializer.setContentUpdatedAt(
                DateUtils.parseDate((String) data.get(DocDetailSerializerKey.CONTENT_UPDATED_AT)));
        }
        if (StringUtils.isNotBlank((String) data.get(DocDetailSerializerKey.DELETED_AT))) {
            docDetailSerializer.setDeletedAt(DateUtils.parseDate((String) data.get(DocDetailSerializerKey.DELETED_AT)));
        }
        if (StringUtils.isNotBlank((String) data.get(DocDetailSerializerKey.CREATED_AT))) {
            docDetailSerializer.setCreatedAt(DateUtils.parseDate((String) data.get(DocDetailSerializerKey.CREATED_AT)));
        }

        if (StringUtils.isNotBlank((String) data.get(DocDetailSerializerKey.UPDATED_AT))) {
            docDetailSerializer.setUpdatedAt(DateUtils.parseDate((String) data.get(DocDetailSerializerKey.UPDATED_AT)));
        }
        result.setData(docDetailSerializer);
        return result;
    }

    public static BookSerializer convertToBook(Object book) {
        Map<String, Object> bookSerializerMap;
        String bookString = null;
        try {
            TypeReference<Map<String, Object>> type = new TypeReference<>() {
            };
            bookString = JsonUtils.objectToJson(book);
            bookSerializerMap = JsonUtils.jsonToObject(bookString, type);
        } catch (Exception e) {
            log.error("BookSerializer convert error, bookString:{}", bookString);
            return null;
        }

        if (CollectionUtils.isEmpty(bookSerializerMap)) {
            return null;
        }

        BookSerializer result = new BookSerializer();
        result.setId((Integer) bookSerializerMap.get(BookSerializerKey.ID));
        result.setType((String) bookSerializerMap.get(BookSerializerKey.TYPE));
        result.setSlug((String) bookSerializerMap.get(BookSerializerKey.SLUG));
        result.setName((String) bookSerializerMap.get(BookSerializerKey.NAME));
        result.setNamespace((String) bookSerializerMap.get(BookSerializerKey.NAMESPACE));

        result.setUserId((Integer) bookSerializerMap.get(BookSerializerKey.USER_ID));
        result.setUser(convertToUser(bookSerializerMap.get(BookSerializerKey.USER)));

        result.setDescription((String) bookSerializerMap.get(BookSerializerKey.DESCRIPTION));
        result.setCreatorId((Integer) bookSerializerMap.get(BookSerializerKey.CREATOR_ID));
        result.setPublicFlag((Integer) bookSerializerMap.get(BookSerializerKey.PUBLIC));
        result.setLikesCount((Integer) bookSerializerMap.get(BookSerializerKey.LIKES_COUNT));
        result.setWatchesCount((Integer) bookSerializerMap.get(BookSerializerKey.WATCHES_COUNT));
        result.setCreatedAt((String) bookSerializerMap.get(BookSerializerKey.CREATED_AT));
        if (StringUtils.isNotBlank((String) bookSerializerMap.get(BookSerializerKey.UPDATED_AT))) {
            result.setUpdatedAt(DateUtils.parseDate((String) bookSerializerMap.get(BookSerializerKey.UPDATED_AT)));
        }

        return result;
    }

    public static UserSerializer convertToUser(Object user) {

        Map<String, Object> userSerializerMap;
        String userString = null;
        try {
            TypeReference<Map<String, Object>> type = new TypeReference<>() {
            };
            userString = JsonUtils.objectToJson(user);
            userSerializerMap = JsonUtils.jsonToObject(userString, type);
        } catch (Exception e) {
            log.error("BookSerializer convert error, userString:{}", userString);
            return null;
        }

        if (CollectionUtils.isEmpty(userSerializerMap)) {
            return null;
        }

        UserSerializer result = new UserSerializer();
        result.setId((Integer) userSerializerMap.get(UserSerializerKey.ID));
        result.setType((String) userSerializerMap.get(UserSerializerKey.TYPE));
        result.setLogin((String) userSerializerMap.get(UserSerializerKey.LOGIN));
        result.setName((String) userSerializerMap.get(UserSerializerKey.NAME));
        result.setAvatarUrl((String) userSerializerMap.get(UserSerializerKey.AVATAR_URL));
        if (StringUtils.isNotBlank((String) userSerializerMap.get(DocDetailSerializerKey.CREATED_AT))) {
            result.setCreatedAt(DateUtils.parseDate((String) userSerializerMap.get(DocDetailSerializerKey.CREATED_AT)));
        }
        if (StringUtils.isNotBlank((String) userSerializerMap.get(DocDetailSerializerKey.UPDATED_AT))) {
            result.setUpdatedAt(DateUtils.parseDate((String) userSerializerMap.get(DocDetailSerializerKey.UPDATED_AT)));
        }
        return result;
    }
}

