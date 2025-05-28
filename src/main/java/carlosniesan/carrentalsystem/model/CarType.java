package carlosniesan.carrentalsystem.model;

public enum CarType {
    PREMIUM(300.0, 0.2, 5),
    SUV(150.0, 0.6, 3),
    SMALL(50.0, 0.3, 1);

    private final double basePrice;
    private final double extraDaySurchargeRate;
    private final int loyaltyPoints;

    CarType(double basePrice, double extraDaySurchargeRate, int loyaltyPoints) {
        this.basePrice = basePrice;
        this.extraDaySurchargeRate = extraDaySurchargeRate;
        this.loyaltyPoints = loyaltyPoints;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getExtraDaySurchargeRate() {
        return extraDaySurchargeRate;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}