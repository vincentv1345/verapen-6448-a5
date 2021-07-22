package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty name;
    private SimpleStringProperty value;
    public SimpleStringProperty getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value.setValue(value);
    }

    public SimpleStringProperty getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Item(String value, String serialNumber, String name){
        this.value = new SimpleStringProperty(value);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.name = new SimpleStringProperty(name);
    }
}
