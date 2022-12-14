import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Equacao_diferencial {
    public static void equacao_diferencial (JFrame frame,JPanel panel, int altura){
    
        int x1, x2, y1, y2;
        float m;
        
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("valor de X1");
        x1 = entrada.nextInt();
        
        System.out.println("valor de Y1");
        y1 = entrada.nextInt();
        
        System.out.println("valor de X2");
        x2= entrada.nextInt();
        
        System.out.println("valor de Y2");
        y2 = entrada.nextInt();
        
        double dx = (double) Math.abs(x2-x1);
        double dy = (double) Math.abs(y2-x1);
        double xf, s;
        float x = x1;
        float y = y1;
        
        if ( dx > dy){
            s = dx;
        }else{
            s = dy;
        }
        double xi = dx/s;
        double yi = dy/s;
        for (int i = 0; i <= s; i++){
            Graphics g = panel.getGraphics();  
            g.drawLine(Math.round(x), altura - Math.round(y),Math.round(x), altura - Math.round(y));
            x +=xi;
            y +=yi;
        }
  
    }
    
}