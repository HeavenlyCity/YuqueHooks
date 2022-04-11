package cn.ridup.tool.yuquehooks.config.properties;

import java.io.File;
import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import lombok.Data;

/**
 * yuque configuration properties.
 *
 * @author ridup
 * @version 0.1.0
 * @since 2022/3/29 20:41
 */
@Data
@ConfigurationProperties("yuque")
public class YuqueProperties {
    /**
     * User home directory.
     */
    public static final String USER_HOME = System.getProperty("user.home");
    /**
     * Authentication enabled.
     */
    private boolean authEnabled = true;

    /**
     * cache store impl
     * memory
     * level
     */
    private String cache = "memory";

    /**
     * Download Timeout.
     */
    private Duration downloadTimeout = Duration.ofSeconds(30);

    /**
     * Work directory.
     */
    private String workDir = ensureSuffix(USER_HOME, File.separator) + ".yuque" + File.separator;

    /**
     * Ensures the string contain suffix.
     *
     * @param string string must not be blank
     * @param suffix suffix must not be blank
     * @return string contain suffix specified
     */
    @NonNull
    public static String ensureSuffix(@NonNull String string, @NonNull String suffix) {
        Assert.hasText(string, "String must not be blank");
        Assert.hasText(suffix, "Suffix must not be blank");

        return StringUtils.removeEnd(string, suffix) + suffix;
    }

    private HaloProperties halo;


}
