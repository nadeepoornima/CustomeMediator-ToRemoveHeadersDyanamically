package com.wso2.customclassmediators.samples;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.om.xpath.AXIOMXPath;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Map;

public class RemoveDynamicHeaders extends AbstractMediator {
    public static final String XPATH_NOTIFY_EVENT_HEADER = "//HEADER";

    public boolean mediate(MessageContext synCtx) {
        org.apache.axis2.context.MessageContext msgContext = ((Axis2MessageContext) synCtx).getAxis2MessageContext();
        try {
            Map<String, Object> headersMap =
                    (Map) msgContext.getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);// getting all transport header : values
            AXIOMXPath axiomxPath = new AXIOMXPath(XPATH_NOTIFY_EVENT_HEADER);
            List list = null;
            OMElement element = AXIOMUtil.stringToOM(synCtx.getProperty("safHeadersList").toString());
            list = axiomxPath.selectNodes(element);

            for (Object om : list) {
                if (om instanceof OMElement) {
                    OMElement omElement = (OMElement) om;
                    headersMap.remove(omElement.getText());
                }
            }

        } catch (JaxenException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return true;
    }
}
