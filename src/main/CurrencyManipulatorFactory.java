package main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> manipulatorMap = new HashMap<>();

    private CurrencyManipulatorFactory() {}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if(!manipulatorMap.containsKey(currencyCode)) {
            manipulatorMap.put(currencyCode, new CurrencyManipulator(currencyCode));
        }
        return manipulatorMap.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulatorMap.values();
    }
}
