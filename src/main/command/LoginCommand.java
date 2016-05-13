package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command
{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCES_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while(true) {
            String cardNumber = ConsoleHelper.readString().trim();
            String cardPin = ConsoleHelper.readString().trim();
            String numberRegex = "^\\d{12}$";
            String pinRegex = "^\\d{4}$";
            if(cardNumber.matches(numberRegex) && cardPin.matches(pinRegex)) {

                if (validCreditCards.keySet().contains(cardNumber) && validCreditCards.getString(cardNumber).equals(cardPin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),cardNumber));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cardNumber));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}