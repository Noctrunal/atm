package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "exit_en");

    public ExitCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equals(res.getString("yes")))
        {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
