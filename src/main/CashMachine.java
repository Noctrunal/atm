package main;

import main.command.CommandExecutor;
import main.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine
{
    public static final String RESOURCES_PATH = "main.resources.";
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        try
        {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do
            {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        }
        catch (InterruptOperationException e)
        {
            ConsoleHelper.printExitMessage();
        }
    }
}
