package com.study.ch09.chain;

public interface Handler {

    void setNext(Handler next);

    RequestMessage handle(RequestMessage requestMessage);
}
