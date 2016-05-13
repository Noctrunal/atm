package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.NavigableMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "withdraw_en");

    public WithdrawCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while(true)
        {
            try
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum < 0)
                {
                    throw new NumberFormatException();
                }

                if(!manipulator.isAmountAvailable(sum)) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }

                Map<Integer, Integer> result = manipulator.withdrawAmount(sum);
                TreeMap<Integer, Integer> treeMap = new TreeMap<>();
                treeMap.putAll(result);
                NavigableMap<Integer, Integer> destOrder = treeMap.descendingMap();
                for (Map.Entry<Integer, Integer> entry : destOrder.entrySet())
                {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),sum,currencyCode));
                break;
            }
            catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            catch (NotEnoughMoneyException ignore) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }
    }
}
