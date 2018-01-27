import java.awt.*;

public abstract class Car implements Movable{

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car

    public String getModelName() {
        return modelName;
    }

    protected String modelName; // The car model name

    protected enum dir{
        NORTH, SOUTH, EAST, WEST
    }

    public dir getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    protected dir direction = dir.NORTH;

    public void setPosition(Point position) {
        this.position = position;
    }

    protected Point position = new Point(150,150); // 0,0


    /**
     * Constructor for Bil. Creates Bil-object with the given parameters.
     * @param nrDoors Number of doors.
     * @param enginePower The Power of the Engine.
     * @param currentSpeed The cars speed currently.
     * @param color The color of the car.
     * @param modelName Name of the car model.
     */
    public Car(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    /**
     * Method which sets currentSpeed to 0.1.
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Method which stop the engine and sets currentSpeed to 0.
     */
    public void stopEngine(){
        currentSpeed = 0;
    }


    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Relocates the car in question depending on it's current direction and position.
     */
    @Override
    public void move() {
        switch (direction){
            case NORTH:
                position.y = (int) (position.y - currentSpeed);
                break;
            case WEST:
                position.x = (int) (position.x - currentSpeed);
                break;
            case EAST:
                position.x = (int) (position.x + currentSpeed);
                break;
            case SOUTH:
                position.y = (int) (position.y + currentSpeed);
                break;
        }
    }

    /**
     * "Turns" the car to the left. Changes it's current direction by -90 degrees.
     */
    @Override
    public void turnLeft() {
        switch (direction){
            case NORTH:
                direction = dir.WEST;
                break;
            case WEST:
                direction = dir.SOUTH;
                break;
            case EAST:
                direction = dir.NORTH;
                break;
            case SOUTH:
                direction = dir.EAST;
                break;
        }
    }
    /**
     * "Turns" the car to the right. Changes it's current direction by +90 degrees.
     */
    @Override
    public void turnRight() {
        switch (direction){
            case NORTH:
                direction = dir.EAST;
                break;
            case WEST:
                direction = dir.NORTH;
                break;
            case EAST:
                direction = dir.SOUTH;
                break;
            case SOUTH:
                direction = dir.WEST;
                break;
        }
    }
    public abstract void gas(double amount);

    public abstract void brake(double amount);
}
