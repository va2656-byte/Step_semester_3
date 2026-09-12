import java.util.*;

class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        if (this.priority != other.priority)
            return Integer.compare(
                this.priority,
                other.priority
            );

        int nameResult =
            this.routeName.compareToIgnoreCase(other.routeName);

        if (nameResult != 0)
            return nameResult;

        return this.routeCode.compareToIgnoreCase(
            other.routeCode
        );
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = routes.clone();

        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - i - 1; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }
}

public class Problem3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of routes: ");
        int n = sc.nextInt();
        sc.nextLine();

        BusRoute[] routes = new BusRoute[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nRoute " + (i + 1));

            System.out.print("Enter route code: ");
            String code = sc.nextLine();

            System.out.print("Enter route name: ");
            String name = sc.nextLine();

            System.out.print("Enter priority: ");
            int priority = sc.nextInt();
            sc.nextLine();

            routes[i] =
                new BusRoute(code, name, priority);
        }

        BusRoute[] ranked =
            BusRoute.rankRoutes(routes);

        System.out.println("\nRanked Routes:");

        for (BusRoute route : ranked) {

            System.out.println(
                route.getRouteCode()
                + " | "
                + route.getRouteName()
                + " | Priority: "
                + route.getPriority()
            );
        }

        sc.close();
    }
}