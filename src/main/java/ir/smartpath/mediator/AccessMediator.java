package ir.smartpath.mediator;


import ir.smartpath.connection.HttpConnection;
import org.apache.log4j.Logger;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.registry.Registry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


public class AccessMediator extends AbstractMediator {

    Logger logger = Logger.getLogger(String.valueOf(AccessMediator.class));
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
        AccessMediator.header = header;
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

    public boolean mediate(MessageContext mc) {

        logger.debug("getting userName");
        String userName = (String) mc.getProperty("api.ut.userId");


        Registry registry = mc.getConfiguration().getRegistry();
        try {
            logger.debug("registry config");
            Properties resourceProperties = registry
                    .getResourceProperties("conf:/properties/" + userName + "/" + address);
            resourceProperties.getProperty("");


            logger.debug("dynamic replacing in header & body");
            String[] fieldSplit = field.split(",");
            for (String fieldKey : fieldSplit) {
                String fieldProperty = resourceProperties.getProperty(fieldKey);
                header = header.replaceAll("\\{\\{" + fieldKey + "}}", fieldProperty);
                body = body.replaceAll("\\{\\{" + fieldKey + "}}", fieldProperty);
            }


            String[] headerSplit = header.split(":");
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put(headerSplit[0], headerSplit[1]);

            log.debug("inputs");
            HttpConnection.urlConnection(headerMap,
                    requestMethod,
                    url,
                    body,
                    contentType,
                    path,
                    expirePath,
                    flag,
                    userName);

            mc.setProperty(path, HttpConnection.urlConnection(headerMap, requestMethod, url, body, contentType, path, expirePath, flag, userName));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
