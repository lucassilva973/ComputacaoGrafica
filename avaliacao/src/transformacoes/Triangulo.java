package transformacoes;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Triangulo {
    public static void triangulo(JFrame frame,JPanel panel, int pt1[],int pt2[],int pt3[]){
        int altura_meio = (int)frame.getHeight()/2;
        int largura_meio = (int)frame.getWidth()/2;
        
        Graphics g = panel.getGraphics();
        
       
        
        g.drawLine(pt1[0] + largura_meio, altura_meio - pt1[1], pt2[0] + largura_meio, altura_meio - pt2[1]);
        g.drawLine(pt1[0] + largura_meio, altura_meio - pt1[1], pt3[0] + largura_meio, altura_meio - pt3[1]);
        g.drawLine(pt3[0] + largura_meio, altura_meio - pt3[1], pt2[0] + largura_meio, altura_meio - pt2[1]);
    }
}
