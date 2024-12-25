package payment;

public class PaymentForm {
    private double accountBalance;

    public PaymentForm(double initialBalance) {
        this.accountBalance = initialBalance;
    }

    public boolean processPayment(double paymentAmount, double discountRate, double taxRate) {
        // Validate input
        if (paymentAmount <= 0 || discountRate < 0 || taxRate < 0) {
            throw new IllegalArgumentException("Invalid payment details provided.");
        }

        // Calculate final amount with discount and tax
        double discount = paymentAmount * discountRate / 100;
        double tax = paymentAmount * taxRate / 100;
        double finalAmount = paymentAmount - discount + tax;

        // Ensure sufficient balance
        if (accountBalance < finalAmount) {
            System.out.println("Insufficient balance to process payment.");
            return false;
        }

        // Deduct final amount from balance
        accountBalance -= finalAmount;

        // Log the transaction
        System.out.printf("Payment processed successfully. Amount: %.2f, Discount: %.2f, Tax: %.2f, Final Amount: %.2f%n",
                paymentAmount, discount, tax, finalAmount);
        return true;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public static void main(String[] args) {
        PaymentForm processor = new PaymentForm(5000); // Initial balance

        // Example payment processing
        boolean result = processor.processPayment(1000, 10, 5); // 10% discount, 5% tax
        System.out.printf("Payment successful: %b, Remaining Balance: %.2f%n", result, processor.getAccountBalance());
    }
}
