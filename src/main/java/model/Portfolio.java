package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class Portfolio {

    private Map<String, Asset> assets;
    private double totalValue;
    private final Set<Integer> appliedTransactionIds;


    public Portfolio(Map<String, Asset> assets) {
        this.assets = assets;
        this.appliedTransactionIds = new HashSet<>();
        this.totalValue = 0;

        for (Asset asset : assets.values()) {
            this.totalValue += asset.getValue();
        }
    }

    public boolean hasAsset(String name){
        return assets.containsKey(name);
    }
    public double getAssetValue(String name){
        Asset asset = assets.get(name);
        if (asset != null){
            return asset.getValue();
        }
        return 0;
    }
    public void addAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset can not be null. Please enter a valid asset object");
        }
        if (assets.containsKey(asset.getName())){
            throw new IllegalArgumentException("Asset already exists within the Portfolio. Unable to add duplicate.");
        }
        assets.put(asset.getName(), asset);
        totalValue += asset.getValue();
    }

    public void removeAsset(String assetName) {
        totalValue -= assets.get(assetName).getValue();
        assets.remove(assetName);
    }


    public double getTotalValue() {
        return totalValue;
    }

   public void applyTransaction(Transaction tx) {
        Objects.requireNonNull(tx, "Transaction cannot be null");

        if (appliedTransactionIds.contains(tx.getId())) {
            throw new IllegalStateException(
                "Transaction already applied: " + tx.getId()
            );
        }

        Asset asset = assets.get(tx.getAssetName());
        if (asset == null) {
            throw new IllegalArgumentException("Asset does not exist");
        }

        if (tx.getType() == Transaction.Type.BUY) {
            asset.setValue(asset.getValue() + tx.getAmount());
            totalValue += tx.getAmount();

        } else if (tx.getType() == Transaction.Type.SELL) {
            double oldValue = asset.getValue();

            double newValue = Math.max(oldValue - tx.getAmount(), 0);
            asset.setValue(newValue);

            double removed = oldValue - newValue;
            totalValue -= removed;
        }

        appliedTransactionIds.add(tx.getId());
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Portfolio:\n");
        for (Asset a : assets.values()) {
            sb.append(a.toString()).append("\n");
        }
        sb.append("Total Value: ").append(getTotalValue());
        return sb.toString();
    }
}
