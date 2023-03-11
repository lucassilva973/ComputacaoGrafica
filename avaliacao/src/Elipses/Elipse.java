package Elipses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Scanner;

import javax.swing.JFrame;

public class Elipse {

	public int alt,larg, x, y;
        JFrame frame;
	Graphics g;
	
    public  Elipse (JFrame frame,int x ,int y){

                this.frame = frame;
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

    public void Cria_elipse() {
            Graphics g = frame.getGraphics();

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
            Graphics g = frame.getGraphics();

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

}
