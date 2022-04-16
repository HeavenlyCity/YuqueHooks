package cn.ridup.tool.yuquehooks.service.enumeration;

/**
 * webhook subject type
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/16 10:58
 */
public enum EnumWebhookSubjectType implements ValueEnum<String> {
    PUBLISH("publish"),
    UPDATE("update"),
    NEW_REVIEW("new_review"),
    COMPLETE_REVIEW("complete_review"),
    CANCEL_REVIEW("cancel_review"),

    ;

    private final String value;

    EnumWebhookSubjectType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
