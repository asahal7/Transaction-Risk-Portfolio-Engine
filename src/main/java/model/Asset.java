package model;

public class Asset {
    private String name;
    private double value;
    private double risk; // risk factor between 0 and 1

    public Asset(String name, double value, double risk) {
        if (value < 0){
            throw new IllegalArgumentException("Asset value cannot be negative.");
        }
        if (risk < 0 || risk > 1) {
            throw new IllegalArgumentException("Risk must be between 0 and 1.");
        }
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
    @Override // overrides natual equal method so in the contains method which we use in portfolio it checks by name
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return name.equals(asset.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    public void setValue(double value) {
        if (value < 0){
            throw new IllegalArgumentException("Asset value cannot be negative.");
        }
        this.value = value;
    }

    public void setRisk(double risk) {
        if (risk < 0 || risk > 1) {
            throw new IllegalArgumentException("Risk must be between 0 and 1.");
        }
        this.risk = risk;
    }

    @Override
    public String toString() {
        return name + " | Value: " + value + " | Risk: " + risk;
    }
}
