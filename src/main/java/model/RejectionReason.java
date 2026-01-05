package model;

public final class RejectionReason {

    public enum Code {
        NULL_TRANSACTION,
        DUPLICATE_TRANSACTION,
        UNKNOWN_ASSET,
        NEGATIVE_OR_ZERO_AMOUNT,
        INSUFFICIENT_ASSET_VALUE
    }

    private final Code code;
    private final String message;

    public RejectionReason(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
