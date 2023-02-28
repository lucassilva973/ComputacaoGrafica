package transformacoes;

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
                             "[0] Desenha triangulo\n" +
                             "[1] Translacao\n" +
                             "[2] Escala\n" +
                             "[3] Rotacao\n" +
                             "[4] Escala com ponto fixo arbitrario pArb(Arbx, Abry)\n"+
                             "[5] Rotacao com ponto fixo arbitrario pArb(Arbx, Abry)\n"+
                             "================================================================\n");
          
          
          
          
          Transformacoes a = new Transformacoes();
          Triangulo novo = new Triangulo();
          int[] pt1 = {40,50,1};
          int[] pt2 = {70,50,1};
          int[] pt3 = {55,90,1}; 
          int[] ptArb = {55,65,1};        
         
          //novos pontos
          int[] pt1_t = new int[3];
          int[] pt2_t = new int[3];
          int[] pt3_t = new int[3];
          //
          Graphics g = panel.getGraphics();
          int valor_menu = entrada.nextInt();
          switch (valor_menu){
              
            case 0:
                //desenha eixo x e y
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                novo.triangulo(frame,panel,pt1,pt2,pt3);  
                break;
            case 1:
                
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                pt1_t = a.translacao(-10,15,pt1);
                pt2_t = a.translacao(-10,15,pt2);
                pt3_t = a.translacao(-10,15,pt3);
                
                novo.triangulo(frame,panel,pt1_t,pt2_t,pt3_t);
                break;
            case 2:
                
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                pt1_t = a.escala(2,2,pt1);
                pt2_t = a.escala(2,2,pt2);
                pt3_t = a.escala(2,2,pt3);
                
                novo.triangulo(frame,panel,pt1_t,pt2_t,pt3_t);
                break;
            case 3:
                
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                pt1_t = a.rotacao(30,pt1);
                pt2_t = a.rotacao(30,pt2);
                pt3_t = a.rotacao(30,pt3); 
                
                novo.triangulo(frame,panel,pt1_t,pt2_t,pt3_t);
                break;
            case 4:
                
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                pt1_t = a.escalaArb(2,2,pt1,ptArb);
                pt2_t = a.escalaArb(2,2,pt2,ptArb);
                pt3_t = a.escalaArb(2,2,pt3,ptArb);
                
                novo.triangulo(frame,panel,pt1_t,pt2_t,pt3_t);
                break;
            case 5:
                
                g.setColor(Color.RED);
                g.drawLine(frame.getWidth()/2,0,frame.getWidth()/2,frame.getHeight());
                g.drawLine(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
                
                pt1_t = a.rotacaoArb(30,pt1,ptArb);
                pt2_t = a.rotacaoArb(30,pt2,ptArb);
                pt3_t = a.rotacaoArb(30,pt3,ptArb); 
                
                novo.triangulo(frame,panel,pt1_t,pt2_t,pt3_t);
                break;
            default:
                System.out.println("Opção inválida. Escolha novamente.");
          
        }
    }
}

}
