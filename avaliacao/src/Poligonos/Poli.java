package Poligonos;


public class Poli {
    private int xIni;
    private int yIni;
    private int xFim;
    private int yFim;
    private int yMax;
    private int yMin;
    private int invM;
    private int arestas;
    private int interseccao;

    public Poli(int xIni, int yIni, int xFim, int yFim, int yMax, int yMin) {
        this.xIni = xIni;
        this.yIni = yIni;
        this.xFim = xFim;
        this.yFim = yFim;
        this.yMax = yMax;
        this.yMin = yMin;
    }

    public int getxIni() {
        return xIni;
    }

    public void setxIni(int xIni) {
        this.xIni = xIni;
    }

    public int getyIni() {
        return yIni;
    }

    public void setyIni(int yIni) {
        this.yIni = yIni;
    }

    public int getxFim() {
        return xFim;
    }

    public void setxFim(int xFim) {
        this.xFim = xFim;
    }

    public int getyFim() {
        return yFim;
    }

    public void setyFim(int yFim) {
        this.yFim = yFim;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getInvM() {
        return invM;
    }

    public void setInvM(int invM) {
        this.invM = invM;
    }

    public int getArestas() {
        return arestas;
    }

    public void setArestas(int arestas) {
        this.arestas = arestas;
    }

    public int getInterseccao() {
        return interseccao;
    }

    public void setInterseccao(int interseccao) {
        this.interseccao = interseccao;
    }
}
