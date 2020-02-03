package com.axa.StringUtil;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Optional;

public class StringUtil {
    private static String INVALID = "#VALUE!";
    private static String NA = "NA";


    public static Optional<Double> parseOptionalDollarValue(String source) {
        if (source.trim().equalsIgnoreCase(INVALID) || source.trim().equalsIgnoreCase(NA)) return Optional.empty();
        source = source.replaceAll("\"", "");

        try {
            Double v = NumberFormat.getCurrencyInstance().parse(source).doubleValue();
            return Optional.of(v);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String getDollarString(Optional<Double> source) {
        String dolloarString = source.map(aDouble -> NumberFormat.getCurrencyInstance().format(aDouble)).orElse(NA);
        return "\"" + dolloarString + "\"";
    }

    public static Optional<Double> parseOptionalPercentageValue(String source) {
        if (source.equalsIgnoreCase(INVALID) || source.equalsIgnoreCase(NA)) return Optional.empty();
        source = source.replaceAll("\"", "");

        try {
            Double v = NumberFormat.getPercentInstance().parse(source).doubleValue();
            return Optional.of(v);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String getPercentageString(Optional<Double> source) {
        NumberFormat instance = NumberFormat.getPercentInstance();
        instance.setMinimumFractionDigits(2);
        String percentageString = source.map(instance::format).orElse(NA);
        return "\"" + percentageString + "\"";
    }


    public static Optional<Integer> parseOptionalIntValue(String source) {
        if (source.equalsIgnoreCase(INVALID) || source.equalsIgnoreCase(NA)) return Optional.empty();
        source = source.replaceAll("\"", "");

        try {
            Integer v = NumberFormat.getNumberInstance().parse(source).intValue();
            return Optional.of(v);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String getIntString(Optional<Integer> source) {
        String intString = source.map(aInt -> NumberFormat.getNumberInstance().format(aInt)).orElse(NA);
        return "\"" + intString + "\"";
    }


    public static Optional<Double> parseOptionalDoubleValue(String source) {
        if (source.equalsIgnoreCase(INVALID) || source.equalsIgnoreCase(NA)) return Optional.empty();
        source = source.replaceAll("\"", "");

        try {
            Double v = NumberFormat.getNumberInstance().parse(source).doubleValue();
            return Optional.of(v);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String getDoubleString(Optional<Double> source) {
        String percentageString = source.map(aDouble -> NumberFormat.getNumberInstance().format(aDouble)).orElse(NA);
        return "\"" + percentageString + "\"";
    }



}
