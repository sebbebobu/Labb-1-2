import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main extends JPanel{
    public Saab95 saab = new Saab95();
    public Volvo240 volvo = new Volvo240();
    public Scania scania = new Scania();
    public BilTransport bilTransport;


    List<Car> cars = new ArrayList<>(); // car[0] = VOLVO car[1] = SAAB car[2] = SCANIA
    public Main(){
        saab.setPosition(new Point(50,50));
        volvo.setPosition(new Point(200,290));
        scania.setPosition(new Point(200,200));
        saab.startEngine();
        volvo.startEngine();
        scania.startEngine();
        saab.gas(1);
        saab.gas(1);
        volvo.gas(1);
        volvo.gas(1);
        scania.gas(1);
        scania.gas(1);
        //volvo.turnLeft();
        //volvo.turnLeft();
        //volvo.turnLeft();
        cars.add(scania);
        cars.add(volvo);
        cars.add(saab);


        // TODO WORK-AROUND
        scania.stopEngine();
        scania.lowerRamp();
        scania.startEngine();
        // TODO WORK-AROUND


        bilTransport = new BilTransport(scania);
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Car c : cars){
            if(c.getDirection() == Car.dir.NORTH || c.getDirection() == Car.dir.SOUTH){
                if(c instanceof Scania){
                    if (c.getDirection().equals(Car.dir.NORTH)){
                        g.setColor(c.getColor());
                        g.fillRect(c.position.x-13,c.position.y-45,26,15);
                        g.setColor(Color.gray);
                        g.fillRect(c.position.x-15,c.position.y-30,30,60);
                        if(!bilTransport.isRampUp()) g.fillRect(c.position.x-12,c.position.y+30,24,25);
                        g.setColor(Color.black);
                        g.drawString(c.getModelName(),c.position.x-20,c.position.y-50);

                    }
                    if (c.getDirection().equals(Car.dir.SOUTH)){
                        g.setColor(c.getColor());
                        g.fillRect(c.position.x-13,c.position.y+30,26,15);
                        g.setColor(Color.gray);
                        g.fillRect(c.position.x-15,c.position.y-30,30,60);
                        if(!bilTransport.isRampUp()) g.fillRect(c.position.x-12,c.position.y-55,24,25);
                        g.setColor(Color.black);
                        g.drawString(c.getModelName(),c.position.x-20,c.position.y-50);
                    }
                }
                else {
                    g.setColor(c.getColor());
                    g.fillRect(c.position.x-10,c.position.y-20,20,40);
                    g.setColor(Color.black);
                    g.drawString(c.getModelName(),c.position.x-20,c.position.y-25);
                }
                g.setColor(Color.white);
                g.drawLine(c.position.x,c.position.y-20,c.position.x,c.position.y+20);
                if(c.getDirection() == Car.dir.NORTH){ // UPP
                    g.drawLine(c.position.x,c.position.y-20,c.position.x-10,c.position.y);
                    g.drawLine(c.position.x,c.position.y-20,c.position.x+10,c.position.y);
                }
                else{ // NER
                    g.drawLine(c.position.x,c.position.y+20,c.position.x-10,c.position.y);
                    g.drawLine(c.position.x,c.position.y+20,c.position.x+10,c.position.y);
                }

            }
            else if (c.getDirection() == Car.dir.WEST || c.getDirection() == Car.dir.EAST){
                if(c instanceof Scania){
                    if (c.getDirection().equals(Car.dir.WEST)){
                        g.setColor(c.getColor());
                        g.fillRect(c.position.x-45,c.position.y-13,15,26);
                        g.setColor(Color.gray);
                        g.fillRect(c.position.x-30,c.position.y-15,60,30);
                        if(!bilTransport.isRampUp()) g.fillRect(c.position.x+30,c.position.y-12,24,25);
                        g.setColor(Color.black);
                        g.drawString(c.getModelName(),c.position.x-20,c.position.y-20);
                    }
                    if (c.getDirection().equals(Car.dir.EAST)){
                        g.setColor(c.getColor());
                        g.fillRect(c.position.x+30,c.position.y-13,15,26);
                        g.setColor(Color.gray);
                        g.fillRect(c.position.x-30,c.position.y-15,60,30);
                        if(!bilTransport.isRampUp()) g.fillRect(c.position.x-54,c.position.y-12,24,25);
                        g.setColor(Color.black);
                        g.drawString(c.getModelName(),c.position.x-20,c.position.y-20);
                    }
                }
                else {
                    g.setColor(c.getColor());
                    g.fillRect(c.position.x - 20, c.position.y - 10, 40, 20);
                    g.setColor(Color.black);
                    g.drawString(c.getModelName(), c.position.x - 25, c.position.y - 15);
                }
                g.setColor(Color.white);
                g.drawLine(c.position.x-20,c.position.y,c.position.x+20,c.position.y);
                if(c.getDirection() == Car.dir.WEST){ // VÄNSTER
                    g.drawLine(c.position.x-20,c.position.y,c.position.x,c.position.y-10);
                    g.drawLine(c.position.x-20,c.position.y,c.position.x,c.position.y+10);
                }
                else{ // HÖGER
                    g.drawLine(c.position.x+20,c.position.y,c.position.x,c.position.y-10);
                    g.drawLine(c.position.x+20,c.position.y,c.position.x,c.position.y+10);
                }
            }

            //g.drawLine(c.position.x,c.position.y,c.position.x,c.position.y); // Mitten av bilen.

        }
    }
    public void doStuffFirst(){

    }
    public void doStuff(){
        cars.get(0).turnLeft();
        cars.get(0).turnLeft();
    }
    public void Update(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        Car selectedCar = new Volvo240();
        while(!(selectedCar.equals(saab) || selectedCar.equals(volvo) || selectedCar.equals(scania))){
            System.out.println("Select vehicle [S] Saab, [V] Volvo or [B] Biltransport: ");
            choice = scanner.next();
            switch (choice){
                case "S":
                    selectedCar = saab;
                    break;
                case "V":
                    selectedCar = volvo;
                    break;
                case "B":
                    selectedCar = scania;
                    break;
            }
        }
        boolean done = false;
        while(!done){
            System.out.println("This vehicle's currentSpeed: "+selectedCar.getCurrentSpeed()+" pixels/move");
            System.out.println("Select action [M] Move, [L] Turn Left or [R] Turn Right: ");
            if (selectedCar.equals(scania)){
                System.out.println("Additional actions for Scania;");
                System.out.println("[O] Lower Ramp, [H] Lift Ramp, [U] Detach Vehicle, [A] Attach Vehicle: ");
            }
            choice = scanner.next();
            switch (choice){
                case "M":
                    boolean moved = false;
                    double amount = 0;
                    while(!moved){
                        System.out.println("Select action [G] Gas, [B] Brake or [R] Roll: ");
                        choice = scanner.next();
                        System.out.println("Enter an amount between [0,1] : ");
                        amount = scanner.nextDouble();
                        switch (choice){
                            case "G":
                                selectedCar.gas(amount);
                                moved = true;
                                break;
                            case "B":
                                selectedCar.brake(amount);
                                moved = true;
                                break;
                            case "R":
                                moved = true;
                                break;
                        }
                    }
                    selectedCar.move();
                    done = true;
                    break;
                case "L":
                    selectedCar.turnLeft();
                    if(selectedCar instanceof Scania) {
                        for(Car car : bilTransport.cars){
                            car.turnLeft();
                        }
                    }
                    done = true;
                    break;
                case "R":
                    selectedCar.turnRight();
                    if(selectedCar instanceof Scania) {
                        for(Car car : bilTransport.cars){
                            car.turnRight();
                        }
                    }
                    done = true;
                    break;
                case "O":
                    if(selectedCar instanceof Scania) {
                        scania.lowerRamp();
                        done = true;
                    }
                    break;
                case "H":
                    if(selectedCar instanceof Scania) {
                        scania.liftRamp();
                        done = true;
                    }
                    break;
                case "A":
                    if(selectedCar instanceof Scania) {
                        bilTransport.attachVehicle(saab);
                        bilTransport.attachVehicle(volvo);
                        done = true;
                    }
                    break;
                case "U":
                    if(selectedCar instanceof Scania) {
                        bilTransport.detachVehicle();
                        done = true;
                    }
                    break;
            }
        }
        //selectedCar.turnRight();
    }
    public static void main(String[] args){
        Main objects = new Main();
        JFrame frame = new JFrame("Car Simulation Window Here");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,400,400);
        frame.getContentPane().add(objects);
        frame.setVisible(true);

        while(true){ // MAIN-LOOP
            objects.Update();
            frame.getContentPane().repaint();
        }

        //TODO IGNORE - disgusting testing method.
        /*
        List<Bil> biles = new ArrayList<>();
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Saab95());
        biles.add(new Saab95());
        biles.add(new Saab95());
        biles.add(new Saab95());
        for (Bil b : biles) {
            b.stopEngine();
            b.startEngine();
            b.getCurrentSpeed();
            b.getColor();
            b.getNrDoors();
            b.getEnginePower();
            b.move();
            b.turnLeft();
            b.turnRight();
            b.setColor(Color.BLUE);
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            if (b instanceof Saab95){
                ((Saab95) b).gas(0);
                ((Saab95) b).brake(0);
                ((Saab95) b).gas(9000);
                ((Saab95) b).brake(9000);
                ((Saab95) b).setTurboOff();
                ((Saab95) b).setTurboOn();
                ((Saab95) b).speedFactor();
            }
            else if (b instanceof Volvo240){
                ((Volvo240) b).brake(0);
                ((Volvo240) b).gas(0);
                ((Volvo240) b).brake(9000);
                ((Volvo240) b).gas(9000);
            }
        }
        */
    }
}