package model;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class AuditEntry {

    public enum Status {
        APPLIED,
        REJECTED
    }

    private final UUID transactionId;
    private final Instant timestamp;
    private final Status status;
    private final List<RejectionReason> reasons;
    private final double beforeTotalValue;
    private final double afterTotalValue;

    public AuditEntry(UUID transactionId,
                      Status status,
                      List<RejectionReason> reasons,
                      double beforeTotalValue,
                      double afterTotalValue) {

        this.transactionId = transactionId;
        this.timestamp = Instant.now();
        this.status = status;
        this.reasons = reasons == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(reasons);
        this.beforeTotalValue = beforeTotalValue;
        this.afterTotalValue = afterTotalValue;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public List<RejectionReason> getReasons() {
        return reasons;
    }

    public double getBeforeTotalValue() {
        return beforeTotalValue;
    }

    public double getAfterTotalValue() {
        return afterTotalValue;
    }
}

