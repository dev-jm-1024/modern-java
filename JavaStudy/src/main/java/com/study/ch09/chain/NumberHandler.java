package com.study.ch09.chain;

import java.util.Spliterator;

public class NumberHandler extends AbstractHandler {
    @Override
    protected boolean canHandle(RequestMessage requestMessage) {
        return requestMessage.getMessage().matches("\\d+"); // 숫자만 가능
    }

    @Override
    protected RequestMessage process(RequestMessage requestMessage) {
        return new RequestMessage("숫자 처리: " + requestMessage.getMessage());
    }
}