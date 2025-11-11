package dto;

public class ErrorResponse extends RuntimeException {
    //message, timestamp, status, path.
    private String message;
    private String timestamp;
    private int status;
    private String path;

    public ErrorResponse(String message) {
        super(message);
    }
}
