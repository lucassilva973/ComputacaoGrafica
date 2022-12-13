import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Equacao_geral {
   
   public static void equacao_geral(JFrame frame,JPanel panel, int altura){
        int x, xi, xf, yi, yf;
        float y, m;
        int color;

        Scanner entrada = new Scanner(System.in); 
        //correção dos pontos y
             
        System.out.println("valor de X1(inicial)");
        xi = entrada.nextInt();
        
        System.out.println("valor de Y1(inicial)");
        yi = entrada.nextInt();
        
        System.out.println("valor de X2(final)");
        xf = entrada.nextInt();
        
        System.out.println("valor de Y2(final)");
        yf = entrada.nextInt();
         
        Graphics g = panel.getGraphics();
        m = (yf-yi) /(xf - xi);
        for (x = xi ; x <= xf; x++){
            y = yi + m * (x - xi);

            g.drawLine(x, altura - Math.round(y),x,altura - Math.round(y));
            
        }
        
        
   }
}
