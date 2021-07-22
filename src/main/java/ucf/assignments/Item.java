package ucf.assignments;

public class Item {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String serialNumber;
    private String name;
    public Item(int value, String serialNumber, String name){
        this.value = value;
        this.serialNumber = serialNumber;
        this.name = name;
    }
}
