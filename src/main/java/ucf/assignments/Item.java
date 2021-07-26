package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class Item extends TextField {
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty name;
    private SimpleStringProperty value;
    public String getValue() {
        return value.get();
    }
    public SimpleStringProperty valueProperty(){
        return value;
    }
    public void setValue(String value) {
        this.value.setValue(value);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }
    public SimpleStringProperty serialNumberProperty(){
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }
    public String getName() {
        return name.get();
    }
    public SimpleStringProperty nameProperty(){
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
    public Item(){
    }
    @Override
    public String toString(){
        return String.format("%-6s %-6s " + "\t \t|" + "%-6s " + "\t \t|"+"%-6s ", value, serialNumber, name);
    }
}
