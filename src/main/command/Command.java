package main.command;

import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

interface Command
{
    void execute() throws InterruptOperationException;
}
