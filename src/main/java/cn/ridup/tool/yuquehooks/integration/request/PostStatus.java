package cn.ridup.tool.yuquehooks.integration.request;

/**
 * Post status.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/9 20:19
 */
public enum PostStatus implements ValueEnum<Integer> {

    /**
     * Published status.
     */
    PUBLISHED(0),

    /**
     * Draft status.
     */
    DRAFT(1),

    /**
     * Recycle status.
     */
    RECYCLE(2),

    /**
     * Intimate status
     */
    INTIMATE(3);

    private final int value;

    PostStatus(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
