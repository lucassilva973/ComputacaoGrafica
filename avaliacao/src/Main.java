import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Main {
  public static void main(String[] args){
      
      Scanner entrada = new Scanner(System.in);
      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    
      JFrame frame = new JFrame();
      frame.setVisible(true);
      
      frame.setSize((int)size.getHeight(), (int)size.getWidth());
      
      JPanel panel = new JPanel();
      frame.add(panel);
      
      while(true){
          System.out.println("================================================================\n"+ 
                             "[0] LIMPAR TELA\n" +
                             "[1] ALGORITMO RETA: EQUACAO GERAL\n" +
                             "[2] ALGORITMO RETA: EQUACAO DIFERENCIAL\n" +
                             "[3] ALGORITMO RETA: BRESENHAN\n" +
                             "[4] SAIR\n"+
                         "================================================================\n");
          
          
          int valor_menu = entrada.nextInt();
          switch (valor_menu){

            case 0:
                    frame.getContentPane().setBackground(Color.white);
                 
                break;
            case 1:
                   Equacao_geral a = new Equacao_geral();
                   a.equacao_geral(frame,panel,(int)size.getHeight());
                break;
            case 2:
                   Equacao_diferencial b = new Equacao_diferencial();
                   b.equacao_diferencial(frame,panel,(int)size.getHeight());
                break;
            case 3:
                   Bresenham c = new Bresenham();
                   c.bresenham(frame,panel,(int)size.getHeight());
                break;
            case 4:
                System.exit(0);
            
            default:
                System.out.println("Opção inválida. Escolha novamente.");  
          
        }
    }
}

}
