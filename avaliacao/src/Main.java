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
                             "[0]  LIMPAR TELA\n" +
                             "[1]  ALGORITMO RETA: EQUACAO GERAL\n" +
                             "[2]  ALGORITMO RETA: EQUACAO DIFERENCIAL\n" +
                             "[3]  ALGORITMO RETA: BRESENHAN\n" +
                             "[4]  Circulo Parametrico\n"+
                             "[5]  Circulo Parametrico Otimo\n"+
                             "[6]  Circulo Ponto Medio\n"+
                             "[7]  ...\n"+
                             "[9]  ...\n"+
                             "[10] ...\n"+
                             "[11] ...\n"+
                             "[12] ...\n"+
                             "[13] SAIR\n"+
                         "================================================================\n");
          
          
          Circulo d = new Circulo();
          
          int valor_menu = entrada.nextInt();
          switch (valor_menu){

            case 0:
                frame.getContentPane().setBackground(Color.WHITE);
                frame.getContentPane().setBackground(Color.BLACK);
                frame.getContentPane().setBackground(Color.WHITE);
                
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
                d.circulo_parametrica(frame,panel,(int)size.getHeight(),(int)size.getWidth());
                
                break;
            case 5:
                d.circulo_parametrico_otimo(frame,panel,(int)size.getHeight(),(int)size.getWidth());
                
                break;
            case 6:
                d.circulo_ponto_medio(frame, panel,(int)size.getHeight(),(int)size.getWidth());
                
                break;
            case 7:
                
                break;
            case 8:
                
                break;
            case 9:
                
                break;
            case 10:
                
                break;
            case 11:
                
                break;
            case 12:
                
                break;
            case 13:
                
                System.exit(0);
            
            default:
                System.out.println("Opção inválida. Escolha novamente.");  
          
        }
    }
}

}
