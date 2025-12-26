package model;

public class Transaction {
    public enum Type { BUY, SELL }

    private Type type;
    private String assetname;
    private double amount; // value of transaction

    public Transaction(Type type, String assetname, double amount) {
        this.type = type;
        this.assetname = assetname;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public String getAssetName() {
        return assetname;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + " " + assetname + " | Amount: " + amount;
    }
}
