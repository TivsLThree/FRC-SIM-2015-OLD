package frc.sim;

import frc.sim.Main.Type;
import java.awt.Color;
import java.awt.Graphics2D;

public class Person extends Entity {
    
    public String name = "Bob";
    public Type role = Type.NONE;
    public Type preferredRole = Type.NONE;
    public double skill = 1.0;
    public double likability = 1.0;
    public int yearsInFRC = 0;
    public Team team = null;
    
    public Person(String name, Team team) {
        super(0, 0, "head.png");
        this.name = name;
        this.team = team;
    }
    
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        Color oldColor = g.getColor();
        g.setColor(team.color);
        g.fillRect(x + (image.getWidth(null) / 2) - 2, y + image.getHeight(null) - 1, 3, 10);
        g.setColor(oldColor);
        g.drawRect(x + (image.getWidth(null) / 2) - 2, y + image.getHeight(null) - 1, 3, 10);
        Util.drawCentered(g, name + " the " + role.toString(), x + (image.getWidth(null) / 2), y - 30);
        Util.drawCentered(g, team.name, x + (image.getWidth(null) / 2), y - 15);
    }
    
    public static Person generatePerson(Team team) {
        String name = "Bob";
        //TODO random name
        Person p = new Person(name, team);
        if (Util.randomInt(1, 3) == 1) {
            switch (Util.randomInt(1, 4)) {
                case 1:
                    p.preferredRole = Type.PROGRAMMING;
                    break;
                case 2:
                    p.preferredRole = Type.MECHANICAL;
                    break;
                case 3:
                    p.preferredRole = Type.ELECTRICAL;
                    break;
                case 4:
                    p.preferredRole = Type.PR;
                    break;
            }
        }
        if (Util.randomInt(1, 20) == 1) { //The chance of making a GENIUS          
            p.skill = Util.randomInt(15, 20) / 10; //Geniuses are very skilled
            p.likability = Util.randomInt(0, 10) / 10; //Geniuses may not be very likable
        } else {
            p.skill = Util.randomInt(8, 12) / 10;
            p.likability = Util.randomInt(5, 15) / 10;
        }
        p.yearsInFRC = Util.randomInt(1, 5);
        if (p.yearsInFRC == 5) {
            p.role = Type.MENTOR;
            p.preferredRole = Type.NONE;
        }
        p.x = Util.randomInt(10, 780);
        p.y = Util.randomInt(10, 580);
        return p;
    }
}