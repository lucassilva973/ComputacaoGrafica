package Elipses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Scanner;

import javax.swing.JFrame;

public class Elipse extends JFrame {

	int alt,larg, x, y;
	Graphics g;
	
    public  Elipse (int alt,int larg, int x ,int y){
                setSize(larg,alt); //Determina o tamanho da tela/
                setLocationRelativeTo(null);//centraliza a tela/
                setDefaultCloseOperation(EXIT_ON_CLOSE);//termina o programa ao ser fechado/
                setVisible(true);//permite visualização/
                this.x = x;
                this.y = y;
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

    public  void limpar_tela(Elipse e) {

            Graphics g = getGraphics();//pega a resolução definida pelo costrutor/
            g.clearRect(0, 0, e.getLarg(), e.getAlt());//*função resevada da bliblioteca 
                                                                            //que limpa a tela*/

             }


    public void Cria_elipse() {
            Graphics g = getGraphics();

            int alt,larg;
            alt=this.alt/2;
            larg=this.larg/2;

            int xc = 0;
            int yc = y;

            int p;
            double auxp;

            p=y * y - x*x*y +x*x/4;

            while (2*y*y*xc <= 2*x*x*yc) {
                    g.drawLine(larg+xc, alt+yc, larg+xc, alt+yc);
                    g.drawLine(larg-xc, alt+yc, larg-xc, alt+yc);
                    g.drawLine(larg+xc, alt-yc, larg+xc, alt-yc);
                    g.drawLine(larg-xc, alt-yc, larg-xc, alt-yc);

                     if (p < 0) {
                     xc++;
                     p = p + 2*y*y*xc + y*y;
                  }
                  else {
                     xc++;
                     yc--;
                     p = p + 2*y*y*xc - 2*x*x*yc - y*y;
                  }
            }

            auxp =  y*y*(xc+0.5)*(xc+0.5) + x*x*(yc-1)*(yc-1) - x*x*y*y;

             while (yc >= 0) {
                        g.drawLine(larg+xc, alt+yc, larg+xc, alt+yc);
                        g.drawLine(larg-xc, alt+yc, larg-xc, alt+yc);
                        g.drawLine(larg+xc, alt-yc, larg+xc, alt-yc);
                        g.drawLine(larg-xc, alt-yc, larg-xc, alt-yc);


             if (auxp > 0) {
             yc--;
             auxp = auxp - 2*x*x*yc + x*x;
          }
          else {
             xc++;
             yc--;
             auxp = auxp + 2*y*y*xc - 2*x*x*yc - x*x;
          }
             }
    }

    public void preencher(){

    for(int aux=0;aux<x || aux<y ;x--,y --) {
            Graphics g = getGraphics();

            int alt,larg;
            alt=this.alt/2;
            larg=this.larg/2;
            

            int xc = 0;
            int yc = y;

            int p;
            double auxp;

            p=y * y - x*x*y +x*x/4;

            while (2*y*y*xc <= 2*x*x*yc) {
                    g.drawLine(larg+xc, alt+yc, larg+xc, alt+yc);
                    g.drawLine(larg-xc, alt+yc, larg-xc, alt+yc);
                    g.drawLine(larg+xc, alt-yc, larg+xc, alt-yc);
                    g.drawLine(larg-xc, alt-yc, larg-xc, alt-yc);

                     if (p < 0) {
                     xc++;
                     p = p + 2*y*y*xc + y*y;
                  }
                  else {
                     xc++;
                     yc--;
                     p = p + 2*y*y*xc - 2*x*x*yc - y*y;
                  }
            }

            auxp =  y*y*(xc+0.5)*(xc+0.5) + x * x *(yc-1)*(yc-1) - x*x*y*y;

             while (yc >= 0) {
                            g.drawLine(larg+xc, alt+yc, larg+xc, alt+yc);
                            g.drawLine(larg-xc, alt+yc, larg-xc, alt+yc);
                            g.drawLine(larg+xc, alt-yc, larg+xc, alt-yc);
                            g.drawLine(larg-xc, alt-yc, larg-xc, alt-yc);


             if (auxp > 0) {
             yc--;
             auxp = auxp - 2*x*x*yc + x*x;
          }
          else {
             xc++;
             yc--;
             auxp = auxp + 2*y*y*xc - 2*x*x*yc - x*x;
          }
             }
            }
    }

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);	
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                int opcao,raiox,raioy;
		int larg = (int) size.getWidth();
		int alt =  (int) size.getHeight();
		
		Elipse elipse= new Elipse(alt,larg,100,200);
		
		elipse.setAlt(alt);
		elipse.setLarg(larg);
		
            while(true) {
			
                        
			System.out.printf("[0] Limpa tela \n"+ 
                                          "[1] Digite os valores a elipse \n"+
                                          "[2] Desenha elipse \n"+
                                          "[3] preenchimento \n"+
                                          "[4] sair \n");
			
			opcao=input.nextInt();
			
			if(opcao==0) {
				elipse.limpar_tela(elipse);
			}
			if(opcao==1) {
				System.out.println("digite os valores da elipse");
                                System.out.println("digite o valor do raio do eixo x");
                                elipse.x=input.nextInt();
                                System.out.println("digite o valor do raio do eixo y");
                                elipse.y=input.nextInt();
			}
                        if(opcao==2) {
				elipse.Cria_elipse();
			}
			if(opcao==3) {	
				elipse.preencher();
			}
			if(opcao==4) {
				System.exit(0);
			}
			}
	}

  

}
