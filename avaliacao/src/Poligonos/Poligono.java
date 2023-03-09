package Poligonos;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Poligono {
    
    
    public static int NL = 8;
    public static int LimSuper,LimInfe;
    
    //void DrawPoli();
    //void PreencherPoli();
    //int CalcInterseccao(int atual, int varredura);
    //float InvCoAngular(float x1, float y1, float x2, float y2);
    //void DDAPoli(float x1, float y1, float x2, float y2);
    
    public static void DrawPoli(JFrame frame,JPanel panel,Poli[] Lado){
        int i;
        Graphics g = panel.getGraphics();
        for (i = 1; i <= NL; i++)
            g.drawLine(Lado[i].getxIni() , frame.getHeight() - Lado[i].getyIni(), Lado[i].getxFim(), frame.getHeight() - Lado[i].getyFim());

    }
       
    public static void PreencherPoli(JFrame frame, JPanel panel, Poli[] Lado) {
    Graphics g = panel.getGraphics();
    int linha;
    
    LimInfe = LimiteInferior(Lado);
    LimSuper = LimiteSuperior(Lado);
    // linha de varredura
    int cont, j, i, l;
    // este vetor contém o inverso do coeficiente angular de cada aresta
    for (i = 0; i < Lado.length; i++)
        Lado[i].setInvM((int) InvCoAngular(Lado[i].getxIni(), Lado[i].getyIni(), Lado[i].getxFim(), Lado[i].getyFim()));
    for (linha = LimInfe; linha <= LimSuper; linha++) {
        cont = 0;
        for (j = 0; j < Lado.length; j++) {
            if (((Lado[j].getyMin() < linha) && (Lado[j].getyMax() > linha)) || (Lado[j].getyMax() == linha)) {
                Lado[cont].setArestas(j);
                cont++;
            }
        }
        for (i = 0; i < cont; i++){
            Lado[i].setInterseccao(CalcInterseccao(Lado[i].getArestas(), linha, Lado));
        }
        for (l = 0; l < cont; l++) {
            if ((l % 2) != 0) {
                g.setColor(Color.RED);
                g.drawLine(Lado[l].getInterseccao(), frame.getHeight() - linha, Lado[l + 1].getInterseccao() + 1, frame.getHeight() - linha);
            }
        }
    }
}

       
   
    
   public static double InvCoAngular(int x1, int y1, int x2, int y2) {
    double invM;
    if (y2 - y1 == 0) {
        invM = Double.POSITIVE_INFINITY;
    } else {
        invM = (double) (x2 - x1) / (y2 - y1);
    }
    return invM;
}
    
    public static int CalcInterseccao(int aresta, int linha, Poli[] Lado) {
    int y = Lado[aresta].getyIni();
    int x = Lado[aresta].getxIni();
    int invM = Lado[aresta].getInvM();

    return x + ((linha - y) / invM);
}
    
    
    public static int LimiteInferior(Poli[] Lado) {
     int limInf = Lado[0].getyIni(); // começa com o Y do primeiro ponto
     for (int i = 1; i < Lado.length; i++) {
         if (Lado[i].getyIni() < limInf) {
             limInf = Lado[i].getyIni(); // atualiza o limite inferior
         }
         if (Lado[i].getyFim() < limInf) {
             limInf = Lado[i].getyFim();
         }
     }
     return limInf;
 }

    public static int LimiteSuperior(Poli[] Lado) {
        int limSup = Lado[0].getyIni(); // começa com o Y do primeiro ponto
        for (int i = 1; i < Lado.length; i++) {
            if (Lado[i].getyIni() > limSup) {
                limSup = Lado[i].getyIni(); // atualiza o limite superior
            }
            if (Lado[i].getyFim() > limSup) {
                limSup = Lado[i].getyFim();
            }
        }
        return limSup;
    }
    
    public static void main(String[] args){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setVisible(true);

        frame.setSize((int)size.getHeight(), (int)size.getWidth());

        JPanel panel = new JPanel();
        frame.add(panel);
        
        
        // Carregamento de instância de polígono (pontos extremos dos lados) - em lote
        Poli[] Lado = new Poli[7];
        Lado[0] = new Poli(200, 100, 300, 100, 100, 100);
        Lado[1] = new Poli(300, 100, 325, 150, 150, 100);
        Lado[2] = new Poli(325, 150, 350, 100, 150, 100);
        Lado[3] = new Poli(350, 100, 450, 100, 100, 100);
        Lado[4] = new Poli(450, 100, 375, 175, 100, 100);
        Lado[5] = new Poli(375, 175, 400, 225, 100, 100);
        
        System.out.println("================================================================\n"+ 
                             "[0]  Desenhe o poligono\n" +
                             "[1]  Peencha o poligono\n" +
                             
                         "================================================================\n");
        
        
        
        Scanner entrada = new Scanner(System.in);
        while (true){
            int valor_menu = entrada.nextInt();
            switch (valor_menu){

                case 0:
                    DrawPoli(frame,panel,Lado);

                    break;
                case 1:
                    PreencherPoli(frame,panel,Lado); 
                    break;
        
        
    
            }
        }    
    }
}