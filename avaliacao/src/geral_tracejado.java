import java.util.*;
import java.awt.*;
import javax.swing.*;

public class geral_tracejado {
    //public static void geral_treacejado(){
    public static void main(String[] args){    
        int x, y, xi, xf, yi, yf;
        
        
        //janela
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setVisible(true);

        frame.setSize((int)size.getHeight(), (int)size.getWidth());

        JPanel panel = new JPanel();
        frame.add(panel); 
        
        //entrada de dados
        Scanner entrada = new Scanner(System.in); 
        System.out.println("Utilize 1 para horizontal\n" + "Utilize 2 para vertical\n" + "Para sair utilize 0\n");
        System.out.println("Opcao: ");
        int letra;
           
        Graphics g = panel.getGraphics();
        while(true){
            letra = entrada.nextInt();
            switch(letra){

                case 1: 
                    System.out.println("Qual a altura da linha(y)?");
                    y = entrada.nextInt();

                    System.out.println("valor de X1");
                    xi = entrada.nextInt();

                    System.out.println("valor de X2");
                    xf = entrada.nextInt();

                    for (x = xi ; x <= xf; x++){


                    g.drawLine(x, (int)size.getHeight() - Math.round(y),x,(int)size.getHeight() - Math.round(y));
                    x +=2;
            }
                    break;
                case 2:

                    System.out.println("Qual a distancia da borda(x)?");
                    x = entrada.nextInt();

                    System.out.println("valor de y1");
                    yi = entrada.nextInt();

                    System.out.println("valor de y2");
                    yf = entrada.nextInt();

                    for (y = yi ; y <= yf; y++){


                    g.drawLine(x, (int)size.getHeight() - Math.round(y),x,(int)size.getHeight() - Math.round(y));
                    y +=2;
                    }
                    break;

                case 0:
                    System.exit(0);
             default:
                    System.out.println("Opção inválida. Escolha novamente.");
        
            }
            System.out.println("Utilize 1 para horizontal\n" + "Utilize 2 para vertical\n" + "Para sair utilize 0\n");
        }  
   }
}
