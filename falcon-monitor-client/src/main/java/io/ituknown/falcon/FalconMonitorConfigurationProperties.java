package io.ituknown.falcon;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Validated
@ConfigurationProperties(prefix = "falcon.monitor")
public class FalconMonitorConfigurationProperties {

    /**
     * 应用名称
     */
    @NotBlank
    private String appId;

    /**
     * 服务地址
     */
    @NotBlank
    private String reportServerUrl = "localhost:8080";

    /**
     * 切点表达式
     */
    @NotEmpty
    private List<String> pointcutExpression = new ArrayList<>();

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getReportServerUrl() {
        return reportServerUrl;
    }

    public void setReportServerUrl(String reportServerUrl) {
        this.reportServerUrl = reportServerUrl;
    }

    public List<String> getPointcutExpression() {
        return pointcutExpression;
    }

    public void setPointcutExpression(List<String> pointcutExpression) {
        this.pointcutExpression = pointcutExpression;
    }
}
