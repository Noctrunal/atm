package main;

import main.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, count);
    }

    public int getTotalAmount() {

        Integer result = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            result += entry.getKey() * entry.getValue();
        }

        return result;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        if(hasMoney() && getTotalAmount() >= expectedAmount) {
            return true;
        } else {
            return false;
        }
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
       TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.putAll(denominations);
        NavigableMap<Integer, Integer> destOrder = tm.descendingMap();

        Map<Integer, Integer> map = new HashMap<>();
        int req = expectedAmount;
        for(int start = 0; start < destOrder.size(); start++) {
            for(int j = start; j < destOrder.size(); j++) {

                Iterator<Map.Entry<Integer, Integer>> it = destOrder.entrySet().iterator();

                for(int k = 0; k < j; k++) {
                    it.next();
                }

                Map.Entry<Integer, Integer> pair = it.next();
                Integer nominal = pair.getKey();
                Integer count = pair.getValue();
                for(int k = 0; k < count; k++) {
                    if(req >= nominal) {
                        map.put(nominal, k+1);
                        req -= nominal;
                    } else break;
                }
            }
            if(req == 0) {
                break;
            } else {
                req = expectedAmount;
                map = new HashMap<>();
            }
        }

        if(map.size() == 0) {
            throw new NotEnoughMoneyException();
        } else {
            for (Map.Entry<Integer, Integer> entry : map.entrySet())
            {
                Integer key = entry.getKey();
                Integer count = entry.getValue();

                Integer curCount = denominations.get(key);
                int balance = curCount - count;
                if(balance == 0) {
                    denominations.remove(key);
                } else {
                    denominations.put(key, balance);
                }
            }
        }
        return map;
    }
}
