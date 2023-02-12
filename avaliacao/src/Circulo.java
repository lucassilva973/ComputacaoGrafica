import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math. *;



public class Circulo {
    
    // SIMETRIZ ORDEM 8 //////////////////////////////////////////////////////////////////////////
    public static void SimetriaOrdem8(JFrame frame, JPanel panel, int x,int y,int xc, int yc){
     Graphics g = panel.getGraphics();
     
     g.drawLine(x + xc, y + yc, x + xc, y + yc);
     g.drawLine(y + xc, x + yc, y + xc, x + yc);
     g.drawLine(-y + xc, x + yc, -y + xc, x + yc);
     g.drawLine(-x + xc, y + yc, -x + xc, y + yc);
     g.drawLine(-x + xc, -y + yc, -x + xc, -y + yc);
     g.drawLine(-y + xc, -x + yc, -y + xc, -x + yc);
     g.drawLine(y + xc, -x + yc, y + xc, -x + yc);
     g.drawLine(x + xc, -y + yc, x + xc, -y + yc);
    }
    
    // CIRCULO PARAMETRICA //////////////////////////////////////////////////////////////////////////
    public static void circulo_parametrica(JFrame frame,JPanel panel, int altura, int largura){
        int y, x, xc, yc;
        
        xc = largura/ 2;
        yc = altura/ 2;
        double t;
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite o raio");
        int r = entrada.nextInt();
        x = r;
        y = 0;
        
        Graphics g = panel.getGraphics();   
        for (t = 1.0; t < 360.0; t = t + 1.0){
            g.drawLine(x + xc, y + yc, x+ xc , y + yc);
            x = (int) (r * Math.cos(Math.PI * t / 180));
            y = (int) (r * Math.sin(Math.PI * t / 180));
            
            }
        }
        
        // CIRCULO PARAMETRICA OTIMO ////////////////////////////////////////////////////////////////////
        public static void circulo_parametrico_otimo(JFrame frame,JPanel panel, int altura, int largura){
            int xc, yc, xr, yr;
            double x, y, t;
            
            Scanner entrada = new Scanner(System.in);
            System.out.print("Digite o raio: ");
            int r = entrada.nextInt();
            
            xc = largura/2; // x do centro da circunferência
            yc = altura/2; // y do centro da circunferência
            t = 1/(double) r;
            x = (double)r;
            y = 0;
            double c = Math.cos(t);
            double s = Math.sin(t);
            while (y <=x){ 
                xr = (int)Math.round(x);
                yr = (int)Math.round(y);
                SimetriaOrdem8(frame, panel, xr, yr, xc, yc);
                x = x * c - y * s; //calcula próximo x
                y = y * c + x * s; //calcula o próximo y
            }
        }
        
        // CIRCULO PONTO MEDIO ////////////////////////////////////////////////////////////////////
        void circulo_ponto_medio(JFrame frame,JPanel panel, int altura, int largura) {
                int xc, yc;
                xc = largura / 2;
                yc = altura / 2;
                
                Scanner entrada = new Scanner(System.in);
                System.out.print("Digite o raio");
                int raio = entrada.nextInt();
                
                int x = 0;
                int y = raio;
                double d = 5 / 4 - raio;
                SimetriaOrdem8(frame, panel, x, y, xc, yc);
                while (y > x) {
                    if (d < 0) /* escolhe E */
                    d = d + 2.0 * x + 3.0;
                    else { /* escolhe SE */
                    d = d + 2.0 * (x - y) + 5;
                    y--;
                    }
                x++;
                SimetriaOrdem8(frame, panel, x, y, xc, yc);
                } /* while*/
            } /*pontomedio*/
}

    