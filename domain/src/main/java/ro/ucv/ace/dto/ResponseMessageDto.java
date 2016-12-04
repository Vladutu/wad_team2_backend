package ro.ucv.ace.dto;

/**
 * Created by tzapt on 12/4/2016.
 */
public class ResponseMessageDto {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessageDto(String message) {
        this.message = message;
    }
}
