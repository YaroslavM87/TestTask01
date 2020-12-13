package com.yaroslavm87.testtask01.ModelCommands;

public abstract class ModelCommand {

    // TODO: check for repetitions of code among commands

    private boolean executed;

    public ModelCommand() {
        executed = false;
    }

    public abstract void execute();

    public void markAsExecuted() {
        this.executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}