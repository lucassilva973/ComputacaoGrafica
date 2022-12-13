import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Equacao_diferencial {
    public static void equacao_diferencial (JFrame frame,JPanel panel, int altura){
    
        int x, x1, x2, y1, y2;
        float y, m;
        int valor;
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("valor de X1");
        x1 = entrada.nextInt();
        
        System.out.println("valor de Y1");
        y1 = entrada.nextInt();
        
        System.out.println("valor de X2");
        x2= entrada.nextInt();
        
        System.out.println("valor de Y2");
        y2 = entrada.nextInt();
        m = (y2-y1) / (x2-x1);
        
        Graphics g = panel.getGraphics();
        for ( x = x1; x < x2; x++){
            y= y1 + m* (x-x1);
           
            g.drawLine(x, altura - Math.round(y),x, altura - Math.round(y));
        }
        
  
    }
    
}
