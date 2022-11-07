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

    public boolean mediate(MessageContext mc) {

        MediatorInputs mediatorInputs = new MediatorInputs();


        logger.debug("getting userName");
        String userName = (String) mc.getProperty("api.ut.userId");


        Registry registry = mc.getConfiguration().getRegistry();
        try {
            logger.debug("registry config");
            Properties resourceProperties = registry
                    .getResourceProperties("conf:/properties/" + userName + "/" + mediatorInputs.address);
            resourceProperties.getProperty("");


            logger.debug("dynamic replacing in header & body");
            String[] fieldSplit = mediatorInputs.field.split(",");
            for (String fieldKey : fieldSplit) {
                String fieldProperty = resourceProperties.getProperty(fieldKey);
                MediatorInputs.header = MediatorInputs.header.replaceAll("\\{\\{" + fieldKey + "}}", fieldProperty);
                mediatorInputs.body = mediatorInputs.body.replaceAll("\\{\\{" + fieldKey + "}}", fieldProperty);
            }


            String[] headerSplit = MediatorInputs.header.split(":");
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put(headerSplit[0], headerSplit[1]);

            log.debug("inputs");
            HttpConnection.urlConnection(headerMap,
                    mediatorInputs.requestMethod,
                    mediatorInputs.url,
                    mediatorInputs.body,
                    mediatorInputs.contentType,
                    mediatorInputs.path,
                    mediatorInputs.expirePath,
                    mediatorInputs.flag,
                    userName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
