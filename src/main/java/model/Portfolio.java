package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Portfolio {

    private final Map<String, Asset> assets;
    private double totalValue;
    private final Set<Integer> appliedTransactionIds;
    private final List<AuditEntry> auditLog = new ArrayList<>();

    public Portfolio(Map<String, Asset> assets) {
        this.assets = Objects.requireNonNull(assets, "Assets map cannot be null");
        this.appliedTransactionIds = new HashSet<>();
        this.totalValue = 0;

        for (Asset asset : assets.values()) {
            if (asset != null) {
                this.totalValue += asset.getValue();
            }
        }
    }

    public boolean hasAsset(String name) {
        return assets.containsKey(name);
    }

    public double getAssetValue(String name) {
        Asset asset = assets.get(name);
        return asset != null ? asset.getValue() : 0;
    }

    public void addAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset can not be null. Please enter a valid asset object");
        }
        if (assets.containsKey(asset.getName())) {
            throw new IllegalArgumentException("Asset already exists within the Portfolio. Unable to add duplicate.");
        }
        assets.put(asset.getName(), asset);
        totalValue += asset.getValue();
    }

    public void removeAsset(String assetName) {
        Asset existing = assets.get(assetName);
        if (existing == null) {
            return;
        }
        totalValue -= existing.getValue();
        assets.remove(assetName);
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void applyTransaction(Transaction tx) {
        double before = totalValue;

        ValidationResult validation = validate(tx);
        if (!validation.isOk()) {
            auditLog.add(new AuditEntry(tx == null ? null : tx.getId(), AuditEntry.Status.REJECTED, validation.getReasons(), before, totalValue));
            return;
        }

        applyInternal(tx);
        appliedTransactionIds.add(tx.getId());

        auditLog.add(new AuditEntry(tx.getId(), AuditEntry.Status.APPLIED, Collections.emptyList(), before, totalValue));
    }

    public List<AuditEntry> getAuditLog() {
        return Collections.unmodifiableList(auditLog);
    }

    private ValidationResult validate(Transaction tx) {
        List<RejectionReason> reasons = new ArrayList<>();

        if (tx == null) {
            reasons.add(new RejectionReason(RejectionReason.Code.NULL_TRANSACTION, "Transaction cannot be null."));
            return ValidationResult.rejected(reasons);
        }

        if (appliedTransactionIds.contains(tx.getId())) {
            reasons.add(new RejectionReason(RejectionReason.Code.DUPLICATE_TRANSACTION, "Transaction already applied: " + tx.getId()));
            return ValidationResult.rejected(reasons);
        }

        if (tx.getAmount() <= 0) {
            reasons.add(new RejectionReason(RejectionReason.Code.NEGATIVE_OR_ZERO_AMOUNT, "Amount must be greater than zero. Got: " + tx.getAmount()));
        }

        Asset asset = assets.get(tx.getAssetName());
        if (asset == null) {
            reasons.add(new RejectionReason(RejectionReason.Code.UNKNOWN_ASSET, "Asset does not exist: " + tx.getAssetName()));
            return ValidationResult.rejected(reasons);
        }

        if (tx.getType() == Transaction.Type.SELL && asset.getValue() < tx.getAmount()) {
            reasons.add(new RejectionReason(RejectionReason.Code.INSUFFICIENT_ASSET_VALUE, "Cannot sell " + tx.getAmount() + " from " + tx.getAssetName() + " (only " + asset.getValue() + " available)."));
        }

        return reasons.isEmpty() ? ValidationResult.ok() : ValidationResult.rejected(reasons);
    }

    private void applyInternal(Transaction tx) {
        Asset asset = assets.get(tx.getAssetName());

        if (tx.getType() == Transaction.Type.BUY) {
            asset.setValue(asset.getValue() + tx.getAmount());
            totalValue += tx.getAmount();
            return;
        }

        if (tx.getType() == Transaction.Type.SELL) {
            asset.setValue(asset.getValue() - tx.getAmount());
            totalValue -= tx.getAmount();
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


