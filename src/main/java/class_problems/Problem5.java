
class Resource {
    String id;
    boolean reservable;
    boolean downloadable;

    Resource(String id, boolean reservable, boolean downloadable) {
        this.id = id;
        this.reservable = reservable;
        this.downloadable = downloadable;
    }
}

public class Problem5 {
    static Resource[] resources = new Resource[100];
    static int size = 0;

    static int find(String id) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compare = resources[mid].id.compareTo(id);

            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static void add(Resource r) {
        if (find(r.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }

        int i = size - 1;

        while (i >= 0 && resources[i].id.compareTo(r.id) > 0) {
            resources[i + 1] = resources[i];
            i--;
        }

        resources[i + 1] = r;
        size++;

        System.out.println(r.id + " added");
    }

    static void reserve(String id, String member) {
        int i = find(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (!resources[i].reservable) {
            System.out.println(id + " rejected: reserve unsupported");
        } else {
            System.out.println(id + " reserved for " + member);
        }
    }

    static void download(String id) {
        int i = find(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (!resources[i].downloadable) {
            System.out.println(id + " rejected: download unsupported");
        } else {
            System.out.println(id + " downloaded");
        }
    }

    static void findAndPrint(String id) {
        int i = find(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else {
            System.out.println(id + " found at index " + i);
        }
    }

    public static void main(String[] args) {
        add(new Resource("B1", true, false));
        add(new Resource("B1", true, false));
        add(new Resource("E1", false, true));

        reserve("B1", "M1");
        download("B1");
        download("E1");
        findAndPrint("E1");
    }
}
