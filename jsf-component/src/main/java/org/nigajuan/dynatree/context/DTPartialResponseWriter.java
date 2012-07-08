package org.nigajuan.dynatree.context;

import org.nigajuan.dynatree.utils.DTUtils;

import javax.faces.context.PartialResponseWriter;
import javax.faces.event.AbortProcessingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DTPartialResponseWriter extends PartialResponseWriter {

    private PartialResponseWriter wrapped;

    public DTPartialResponseWriter(PartialResponseWriter writer) {
        super(writer);
        wrapped = writer;
    }

    @Override
    public void endDocument() throws IOException {
        DTRequestContext requestContext = DTRequestContext.getCurrentInstance();

        if (requestContext != null && requestContext.getNodes() != null) {
            try {
                encodeCallbackParams(requestContext);
            } catch (Exception exception) {
                throw new AbortProcessingException(exception);
            } finally {
            }
        }
        wrapped.endDocument();
    }

    private void encodeCallbackParams(DTRequestContext requestContext) throws IOException {
        Map<String, String> callbackParamExtension = new HashMap<String, String>();
        callbackParamExtension.put("dynatree", "lazyLoad");
        startExtension(callbackParamExtension);
        write(DTUtils.getObjectWriter().writeValueAsString(requestContext.getNodes()));
        endExtension();
    }

    @Override
    public void delete(String targetId) throws IOException {
        wrapped.delete(targetId);
    }

    @Override
    public void endError() throws IOException {
        wrapped.endError();
    }

    @Override
    public void endEval() throws IOException {
        wrapped.endEval();
    }

    @Override
    public void endExtension() throws IOException {
        wrapped.endExtension();
    }

    @Override
    public void endInsert() throws IOException {
        wrapped.endInsert();
    }

    @Override
    public void endUpdate() throws IOException {
        wrapped.endUpdate();
    }

    @Override
    public void redirect(String url) throws IOException {
        wrapped.redirect(url);
    }

    @Override
    public void startDocument() throws IOException {
        wrapped.startDocument();
    }

    @Override
    public void startError(String errorName) throws IOException {
        wrapped.startError(errorName);
    }

    @Override
    public void startEval() throws IOException {
        wrapped.startEval();
    }

    @Override
    public void startExtension(Map<String, String> attributes) throws IOException {
        wrapped.startExtension(attributes);
    }

    @Override
    public void startInsertAfter(String targetId) throws IOException {
        wrapped.startInsertAfter(targetId);
    }

    @Override
    public void startInsertBefore(String targetId) throws IOException {
        wrapped.startInsertBefore(targetId);
    }

    @Override
    public void startUpdate(String targetId) throws IOException {
        wrapped.startUpdate(targetId);
    }

    @Override
    public void updateAttributes(String targetId, Map<String, String> attributes) throws IOException {
        wrapped.updateAttributes(targetId, attributes);
    }


}
