package com.study.ch09.chain;

public class EnglishHandler extends AbstractHandler {
    @Override
    protected boolean canHandle(RequestMessage requestMessage) {
        return requestMessage.getMessage().matches("[a-zA-Z]+");
    }

    @Override
    protected RequestMessage process(RequestMessage requestMessage) {
        return new RequestMessage("영어 처리: " + requestMessage.getMessage());
    }
}
