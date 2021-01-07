package travelling.api.app.common.constant;

import java.util.Arrays;

public enum MessageConstant {
    SUCCESS("SUCCESS"),
    GENERAL_ERROR("GENERAL_ERROR"),
    EXPIRED_TOKEN("EXPIRED_TOKEN"),
    INVALID_TOKEN("INVALID_TOKEN"),
    LOGIN_FALSE("LOGIN_FALSE"),
    ACCESS_DENIED("ACCESS_DENIED"),
    USER_NOT_FOUND("USER_NOT_FOUND"),
    CAMPAIGN_NOT_FOUND("CAMPAIGN_NOT_FOUND"),
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND"),
    TIME_ERROR("TIME_ERROR"),
    USER_NAME_BLANK("USER_01"),
    USER_FOUND("USER_FOUND"),
    USER_NOT_ACTIVE("USER_NOT_ACTIVE"),
    PASSWORD_BLANK("USER_02"),
    PASSWORD_FORMAT("USER_03"),
    UNIQUE_ELEMENT("HIBERNATE_01"),
    DATA_INVALID("DATA_INVALID"),
    INTERNAL_SERVER("INTERNAL_SERVER"),
    TOUR_NOT_FOUND("TOUR_NOT_FOUND"),
    BLOG_NOT_FOUND("BLOG_NOT_FOUND"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND");
    private String value;

    MessageConstant(String value) {
        this.value = value;
    }

    public static String getValue(String code) {
        return Arrays.stream(values()).filter(v -> v.value.equals(code)).findFirst().get().value();
    }

    public String value() {
        return value;
    }

}
