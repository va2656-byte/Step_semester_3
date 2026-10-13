public class Problem1 {

    static abstract class PaymentMethod {
        private final String transactionId;
        private static int counter = 1001;

        PaymentMethod() {
            transactionId = "TXN-" + counter++;
        }

        public abstract String processPayment(double amount);

        public String processPayment(double amount, String note) {
            return processPayment(amount) + " (" + note + ")";
        }

        public String getTransactionId() {
            return transactionId;
        }
    }

    static class CreditCardPayment extends PaymentMethod {
        private String cardNumberLastFour;

        CreditCardPayment(String cardNumberLastFour) {
            this.cardNumberLastFour = cardNumberLastFour;
        }

        public String processPayment(double amount) {
            return "Charged $" + amount + " to card ending "
                    + cardNumberLastFour + " - Txn " + getTransactionId();
        }
    }

    static class CashPayment extends PaymentMethod {
        public String processPayment(double amount) {
            return "Received $" + amount + " in cash - Txn "
                    + getTransactionId();
        }
    }

    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc = new CreditCardPayment("4471");
        PaymentMethod ref = cc;

        System.out.println(cc.processPayment(250.0));
        System.out.println(cc.processPayment(250.0, "Birthday gift"));

        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        printConfirmation(ref, 250.0);
    }
}