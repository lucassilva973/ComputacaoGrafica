/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poligonos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;


public class Poligono extends JFrame  {

	int alt,larg;
	Graphics g;
	/*essas cordenadas tem que ser unica para cada aluno*/
	private int[] Pontos_x = {140, 120, 80, 120, 140, 160, 200, 160};/*cordenadas x dos vertices*/
	private int[] Pontos_y = {200, 160, 140, 120, 80, 120 ,140, 160};/*cordenadas y dos vertices*/
	private int Pontos_n = 8;/*numero de vertice*/
	
    public	Poligono (int alt,int larg){
                    setSize(larg,alt);/*Determina o tamanho da tela*/
                    setLocationRelativeTo(null);/*centraliza a tela*/
                    setDefaultCloseOperation(EXIT_ON_CLOSE);/*termina o programa ao ser fechado*/
                    setVisible(true);/*permite visualização*/
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

    public  void limpar_tela(Poligono p) {

            Graphics g = getGraphics();/*pega a resolução definida pelo costrutor*/
            g.clearRect(0, 0, p.getLarg(), p.getAlt());/*função resevada da bliblioteca 
                                                                            que limpa a tela*/

             }

    public void desenhar(){
            Graphics g = getGraphics();

             Polygon poly = new Polygon(Pontos_x, Pontos_y, Pontos_n);

             for (int i = 0; i < Pontos_n; i++) {
             int x2 = Pontos_x[i];
             int y2 = Pontos_y[i];
             int x3 = Pontos_x[(i + 1) % Pontos_n];/* e necessario fazer a divisão para garantir que pecorremos todos os pontos*/
             int y3 = Pontos_y[(i + 1) % Pontos_n];
             g.drawLine(x2, y2, x3, y3);
             }
             }

    public void pintar () {
               Graphics g = getGraphics();
                /*import java.awt.Polygon;*/
               Polygon poly = new Polygon(Pontos_x, Pontos_y, Pontos_n);/*a criação desse objeto e necessaria para defirnimos o y min e o y max*/

               int[] scan = new int[getSize().width];/*cria uma lista com toda o tamanho da altura da tela*/

               int minY = poly.getBounds().y;/*pega o y min do poligono*/
               int maxY = minY + poly.getBounds().height;/*pega 0 y max do poligono*/

               for (int y = minY; y < maxY; y++) {/*responsavel por pecorrer o y min ate o y max*/
                     Arrays.fill(scan, 0);/*responsavel por pecorrer a tela*/
                     int x1 = Integer.MAX_VALUE;/*cria um interio de valor maximo possivel. Importante para definir o valor de x*/

                     for (int i = 0; i < Pontos_n; i++) {
                        int x2 = Pontos_x[i];
                        int y2 = Pontos_y[i];
                        int x3 = Pontos_x[(i + 1) % Pontos_n];/* e necessario fazer a divisão para garantir que pecorremos todos os pontos*/
                        int y3 = Pontos_y[(i + 1) % Pontos_n];

                        if ((y2 < y && y3 >= y) || (y3 < y && y2 >= y)) {/*verifica o valor de ymin e ymax em relação aos nossos vertices*/
                           int x = x2 + (y - y2) * (x3 - x2) / (y3 - y2);/*calcula o valor de x*/
                           scan[x]++;/*registra a tela*/

                           if (x < x1) {
                              x1 = x;}/*verifica o menor ponto de x*/
                        }
                     }

                     boolean fora = false;/*booleano responsavel por verificar se a varredura esta fora ou dentro do poligono*/

                        for (int x = x1; x < getSize().width; x++) {
                            if (scan[x] % 2 == 1) {/*verifica se esta fora da tela*/
                                fora = !fora;
                            }

                            if (fora) {
                                g.drawLine(x, y, x, y);
                            }
                        }

                     }
    }




    public static void main(String[] args) {

            Scanner input = new Scanner(System.in);	
            int opcao;


           Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            
            int larg = (int) size.getWidth();
            int alt =  (int) size.getHeight();

            Poligono p= new Poligono(alt,larg);

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
                p.pintar();
            }
            if(opcao==3) {
               System.exit(0);
            }

        }
    }
}

