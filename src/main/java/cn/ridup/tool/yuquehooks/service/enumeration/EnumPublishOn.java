package cn.ridup.tool.yuquehooks.service.enumeration;

/**
 * publish on the type
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/4/16 10:58
 */
public enum EnumPublishOn implements ValueEnum<String>{

    /** 发布 */
    PUBLISH("publish"),
    /** 审批通过，则发布；审批取消，则不发布 */
    REVIEW("review");

    private final String value;

    EnumPublishOn(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
