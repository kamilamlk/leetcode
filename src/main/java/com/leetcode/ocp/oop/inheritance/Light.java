package com.leetcode.ocp.oop.inheritance;

class InvalidHoursException extends Exception {
    public InvalidHoursException(String message) {
        super(message);
    }
}
class NegativeHoursException extends InvalidHoursException {
    public NegativeHoursException() {
        super("Negative hours");
    }
}
class ZeroHoursException extends InvalidHoursException {
    public ZeroHoursException() {
        super("Zero hours");
    }
}

class LightExample {
    public static void main(String[] args) throws InvalidHoursException {
        NeonLight neonLight = new NeonLight("Neon Light");
        neonLight.demonstrate();
    }
}

public class Light {
    protected String lightType = "Generic Light";

    public Light() {
        System.out.println("Light constructor");
    }

    protected double energyCost(int hours) throws InvalidHoursException {
        if (hours < 0) {
            throw new NegativeHoursException();
        } else if (hours == 0) {
            throw new ZeroHoursException();
        }
        double cost = hours * 0.5; // Example cost calculation
        System.out.println("Energy cost for " + lightType + ": " + cost);
        return cost;
    }

    public Light makeInstance() {
        System.out.println("Light");
        return new Light();
    }

    public void showSign() {
        System.out.println("Light.showSign");
    }

    public static void printLightType() {
        System.out.println("Static. General Light");
    }
}

class TubeLight extends Light {
    protected String lightType = "Tube Light";

    public TubeLight() {
        this("Tube Light");
        System.out.println("TubeLight constructor");
    }

    public TubeLight(String lightType) {
        this.lightType = lightType;
        System.out.printf("Tube %s constructor%n", lightType);
    }

    @Override
    public double energyCost(int hours) throws InvalidHoursException {
        if (hours < 0) {
            throw new NegativeHoursException();
        } else if (hours == 0) {
            throw new ZeroHoursException();
        }
        double cost = hours * 0.3; // Example cost calculation for Tube Light
        System.out.println("Energy cost for " + lightType + ": " + cost);
        return cost;
    }

    public double energyCost() {
        double flatRate = 20.0; // Example flat rate
        System.out.println("Energy cost for " + lightType + ": " + flatRate);
        return flatRate;
    }

    @Override
    public TubeLight makeInstance() {
        System.out.println("TubeLight");
        return new TubeLight();
    }


    public static void printLightType() {
        System.out.println("Static. Tube Light");
    }
}

class NeonLight extends TubeLight {
    protected String lightType = "Neon Light";

    public NeonLight(String lightType) {
        this.lightType = lightType;
        System.out.printf("Neon %s constructor%n", lightType);
    }

    public void demonstrate() throws InvalidHoursException {
        super.showSign(); // Light's showSign
        super.energyCost(50); // TubeLight's energyCost
        ((Light) this).energyCost(50); // TubeLight's energyCost

        System.out.println("NeonLight's lightType: " + lightType);
        System.out.println("TubeLight's lightType: " + super.lightType);
        System.out.println("Light's lightType: " + ((Light) this).lightType); // Light's lightType. Accessing hidden field
        super.printLightType(); // Static method from TubeLight

        ((TubeLight) this).printLightType(); // Static method from TubeLight
        ((Light) this).printLightType(); // Static method from Light
    }
}



