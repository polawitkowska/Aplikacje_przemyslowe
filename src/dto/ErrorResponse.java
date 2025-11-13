package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    private String message;
    private String timestamp;
    private int status;
    private String path;

    public ErrorResponse(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public ErrorResponse(String message, int status) {
        this(message, status, "");
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
