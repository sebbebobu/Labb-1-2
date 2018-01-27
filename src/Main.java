import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main extends JPanel{
    public Saab95 saab = new Saab95();
    public Volvo240 volvo = new Volvo240();
    List<Car> cars = new ArrayList<>(); // car[0] = VOLVO car[1] = SAAB
    public Main(){
        saab.setPosition(new Point(50,50));
        saab.startEngine();
        volvo.startEngine();
        saab.gas(1);
        saab.gas(1);
        volvo.gas(1);
        volvo.gas(1);
        //volvo.turnLeft();
        //volvo.turnLeft();
        //volvo.turnLeft();
        cars.add(volvo);
        cars.add(saab);
    }

    public void paint(Graphics g){
        super.paint(g);
        for (Car c : cars){
            g.setColor(c.getColor());
            if(c.getDirection() == Car.dir.NORTH || c.getDirection() == Car.dir.SOUTH){
                g.fillRect(c.position.x-10,c.position.y-20,20,40);
                g.setColor(Color.black);
                g.drawString(c.getModelName(),c.position.x-20,c.position.y-25);
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
                g.setColor(c.getColor());
                g.fillRect(c.position.x-20,c.position.y-10,40,20);
                g.setColor(Color.black);
                g.drawString(c.getModelName(),c.position.x-25,c.position.y-15);
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
        while(!(selectedCar.equals(saab) || selectedCar.equals(volvo))){
            System.out.println("Select vehicle [S] Saab or [V] Volvo: ");
            choice = scanner.next();
            switch (choice){
                case "S":
                    selectedCar = saab;
                    break;
                case "V":
                    selectedCar = volvo;
                    break;
            }

        }
        boolean done = false;
        while(!done){
            System.out.println("This vehicle's currentSpeed: "+selectedCar.getCurrentSpeed()+" pixels/move");
            System.out.println("Select action [M] Move, [L] Turn Left or [R] Turn Right: ");
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
                    done = true;
                    break;
                case "R":
                    selectedCar.turnRight();
                    done = true;
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