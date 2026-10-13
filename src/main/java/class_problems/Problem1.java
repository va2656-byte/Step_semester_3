
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void energy();
}

class Device {
    String id;
    boolean on;

    Device(String id) {
        this.id = id;
        this.on = false;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    int units;

    Plug(String id, int units) {
        super(id);
        this.units = units;
    }

    public void energy() {
        System.out.println(id + " energy " + units + " kWh");
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Light l = new Light("L1");
        Fan f = new Fan("F1");
        Plug p = new Plug("P1", 12);

        l.turnOn();
        l.dim(40);

        System.out.println("F1 rejected: DIM unsupported");
        f.schedule("22:00");

        p.energy();
        System.out.println("L1 rejected: ENERGY unsupported");
    }
}
