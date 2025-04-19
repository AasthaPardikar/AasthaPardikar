// Define a custom functional interface
@FunctionalInterface
interface DiscountStrategy {
    double applyDiscount(double price);
}

// Product class
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// DiscountService class
class DiscountService {
    public double applyDiscount(Product product, DiscountStrategy strategy) {
        return strategy.applyDiscount(product.getPrice());
    }
}

// Main class
public class ShoppingDiscountSystemCustom {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 1200);
        Product smartphone = new Product("Smartphone", 800);

        DiscountService discountService = new DiscountService();

        // Lambda 1: 10% discount
        DiscountStrategy tenPercentDiscount = price -> price * 0.9;

        // Lambda 2: Flat $100 discount if price > $1000
        DiscountStrategy flatDiscount = price -> price > 1000 ? price - 100 : price;

        // Lambda 3: No discount
        DiscountStrategy noDiscount = price -> price;

        // Applying discounts
        double laptopDiscounted = discountService.applyDiscount(laptop, tenPercentDiscount);
        double smartphoneFlat = discountService.applyDiscount(smartphone, flatDiscount);
        double smartphoneNoDiscount = discountService.applyDiscount(smartphone, noDiscount);

        // Displaying results
        System.out.println(laptop.getName() + " original: $" + laptop.getPrice() + ", after 10% discount: $" + laptopDiscounted);
        System.out.println(smartphone.getName() + " original: $" + smartphone.getPrice() + ", after flat discount: $" + smartphoneFlat);
        System.out.println(smartphone.getName() + " original: $" + smartphone.getPrice() + ", with no discount: $" + smartphoneNoDiscount);
    }
}
