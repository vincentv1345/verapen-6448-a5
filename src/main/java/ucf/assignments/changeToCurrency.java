package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Vincent Verapen
 */
import javafx.scene.control.Alert;

import java.text.NumberFormat;
import java.util.Locale;

public class changeToCurrency{
    public String changeToCurrency(String x){
        try {
            double value = Double.parseDouble(x);
            double value2 = Math.round(value * 100.0)/100.0;
            NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
            return n.format(value2);
        }
        catch (NumberFormatException e){
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert currencyNotParsable = new Alert(alertType, "The following input is not parsable");
            e.printStackTrace();
        }
        return null;
    }
}
