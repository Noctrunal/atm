package main;

import main.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{

    private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException
    {
        String line = null;
        try {
            line = r.readLine();
            if("EXIT".equalsIgnoreCase(line)) {
                throw new InterruptOperationException();
            }
        }
        catch (IOException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        while(true) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            String currencyCode = readString();
            if(currencyCode.length() == 3) {
                return currencyCode.toUpperCase();
            } else {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        while(true) {
            try
            {
                ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
                String data = readString();
                String regex = "^[1-9]\\d*\\s[1-9]\\d*$";
                if (data.matches(regex))
                {
                    String[] digits = data.split("\\s");
                    return digits;
                } else
                {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        while(true) {
            try {
                ConsoleHelper.writeMessage(res.getString("choose.operation"));
                ConsoleHelper.writeMessage(res.getString("operation.INFO"));
                ConsoleHelper.writeMessage(res.getString("operation.DEPOSIT"));
                ConsoleHelper.writeMessage(res.getString("operation.WITHDRAW"));
                ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
                String askOperation = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(askOperation));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
