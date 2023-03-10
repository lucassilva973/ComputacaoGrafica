
package Poligonos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;



public class Poli extends JFrame  {

	int alt,larg;
	Graphics g;
	
	private int[] Pontos_x = {200,180,175,165,120,80,10,55,190,325,370,300,260,215,205};
	private int[] Pontos_y = {140,140,160,130,130,180,180,90,50,90,180,180,130,130,160};
	private int Pontos_n = 15;
	
    public Poli (int alt,int larg){
                setSize(larg,alt);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
            }

    public int getAlt() {
            return alt;
    }


    public void setAlt(int alt) {
            this.alt = alt;
    }


    public int getLarg() {
            return larg;
    }


    public void setLarg(int larg) {
            this.larg = larg;
    }

    public  void limpar_tela(Poli p) {

            Graphics g = getGraphics();
            g.clearRect(0, 0, p.getLarg(), p.getAlt());

             }

    public void desenhar(){
            Graphics g = getGraphics();

             Polygon poly = new Polygon(Pontos_x, Pontos_y, Pontos_n);

             for (int i = 0; i < Pontos_n; i++) {
             int x2 = Pontos_x[i];
             int y2 = Pontos_y[i];
             int x3 = Pontos_x[(i + 1) % Pontos_n];
             int y3 = Pontos_y[(i + 1) % Pontos_n];
             g.drawLine(larg/2 + x2, alt/2 - y2, larg/2 + x3, alt/2 - y3);
             }
             }

    public void pintar () {
               Graphics g = getGraphics();
                
               Polygon poly = new Polygon(Pontos_x, Pontos_y, Pontos_n);

               int[] scan = new int[getSize().width];
               
               //pega o minimo e o maximo de y
               int minY = poly.getBounds().y;
               int maxY = minY + poly.getBounds().height;

               for (int y = minY; y < maxY; y++) {
                     Arrays.fill(scan, 0);//percorre a tela
                     int x1 = Integer.MAX_VALUE;

                     for (int i = 0; i < Pontos_n; i++) {
                        int x2 = Pontos_x[i];
                        int y2 = Pontos_y[i];
                        int x3 = Pontos_x[(i + 1) % Pontos_n];
                        int y3 = Pontos_y[(i + 1) % Pontos_n];

                        if ((y2 < y && y3 >= y) || (y3 < y && y2 >= y)) {
                           int x = x2 + (y - y2) * (x3 - x2) / (y3 - y2);
                           scan[x]++;
                           //menor ponto de x
                           if (x < x1) {
                              x1 = x;}
                        }
                     }

                     boolean fora = false;

                        for (int x = x1; x < getSize().width; x++) {
                            if (scan[x] % 2 == 1) {
                                fora = !fora;
                            }

                            if (fora) {
                                g.setColor(Color.BLACK);
                                g.drawLine(larg/2 + x, alt/2 -y, larg/2 + x, alt/2 -y);
                            }
                        }

                     }
    }

    
//    public void Circulo() {
//        Graphics g = getGraphics();
//
//
//        g.setColor(Color.BLACK);
//        g.drawOval(55, 40, 145, 125);
//
//        
//        g.setColor(Color.BLACK);
//        g.fillOval(55, 40, 145, 125);
//    }


    public static void main(String[] args) {

            Scanner input = new Scanner(System.in);	
            int opcao;

            
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            
            int larg = (int) size.getWidth();
            int alt =  (int) size.getHeight();

            Poli p= new Poli(alt,larg);

            p.setAlt(alt);
            p.setLarg(larg);


        while(true) {

            System.out.printf("[0] Limpa tela \n"+ 
                              "[1] Desenha \n"+
                              "[2] preenchimento \n"+
                              "[3] sair \n");

            opcao=input.nextInt();

            if(opcao==0) {
                p.limpar_tela(p);
            }
            if(opcao==1) {
                System.out.println("Poligono desenhado");
                p.desenhar();

            }
            if(opcao==2) {
                System.out.println("Poligono desenhado");
               
                //p.Circulo();
                p.pintar();
            }
            if(opcao==3) {
               System.exit(0);
            }

        }
    }
}
