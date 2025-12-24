package model;

public class Asset {
    private String name;
    private double value;
    private double risk; // risk factor between 0 and 1

    public Asset(String name, double value, double risk) {
        this.name = name;
        this.value = value;
        this.risk = risk;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public double getRisk() {
        return risk;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }

    @Override
    public String toString() {
        return name + " | Value: " + value + " | Risk: " + risk;
    }
}
