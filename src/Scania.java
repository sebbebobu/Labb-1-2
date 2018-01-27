import java.awt.*;

public class Scania extends Car {

    /**
     * Defines the angle for the bed of the truck.
     */
    protected double bedAngle = 0; // 0 is up. 70 is down.

    /**
     * Simple Getter.
     * @return The truck-bed's angle.
     */
    public double getBedAngle() {
        return bedAngle;
    }
    /**
     * Simple Setter.
     * @param bedAngle Wanted angle for bed.
     */
    public void setBedAngle(double bedAngle) {
        this.bedAngle = bedAngle;
    }


    /**
     * Constructor for Scania. Creates Scania-object with the given parameters.
     */
    public Scania() {
        super(2, 360, 0, Color.blue, "Scania");
    }

    /**
     * Absolute maximum acceleration based on engine-power.
     * @return EnginePower / 100.
     */
    public double speedFactor(){
        return enginePower * 0.01;
    }

    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of gas.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of brake.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void liftRamp(){
        setBedAngle(0);
    }

    public void lowerRamp(){
        if(!isEngineOn())
            setBedAngle(70);
    }
}
