package cn.ridup.tool.yuquehooks.integration.request;

import cn.ridup.tool.yuquehooks.service.enumeration.ValueEnum;

/**
 * @author ryanwang
 * @date 2020-02-28
 */
public enum PostEditorType implements ValueEnum<Integer> {

    /**
     * Markdown editor.
     */
    MARKDOWN(0),

    /**
     * Rich text editor.
     */
    RICHTEXT(1);

    private final Integer value;

    PostEditorType(Integer value) {
        this.value = value;
    }

    /**
     * Get enum value.
     *
     * @return enum value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
