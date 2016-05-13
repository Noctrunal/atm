package main.command;

import main.Operation;
import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor
{
    private CommandExecutor() {}

    private static Map<Operation, Command> operationCommandMap = new HashMap<>();

    static {
        operationCommandMap.put(Operation.LOGIN, new LoginCommand());
        operationCommandMap.put(Operation.INFO, new InfoCommand());
        operationCommandMap.put(Operation.DEPOSIT, new DepositCommand());
        operationCommandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        operationCommandMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        operationCommandMap.get(operation).execute();
    }
}
