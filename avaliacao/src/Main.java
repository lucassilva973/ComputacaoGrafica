import Elipses.Elipse;
import Poligonos.Poli;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Main {
  public static void main(String[] args){
      
      Scanner entrada = new Scanner(System.in);
      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    
      JFrame frame = new JFrame();
      frame.setVisible(true);
      
      frame.setSize((int)size.getWidth(), (int)size.getHeight());
      
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
                             "[7]  Poligono Concavo\n"+
                             "[8]  Elipse\n"+
                             "[9] ...\n"+
                             "[10] ...\n"+
                             "[11] SAIR\n"+
                             
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
                
                System.out.print("Digite o raio: ");
                int raio = entrada.nextInt();
                d.circulo_parametrica(frame,panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                
                System.out.print("Deseja preencher o circulo? [1]sim / [2]nao\n");
                int escolha = entrada.nextInt();
                if(escolha == 1){
                   
                    d.PreencherCirculo_parametrica(frame, panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                }
                break;
            case 5:
                
                System.out.print("Digite o raio: ");
                raio = entrada.nextInt();
                d.circulo_parametrico_otimo(frame,panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                
                System.out.print("Deseja preencher o circulo? [1]sim / [2]nao\n");
                int escolha1 = entrada.nextInt();
                if(escolha1 == 1){
                  
                    d.PreencherCirculo_parametrica_otimo(frame, panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                }
                break;
            case 6:
                
                System.out.print("Digite o raio: ");
                raio = entrada.nextInt();
                d.circulo_ponto_medio(frame, panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                
                System.out.print("Deseja preencher o circulo? [1]sim / [2]nao\n");
                int escolha2 = entrada.nextInt();
                if(escolha2 == 1){
                    
                    d.PreencherCirculo_medio(frame, panel,(int)size.getHeight(),(int)size.getWidth(),raio);
                }
                break;
            case 7:
                int larg = (int) size.getWidth();
                int alt =  (int) size.getHeight();
                
                // cria poligono, ele é carregado na propria classe
                Poli p= new Poli(frame);

                p.setAlt(alt);
                p.setLarg(larg);
                
                p.desenhar();
                System.out.print("Deseja preencher o poligono? [1]sim / [2]nao\n");
                escolha2 = entrada.nextInt();
                if(escolha2 == 1){	
                    p.pintar();
                }
                break;
            case 8:
		//cria a elipse
                Elipse elipse= new Elipse(frame,100,200);
		
		elipse.setAlt((int) size.getHeight());
		elipse.setLarg((int) size.getWidth());
                
                System.out.printf("Para digitar os valores da elipse digite 1\n");
                
                int opcao=entrada.nextInt();
                
                // Escolhe opção para definir os valores de x e y ou Desenha a elipse carregada
                if(opcao==1) {
                    System.out.println("digite os valores da elipse");
                    System.out.println("digite o valor do raio do eixo x");
                    elipse.x = entrada.nextInt();
                    System.out.println("digite o valor do raio do eixo y");
                    elipse.y = entrada.nextInt();
			}
                
                elipse.Cria_elipse();//não vai dar erro pois a elipse foi carregada na inicialização da elipse
                
                System.out.print("Deseja preencher o poligono? [1]sim / [2]nao\n");
                escolha2 = entrada.nextInt();
                if(escolha2 == 1){	
                    elipse.preencher();;
                }
               
                break;
            case 9:
                
                break;
            case 10:
                
                break;
            case 11:
                System.exit(0);
                break; 
            
            default:
                System.out.println("Opção inválida. Escolha novamente.");  
          
        }
    }
}

}
