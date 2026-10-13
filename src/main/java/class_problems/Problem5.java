public class Problem5 {

    interface Renewable {
        String renew();
    }

    interface Reservable {
        String reserve();
    }

    static abstract class LibraryItem {
        private final String itemId;
        private static int counter = 1001;

        LibraryItem() {
            itemId = "LIB-" + counter++;
        }

        public abstract int getLoanPeriodDays();

        public String getItemId() {
            return itemId;
        }
    }

    static class Textbook extends LibraryItem
            implements Renewable, Reservable {
        private String title;

        Textbook(String title) {
            this.title = title;
        }

        public int getLoanPeriodDays() {
            return 14;
        }

        public String renew() {
            return title + " renewed";
        }

        public String reserve() {
            return title + " reserved";
        }
    }

    static class Magazine extends LibraryItem implements Renewable {
        private String title;

        Magazine(String title) {
            this.title = title;
        }

        public int getLoanPeriodDays() {
            return 7;
        }

        public String renew() {
            return title + " renewed";
        }
    }

    static class DigitalPass implements Renewable {
        private String resourceName;

        DigitalPass(String resourceName) {
            this.resourceName = resourceName;
        }

        public String renew() {
            return resourceName + " renewed";
        }
    }

    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(item.getItemId() + ": "
                    + item.getLoanPeriodDays() + " days");
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook t = new Textbook("Java Fundamentals");
        Magazine m = new Magazine("Tech Monthly");
        DigitalPass d = new DigitalPass("E-Journal Access");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        System.out.println(m.renew());
        System.out.println(reserveIfSupported(m));

        System.out.println(d.renew());
        System.out.println(reserveIfSupported(d));

        LibraryItem ref = t;
        System.out.println(reserveIfSupported(ref));

        processCheckouts(new LibraryItem[]{t, m});
    }
}