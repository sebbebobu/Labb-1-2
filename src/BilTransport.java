import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BilTransport {
    protected Scania scania;
    protected List<Car> cars;
    protected final int maxCapacity = 1; // Not something usually changeable...
    protected boolean rampUp;

    //TODO OR NOT TODO; If our truck turns - none of our loaded vehicles turn! :D


    /**
     * Getter for rampUp - which checks the scania-object current ramp-angle.
     * @return Getter for rampUp.
     */
    public boolean isRampUp() {
        rampUp = scania.getBedAngle() == 0;
        return rampUp;
    }

    public BilTransport(Scania scania) { // Can not add cars at init.. Needs to be called by attachVehicle for obvious reasons.
        this.scania = scania;
        this.cars = new ArrayList<>(maxCapacity);
        rampUp = scania.getBedAngle() == 0;
    }

    /**
     * Checks if it's possible to attach the given vehicle based on location (but also direction!).
     * @param car The given vehicle to load onto the truckbed.
     */
    public void attachVehicle(Car car){
        if(!(car instanceof Scania) && cars.size() < maxCapacity && !isRampUp()){ // A Scania can't transport another Scania.
            Enum dirScania = scania.getDirection();
            Enum dirCar = car.getDirection();
            int xScania = scania.getPosition().x;
            int yScania = scania.getPosition().y;
            int xCar = car.getPosition().x;
            int yCar = car.getPosition().y;
            if (dirScania.equals(Car.dir.NORTH) && (dirCar.equals(Car.dir.NORTH) || dirCar.equals(Car.dir.SOUTH))){
                if (yScania < yCar && (xScania + 15 > xCar && xScania - 15 < xCar)) {
                    car.setPosition(scania.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Car.dir.SOUTH) && (dirCar.equals(Car.dir.NORTH) || dirCar.equals(Car.dir.SOUTH))){
                if (yScania > yCar && (xScania + 15 > xCar && xScania - 15 < xCar)) {
                    car.setPosition(scania.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Car.dir.WEST) && (dirCar.equals(Car.dir.WEST) || dirCar.equals(Car.dir.EAST))){
                if (xScania < xCar && (yScania + 15 > yCar && yScania - 15 < yCar)) {
                    car.setPosition(scania.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Car.dir.EAST) && (dirCar.equals(Car.dir.WEST) || dirCar.equals(Car.dir.EAST))){
                if (xScania > xCar && (yScania + 15 > yCar && yScania - 15 < yCar)) {
                    car.setPosition(scania.getPosition());
                    cars.add(car);
                }
            }
        }
    }

    /**
     * Detaches the last vehicle in the list (most recently added)
     * As long as there is a vehicle to unload and the ramp is lowered.
     */
    public void detachVehicle(){
        if(cars.size() >= 1 && !isRampUp()) {
            Car car = cars.get(cars.size() - 1);
            switch (scania.getDirection()){
                case NORTH:
                    car.setPosition(new Point(scania.getPosition().x, scania.getPosition().y+50));
                    break;
                case SOUTH:
                    car.setPosition(new Point(scania.getPosition().x, scania.getPosition().y-50));
                    break;
                case WEST:
                    car.setPosition(new Point(scania.getPosition().x+50, scania.getPosition().y));
                    break;
                case EAST:
                    car.setPosition(new Point(scania.getPosition().x-50, scania.getPosition().y));
                    break;
            }
            cars.remove(car);
        }
    }
}
