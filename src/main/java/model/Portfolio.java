package model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Asset> assets;
    double totalValue;

    public Portfolio() {
        assets = new HashMap<String, Asset>();
        totalValue = 0;
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
        assets.put(asset.getName(), asset);
    }

    public void removeAsset(String assetName) {
        assets.remove(assetName);
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void applyTransaction(Transaction tx) {
        Asset asset = tx.getAsset();

        if (tx.getType() == Transaction.Type.BUY) {
            asset.setValue(asset.getValue() + tx.getAmount());
            totalValue += tx.getAmount();

            if (!assets.containsKey(asset.getName())) {
                assets.put(asset.getName(), asset);
            }

        } else if (tx.getType() == Transaction.Type.SELL) {
            double oldValue = asset.getValue();

            double newValue = Math.max(oldValue - tx.getAmount(), 0);
            asset.setValue(newValue);

            double removed = oldValue - newValue;
            totalValue -= removed;

            if (asset.getValue() == 0) {
                assets.remove(asset.getName());
            }
        }
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
