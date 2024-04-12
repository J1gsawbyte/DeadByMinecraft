package com.j1gsaw.deadbyminecraft.Killer.Exception;

public class KillerOperationFailedException extends Exception {
    private final String operation;
    private final String reason;
    public KillerOperationFailedException(String operation, String reason) {
        this.operation = operation;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "KillerClass中，" + operation + "操作异常。原因为：" + reason;
    }
}
