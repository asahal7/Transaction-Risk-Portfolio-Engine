package model;

public class Transaction {
    public enum Type { BUY, SELL }

    private Type type;
    private Asset asset;
    private double amount; // value of transaction

    public Transaction(Type type, Asset asset, double amount) {
        this.type = type;
        this.asset = asset;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public Asset getAsset() {
        return asset;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + " " + asset.getName() + " | Amount: " + amount;
    }
}
