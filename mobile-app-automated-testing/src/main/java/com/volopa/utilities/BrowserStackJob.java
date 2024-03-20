package com.volopa.utilities;


import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


public class BrowserStackJob {
	 private String name;
	    private Object duration;
	    private String os;
	    private String osVersion;
	    private String browserVersion;
	    private Object browser;
	    private String device;
	    private String status;
	    private String hashedID;
	    private Object reason;
	    private String buildName;
	    private String projectName;
	    private String buildHashedID;
	    private String logs;
	    private String browserstackStatus;
	    private OffsetDateTime createdAt;
	    private String browserURL;
	    private String publicURL;
	    private String videoURL;
	    private String appiumLogsURL;
	    private String deviceLogsURL;
	    private AppDetails appDetails;
	    private Object terminalLogsURL;

	    @JsonProperty("name")
	    public String getName() { return name; }
	    @JsonProperty("name")
	    public void setName(String value) { this.name = value; }

	    @JsonProperty("duration")
	    public Object getDuration() { return duration; }
	    @JsonProperty("duration")
	    public void setDuration(Object value) { this.duration = value; }

	    @JsonProperty("os")
	    public String getOS() { return os; }
	    @JsonProperty("os")
	    public void setOS(String value) { this.os = value; }

	    @JsonProperty("os_version")
	    public String getOSVersion() { return osVersion; }
	    @JsonProperty("os_version")
	    public void setOSVersion(String value) { this.osVersion = value; }

	    @JsonProperty("browser_version")
	    public String getBrowserVersion() { return browserVersion; }
	    @JsonProperty("browser_version")
	    public void setBrowserVersion(String value) { this.browserVersion = value; }

	    @JsonProperty("browser")
	    public Object getBrowser() { return browser; }
	    @JsonProperty("browser")
	    public void setBrowser(Object value) { this.browser = value; }

	    @JsonProperty("device")
	    public String getDevice() { return device; }
	    @JsonProperty("device")
	    public void setDevice(String value) { this.device = value; }

	    @JsonProperty("status")
	    public String getStatus() { return status; }
	    @JsonProperty("status")
	    public void setStatus(String value) { this.status = value; }

	    @JsonProperty("hashed_id")
	    public String getHashedID() { return hashedID; }
	    @JsonProperty("hashed_id")
	    public void setHashedID(String value) { this.hashedID = value; }

	    @JsonProperty("reason")
	    public Object getReason() { return reason; }
	    @JsonProperty("reason")
	    public void setReason(Object value) { this.reason = value; }

	    @JsonProperty("build_name")
	    public String getBuildName() { return buildName; }
	    @JsonProperty("build_name")
	    public void setBuildName(String value) { this.buildName = value; }

	    @JsonProperty("project_name")
	    public String getProjectName() { return projectName; }
	    @JsonProperty("project_name")
	    public void setProjectName(String value) { this.projectName = value; }

	    @JsonProperty("build_hashed_id")
	    public String getBuildHashedID() { return buildHashedID; }
	    @JsonProperty("build_hashed_id")
	    public void setBuildHashedID(String value) { this.buildHashedID = value; }

	    @JsonProperty("logs")
	    public String getLogs() { return logs; }
	    @JsonProperty("logs")
	    public void setLogs(String value) { this.logs = value; }

	    @JsonProperty("browserstack_status")
	    public String getBrowserstackStatus() { return browserstackStatus; }
	    @JsonProperty("browserstack_status")
	    public void setBrowserstackStatus(String value) { this.browserstackStatus = value; }

	    @JsonProperty("created_at")
	    public OffsetDateTime getCreatedAt() { return createdAt; }
	    @JsonProperty("created_at")
	    public void setCreatedAt(OffsetDateTime value) { this.createdAt = value; }

	    @JsonProperty("browser_url")
	    public String getBrowserURL() { return browserURL; }
	    @JsonProperty("browser_url")
	    public void setBrowserURL(String value) { this.browserURL = value; }

	    @JsonProperty("public_url")
	    public String getPublicURL() { return publicURL; }
	    @JsonProperty("public_url")
	    public void setPublicURL(String value) { this.publicURL = value; }

	    @JsonProperty("video_url")
	    public String getVideoURL() { return videoURL; }
	    @JsonProperty("video_url")
	    public void setVideoURL(String value) { this.videoURL = value; }

	    @JsonProperty("appium_logs_url")
	    public String getAppiumLogsURL() { return appiumLogsURL; }
	    @JsonProperty("appium_logs_url")
	    public void setAppiumLogsURL(String value) { this.appiumLogsURL = value; }

	    @JsonProperty("device_logs_url")
	    public String getDeviceLogsURL() { return deviceLogsURL; }
	    @JsonProperty("device_logs_url")
	    public void setDeviceLogsURL(String value) { this.deviceLogsURL = value; }

	    @JsonProperty("app_details")
	    public AppDetails getAppDetails() { return appDetails; }
	    @JsonProperty("app_details")
	    public void setAppDetails(AppDetails value) { this.appDetails = value; }

	    @JsonProperty("terminal_logs_url")
	    public Object getTerminalLogsURL() { return terminalLogsURL; }
	    @JsonProperty("terminal_logs_url")
	    public void setTerminalLogsURL(Object value) { this.terminalLogsURL = value; }
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(BrowserStackJob.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(BrowserStackJob.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static BrowserStackJob fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(BrowserStackJob obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return BrowserStackJob.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(BrowserStackJob.class);
        writer = mapper.writerFor(BrowserStackJob.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

class AppDetails {
	private String appURL;
    private String appName;
    private String appVersion;
    private Object appCustomID;
    private OffsetDateTime uploadedAt;

    @JsonProperty("app_url")
    public String getAppURL() { return appURL; }
    @JsonProperty("app_url")
    public void setAppURL(String value) { this.appURL = value; }

    @JsonProperty("app_name")
    public String getAppName() { return appName; }
    @JsonProperty("app_name")
    public void setAppName(String value) { this.appName = value; }

    @JsonProperty("app_version")
    public String getAppVersion() { return appVersion; }
    @JsonProperty("app_version")
    public void setAppVersion(String value) { this.appVersion = value; }

    @JsonProperty("app_custom_id")
    public Object getAppCustomID() { return appCustomID; }
    @JsonProperty("app_custom_id")
    public void setAppCustomID(Object value) { this.appCustomID = value; }

    @JsonProperty("uploaded_at")
    public OffsetDateTime getUploadedAt() { return uploadedAt; }
    @JsonProperty("uploaded_at")
    public void setUploadedAt(OffsetDateTime value) { this.uploadedAt = value; }

}
