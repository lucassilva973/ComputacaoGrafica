import java.util.*;
import java.awt.*;
import javax.swing.*;

public class grade {
    public static void main(String[] args){    
        int x, y;
        
        
        //janela
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setVisible(true);

        frame.setSize((int)size.getHeight(), (int)size.getWidth());

        JPanel panel = new JPanel();
        frame.add(panel); 
    
        Scanner entrada = new Scanner(System.in); 
        System.out.println("Qual a distancia entre as linhas e colunas");
        int distancia = entrada.nextInt();
        
        System.out.println("Qual a distancia entre os pixels");
        int distancia_entre_pixels = entrada.nextInt();
        
        Graphics g = panel.getGraphics();
        for (y = 0 ;y <= (int)size.getHeight(); y+=distancia){
            for (x = 0; x <=( int)size.getWidth(); x+=distancia_entre_pixels){
                g.drawLine(x, (int)size.getHeight() - Math.round(y),x,(int)size.getHeight() - Math.round(y));
            }
        }
        for (y = 0 ;y <= (int)size.getHeight(); y+=distancia_entre_pixels){
            for (x = 0; x <=( int)size.getWidth(); x+=distancia){
                g.drawLine(x, (int)size.getHeight() - Math.round(y),x,(int)size.getHeight() - Math.round(y));
            }
        }
    }
}
