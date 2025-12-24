package model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Asset> assets;

    public Portfolio() {
        assets = new ArrayList<>();
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public void removeAsset(Asset asset) {
        assets.remove(asset);
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public double getTotalValue() {
        return assets.stream().mapToDouble(Asset::getValue).sum();
    }

    public void applyTransaction(Transaction tx) {
        Asset asset = tx.getAsset();
        if (tx.getType() == Transaction.Type.BUY) {
            asset.setValue(asset.getValue() + tx.getAmount());
            if (!assets.contains(asset)) assets.add(asset);
        } else if (tx.getType() == Transaction.Type.SELL) {
            double newValue = asset.getValue() - tx.getAmount();
            asset.setValue(Math.max(newValue, 0));
            if (asset.getValue() == 0) assets.remove(asset);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Portfolio:\n");
        for (Asset a : assets) {
            sb.append(a.toString()).append("\n");
        }
        sb.append("Total Value: ").append(getTotalValue());
        return sb.toString();
    }
}
