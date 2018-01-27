import java.awt.*;

public class Saab95 extends Car{
    private boolean turboOn = false;
    public Saab95(){
        super(2,125,0,Color.red,"Saab95");
    }

    /**
     * It's a getter essentially........
     * @return boolean-value of turboOn.
     */
    public boolean isTurboOn(){
        return turboOn;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * Absolute maximum acceleration based on turbo and engine-power.
     * @return EnginePower * Turbo-power / 100. Depending on whether turbo is on or not.
     */
    public double speedFactor(){
        double turbo = 1;
        if(turboOn)
            turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of gas.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of brake.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }


}
