
import java.util.*;

interface Capability {
    String getName();
    boolean apply(int value, String deviceName);
}

class PowerCapability implements Capability {
    private boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean apply(int value, String name) {
        if (value != 0 && value != 1) {
            System.out.println("Rejected: Power must be 0 or 1.");
            return false;
        }

        on = value == 1;
        System.out.println(name + ": " + (on ? "ON" : "OFF"));
        return true;
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    public String getName() {
        return "Brightness";
    }

    public boolean apply(int value, String name) {
        if (value < 0 || value > 100) {
            System.out.println("Rejected: Brightness must be between 0 and 100%.");
            return false;
        }

        brightness = value;
        System.out.println(name + ": brightness set to "
                + brightness + "%.");
        return true;
    }
}

class TemperatureCapability implements Capability {
    private int temperature = 24;

    public String getName() {
        return "Temperature";
    }

    public boolean apply(int value, String name) {
        if (value < 16 || value > 30) {
            System.out.println("Rejected: " + name
                    + " temperature must be between 16°C and 30°C.");
            return false;
        }

        temperature = value;
        System.out.println(name + ": temperature set to "
                + temperature + "°C.");
        return true;
    }
}

class TimerCapability implements Capability {
    private int minutes;

    public String getName() {
        return "Timer";
    }

    public boolean apply(int value, String name) {
        if (value < 0) {
            System.out.println("Rejected: Timer cannot be negative.");
            return false;
        }

        minutes = value;
        System.out.println(name + ": timer set to " + minutes + " minutes.");
        return true;
    }
}

class Device {
    String name;
    Map<String, Capability> capabilities = new LinkedHashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability capability) {
        if (capabilities.containsKey(capability.getName())) {
            return;
        }

        capabilities.put(capability.getName(), capability);
        System.out.println(name + ": " + capability.getName()
                + " capability added.");
    }

    boolean supports(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }

    boolean apply(String capabilityName, int value) {
        Capability c = capabilities.get(capabilityName);

        if (c == null) {
            return false;
        }

        return c.apply(value, name);
    }
}

class SceneStep {
    String capabilityName;
    int value;

    SceneStep(String capabilityName, int value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }
}

class Scene {
    String name;
    ArrayList<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(String capabilityName, int value) {
        steps.add(new SceneStep(capabilityName, value));
    }

    void execute(ArrayList<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.supports(step.capabilityName)) {
                    if (device.apply(step.capabilityName, step.value)) {
                        actions++;
                    }
                }
            }
        }

        System.out.println("Scene '" + name
                + "' completed: " + actions + " actions applied.");
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        ArrayList<Device> devices = new ArrayList<>();
        devices.add(ac);
        devices.add(lights);
        devices.add(projector);

        Scene lecture = new Scene("Lecture Mode");
        lecture.addStep("Power", 1);
        lecture.addStep("Brightness", 40);
        lecture.addStep("Temperature", 24);
        lecture.execute(devices);

        ac.apply("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.apply("Brightness", 70);
    }
}
