import java.awt.*;
import java.util.*;
import javax.swing.*;



public class Bresenham_tracejado {
   public static void main(String[] args){
      
      Scanner entrada = new Scanner(System.in);
      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    
      JFrame frame = new JFrame();
      frame.setVisible(true);
      
      frame.setSize((int)size.getHeight(), (int)size.getWidth());
      
      JPanel panel = new JPanel();
      frame.add(panel); 
      
      int dx, dy, x1, x2, y1, y2,p,c1,c2, x,y;
             
        
        System.out.println("valor de X1");
        x1 = entrada.nextInt();
        
        System.out.println("valor de Y1");
        y1 = entrada.nextInt();
        
        System.out.println("valor de X2");
        x2= entrada.nextInt();
        
        System.out.println("valor de Y2");
        y2 = entrada.nextInt();
        
        Graphics g = panel.getGraphics();
        
        dx = x2-x1;
        dy = y2-y1;
        p = 2*dy-dx;
        c1 = 2*dy;
        c2 = 2*(dy-dx);
        x = x1;
        y = y1;
        
        while (x <x2){
            if(p<0){
                p += c1;
            }else{
                p += c2;
                y += 2;
            }
            x += 2;
            g.drawLine(x,(int)size.getHeight()- y,x, (int)size.getHeight()-y);
        }
    }
   
}
   
   
   

