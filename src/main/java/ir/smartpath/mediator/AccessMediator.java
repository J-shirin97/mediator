package ir.smartpath.mediator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class AccessMediator extends AbstractMediator {
    private static final Log log = LogFactory.getLog(AccessMediator.class);











    @Override
    public boolean mediate(MessageContext synCtx) {
        return false;
    }
}
