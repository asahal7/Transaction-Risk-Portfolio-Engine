package service;

import model.Asset;
import model.Portfolio;

import java.util.Random;

public class RiskCalculator {

    private static Random random = new Random();

    // Weighted risk based on asset value
    public static double calculatePortfolioRisk(Portfolio portfolio) {
        double totalValue = portfolio.getTotalValue();
        if (totalValue == 0) return 0;

        double weightedRisk = 0;
        for (Asset asset : portfolio.getAssetsView()) {
            weightedRisk += (asset.getValue() / totalValue) * asset.getRisk();
        }
        return weightedRisk;
    }

    // Simulate risk fluctuations
    public static void simulateMarketFluctuations(Portfolio portfolio) {
        for (Asset asset : portfolio.getAssetsView()) {
            double changeFactor = 0.9 + 0.2 * random.nextDouble(); // +/-10%
            asset.setValue(asset.getValue() * changeFactor);
        }
    }
}
