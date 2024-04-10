package com.j1gsaw.deadbyminecraft.Survivor.Exception;

public class SurvivorOperationFailedException extends Exception{
    private final String operation;
    private final String reason;
    public SurvivorOperationFailedException(String operation, String reason) {
        this.operation = operation;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "SurvivorClass中，" + operation + "操作异常。原因为：" + reason;
    }
}
