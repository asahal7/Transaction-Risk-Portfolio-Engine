import model.Asset;
import model.Portfolio;
import model.Transaction;
import service.RiskCalculator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Create assets
        Asset stock = new Asset("Stock A", 1000, 0.2);
        Asset bond = new Asset("Bond B", 5000, 0.05);
        Asset crypto = new Asset("Crypto C", 2000, 0.8);

        // Create portfolio
        Portfolio portfolio = new Portfolio(new HashMap<>());
        portfolio.addAsset(stock);
        portfolio.addAsset(bond);
        portfolio.addAsset(crypto);

        System.out.println("Initial Portfolio:");
        System.out.println(portfolio);
        System.out.println("Initial Risk: " + RiskCalculator.calculatePortfolioRisk(portfolio));

        // Apply transactions
        Transaction buyStock = new Transaction(Transaction.Type.BUY, stock.getName(), 2000);
        Transaction sellBond = new Transaction(Transaction.Type.SELL, bond.getName(), 1000);
        portfolio.applyTransaction(buyStock);
        portfolio.applyTransaction(sellBond);

        System.out.println("\nAfter Transactions:");
        System.out.println(portfolio);
        System.out.println("Risk: " + RiskCalculator.calculatePortfolioRisk(portfolio));

        // Simulate market changes
        RiskCalculator.simulateMarketFluctuations(portfolio);
        System.out.println("\nAfter Market Fluctuations:");
        System.out.println(portfolio);
        System.out.println("Risk: " + RiskCalculator.calculatePortfolioRisk(portfolio));
    }
}
