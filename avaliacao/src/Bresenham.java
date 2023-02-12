import java.util.*;
import java.awt.*;
import javax.swing.*;


public class Bresenham {
    public static void bresenham(JFrame frame,JPanel panel, int altura){
    
        int dx, dy, x1, x2, y1, y2,p,c1,c2, x,y;
        
        Scanner entrada = new Scanner(System.in);
        
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
        
        if (x == x2){
            for ( y = y1; y < y2;y++){
                g.drawLine(x, altura - y,x, altura - y);
            }
        }
        while (x < x2){
            if(p<0){
                p += c1;
            }else{
                p += c2;
                y++;
                
            }
            x++;
            g.drawLine(x, altura - y,x, altura - y);
        }
       
    
    
    }
        
        
}

