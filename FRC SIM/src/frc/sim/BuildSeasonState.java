package frc.sim;

import frc.sim.Main.Type;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class BuildSeasonState extends State {
    
    public Day[] days = new Day[]{Day.SATURDAY, Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY};
    public Day day = Day.SATURDAY;
    public int week = 1;
    public Person selected = null;
    public Timer timer;
    
    public BuildSeasonState() {
        int seperation = 130;
        int start = 10;
        timer = new Timer(2000, (ActionEvent e) -> {
            int index = 0;
            for (Day d : days) {
                if (day == d) {
                    break;
                }
                index++;
            }
            if ((index + 1) < days.length) {
                day = days[index + 1];
            } else {
                week++;
                if (week != 6) {
                    day = Day.SATURDAY;
                } else {
                    //TODO end build season
                }
            }           
        });
        timer.start();
        
        entities.add(new Button(start, 50, "buttons/programming.png") {
            @Override
            public void clicked(MouseEvent m) {
                if (selected != null && selected.role != Type.MENTOR) {
                    selected.role = Type.PROGRAMMING;
                }
            }
            
            @Override
            public void tick() {
                super.tick();
                if (selected == null) {
                    visible = false;
                } else {
                    visible = true;
                }
            }
        });
        entities.add(new Button(start + seperation, 50, "buttons/mechanical.png") {
            @Override
            public void clicked(MouseEvent m) {
                if (selected != null && selected.role != Type.MENTOR) {
                    selected.role = Type.MECHANICAL;
                }
            }
            
            @Override
            public void tick() {
                super.tick();
                if (selected == null) {
                    visible = false;
                } else {
                    visible = true;
                }
            }        
        });
        entities.add(new Button(start + (seperation * 2), 50, "buttons/electrical.png") {
            @Override
            public void clicked(MouseEvent m) {
                if (selected != null && selected.role != Type.MENTOR) {
                    selected.role = Type.ELECTRICAL;
                }
            }
            
            @Override
            public void tick() {
                super.tick();
                if (selected == null) {
                    visible = false;
                } else {
                    visible = true;
                }
            }
        });
        entities.add(new Button(start + (seperation * 3), 50, "buttons/pr.png") {
            @Override
            public void clicked(MouseEvent m) {
                if (selected != null && selected.role != Type.MENTOR) {
                    selected.role = Type.PR;
                }
            }
            
            @Override
            public void tick() {
                super.tick();
                if (selected == null) {
                    visible = false;
                } else {
                    visible = true;
                }
            }
        });
    }
    
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawString(day.toString() + ", Week " + week, 10, 20);
        for (Person p : Main.team.members) {
            p.draw(g);
            p.tick();
        }
        if (selected != null) {
            Rectangle r = selected.box();
            Color oldColor = g.getColor();
            g.setColor(Color.GREEN);
            g.drawRect(r.x, r.y, r.width, r.height);
            g.setColor(oldColor);
        }
    }

    @Override
    public void clicked(MouseEvent m) {
        Point point = Board.self.getLocationOnScreen();
        int mouseX = m.getXOnScreen() - point.x;
        int mouseY = m.getYOnScreen() - point.y;
        for (Person p : Main.team.members) {
            if (p.box().contains(mouseX, mouseY)) {
                System.out.println("Selected a dude");
                selected = p;
                return;
            }
        }
    }

    public enum Day {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;
    }
}