package com.rates.spring.rest.exceptions;

public class ExceptionTemplateBuilder {

    private final ExceptionTemplateImpl template = new ExceptionTemplateImpl();

    public ExceptionTemplateBuilder() {
    }

    public ExceptionTemplateBuilder responseStatus(int status) {
        template.setResponseStatus(status);
        return this;
    }

    public ExceptionTemplateBuilder errorMessageTemplate(String messageTemplate) {
        template.setErrorMessageTemplate(messageTemplate);
        return this;
    }

    public ExceptionTemplateBuilder errorCode(int errorCode) {
        template.setErrorCode(errorCode);
        return this;
    }

    public ExceptionTemplateBuilder logException(boolean flag) {
        template.setLogException(flag);
        return this;
    }

    public ExceptionTemplate build() {
        return template;
    }
}
