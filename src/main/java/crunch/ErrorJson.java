package crunch;

import java.util.Map;

public class ErrorJson {

    public Integer status;
    public String error;
    public String message;
    public String timeStamp;
    public String trace;

    public ErrorJson(int status) {
        this.status = status;
        
    }

}