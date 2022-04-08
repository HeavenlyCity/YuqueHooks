package cn.ridup.tool.yuquehooks.integration.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Halo Login Response Dto
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/4/7 15:40
 */
@Data
public class HaloLoginResponseDto implements java.io.Serializable {
    private static final long serialVersionUID = 4125748380987222971L;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
