package com.study.ch09.chain;

public abstract class AbstractHandler implements Handler {

    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public RequestMessage handle(RequestMessage requestMessage) {
        if (canHandle(requestMessage)) {
            return process(requestMessage);
        } else if (next != null) {
            return next.handle(requestMessage); // 위임
        }
        return new RequestMessage("처리 불가: " + requestMessage.getMessage());
    }

    protected abstract boolean canHandle(RequestMessage requestMessage);
    protected abstract RequestMessage process(RequestMessage requestMessage);
}
