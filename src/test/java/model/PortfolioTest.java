package model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {

    @Test
    void buyTransactionShouldIncreaseTotalValue() {

        // Arrange
        Portfolio portfolio = new Portfolio(new HashMap<>());
        Asset stock = new Asset("AAPL", 1000, 0.2);
        portfolio.addAsset(stock);

        Transaction buy = new Transaction(Transaction.Type.BUY, "AAPL", 500);

        double before = portfolio.getTotalValue();

        // Act
        portfolio.applyTransaction(buy);

        double after = portfolio.getTotalValue();

        // Assert
        assertEquals(before + 500, after);
    }
    @Test
    void sellTransactionShouldDecreaseTotalValue() {

        Portfolio portfolio = new Portfolio(new HashMap<>());
        Asset bond = new Asset("BOND", 2000, 0.05);
        portfolio.addAsset(bond);

        Transaction sell = new Transaction(Transaction.Type.SELL, "BOND", 300);

        double before = portfolio.getTotalValue();

        portfolio.applyTransaction(sell);

        double after = portfolio.getTotalValue();

        assertEquals(before - 300, after);
    }

    @Test
    void duplicateTransactionShouldBeRejected() {

        Portfolio portfolio = new Portfolio(new HashMap<>());
        Asset crypto = new Asset("BTC", 1000, 0.8);
        portfolio.addAsset(crypto);

        Transaction tx = new Transaction(Transaction.Type.BUY, "BTC", 200);

        portfolio.applyTransaction(tx);

        double afterFirst = portfolio.getTotalValue();

        // Apply the same transaction again
        portfolio.applyTransaction(tx);

        double afterSecond = portfolio.getTotalValue();

        // Total value should not change the second time
        assertEquals(afterFirst, afterSecond);

        // Audit log should contain a rejection entry
        assertEquals(2, portfolio.getAuditLog().size());
        assertEquals(AuditEntry.Status.REJECTED,
                portfolio.getAuditLog().get(1).getStatus());
    }
}
