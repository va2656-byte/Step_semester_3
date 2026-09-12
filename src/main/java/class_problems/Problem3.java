import java.util.*;

class PatientVitals {

    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        for (double reading : initialReadings) {
            recordReading(reading);
        }
    }

    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    public double getAverage() {

        if (count == 0) {
            return 0.0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    public double[] getAllReadings() {

        double[] copy = new double[count];

        for (int i = 0; i < count; i++) {
            copy[i] = readings[i];
        }

        return copy;
    }
}

public class Problem3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of initial readings: ");
        int n = sc.nextInt();

        double[] initialReadings =
                new double[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Enter reading " + (i + 1) + ": ");

            initialReadings[i] =
                    sc.nextDouble();
        }

        PatientVitals v =
                new PatientVitals(initialReadings);

        System.out.println("\nValid readings:");

        double[] result =
                v.getAllReadings();

        for (double reading : result) {
            System.out.println(reading);
        }

        System.out.println(
                "Average: " + v.getAverage());

        System.out.print(
                "\nEnter another reading: ");

        double newReading =
                sc.nextDouble();

        v.recordReading(newReading);

        System.out.println(
                "Updated readings:");

        result = v.getAllReadings();

        for (double reading : result) {
            System.out.println(reading);
        }

        System.out.println(
                "Updated average: " +
                v.getAverage());

        sc.close();
    }
}