package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command
{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "deposit_en");

    public DepositCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);
        manipulator.addAmount(denomination,count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),denomination * count, currencyCode));
    }
}
