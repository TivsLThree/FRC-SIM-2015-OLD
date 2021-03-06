package frc.sim;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Entity {
    
    public int x = 0;
    public int y = 0;
    public Image image = null;
    public boolean mouseIn = false;
    public boolean mouseWasIn = false;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Entity(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        image = Util.toImage(getClass(), imagePath);
    }
    
    public Rectangle box() {
        if (image != null) {
            return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
        }
        return new Rectangle(x, y, 0, 0);
    }
    
    public void tick() {
        Point p = Board.self.getLocationOnScreen();
        Point p2 = MouseInfo.getPointerInfo().getLocation();
        
        int mouseX = p2.x - p.x;
        int mouseY = p2.y - p.y;
         
        if (box().contains(mouseX, mouseY)) {
            if (!mouseWasIn) {
                mouseEnter();
                mouseWasIn = true;
            }
        } else if (mouseWasIn) {
            mouseLeave();
            mouseWasIn = false;
        }
    }
    
    public void draw(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        }
    }    
          
    public void mouseEnter() {
        mouseIn = true;
    }
    
    public void mouseLeave() {
        mouseIn = false;
    }
    
    public void clicked(MouseEvent m) {}
}
