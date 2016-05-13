package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;

import java.util.ResourceBundle;


class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "info_en");

    public InfoCommand()
    {
    }

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean flag = false;
        for(CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if(manipulator.hasMoney()) {
                ConsoleHelper.writeMessage(String.format("%s - %s", manipulator.getCurrencyCode(), manipulator.getTotalAmount()));
                flag = true;
            }
        }
        if(!flag) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
