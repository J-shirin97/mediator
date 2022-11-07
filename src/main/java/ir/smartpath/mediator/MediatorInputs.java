package ir.smartpath.mediator;

import org.apache.log4j.Logger;

public class MediatorInputs {
    Logger logger = Logger.getLogger(String.valueOf(MediatorInputs.class));
    public boolean flag;
    public static String header;
    public String requestMethod;
    public String url;
    public String body;
    public String contentType;
    public String path;
    public String expirePath;
    public String field;
    public String address;



    public MediatorInputs(boolean flag, String requestMethod, String url, String body, String contentType, String path, String expirePath, String field, String address , String header) {
        this.flag = flag;
        this.requestMethod = requestMethod;
        this.url = url;
        this.body = body;
        this.contentType = contentType;
        this.path = path;
        this.expirePath = expirePath;
        this.field = field;
        this.address = address;


    }


    public MediatorInputs() {

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static String getHeader() {
        return header;
    }

    public static void setHeader(String header) {
        MediatorInputs.header = header;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExpirePath() {
        return expirePath;
    }

    public void setExpirePath(String expirePath) {
        this.expirePath = expirePath;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
