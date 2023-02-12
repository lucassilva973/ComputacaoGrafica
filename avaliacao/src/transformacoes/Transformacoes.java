package transformacoes;


public class Transformacoes {
    //          a    b    c
    // [ a ]  [ Ex ,  0 , 0]
    // [ b ]  [ 0  , Ey , 0]
    // [ c ]  [ 0  ,  0 , 1]
    
    
    //Escala//////////////////////////////////////////////////
    public static int[] escala(int Ex,int Ey, int[] pontos){
       
        // [a]
        // [b]
        // [c]
        int[][] matriz_escala = {{Ex,0,0},
                                 {0,Ey,0},
                                 {0,0,1}}; 
        int[] novos_pontos = new int[3];
        for(int a=0;a<3;a++){
            int soma=0;
            int n_p=0;
            for(int b=0;b<3;b++){             
         
                n_p = pontos[a] * matriz_escala[a][b];
                soma+=n_p;
                
                
            }
            novos_pontos[a] = soma;
        }
        System.out.println("x:" + novos_pontos[0]+ " y:" + novos_pontos[1] +" z:" + novos_pontos[2]);
        return novos_pontos;
    }
    
    //Translação//////////////////////////////////////////////////
    public static int[] translacao(int Tx,int Ty, int[] pontos){
        int[][] matriz_translacao = {{1,0,Tx},
                                     {0,1,Ty},
                                     {0,0,1}};
        
        int[] novos_pontos = new int[3];
        for(int a=0;a<3;a++){
            int soma=0;
            int n_p=0;
            for(int b=0;b<3;b++){             
         
                n_p = pontos[b] * matriz_translacao[a][b];
                soma+=n_p;
                
                
            }
            novos_pontos[a] = soma;
        }
        
        return novos_pontos;
        
    }
    
    //Rotação////////////////////////////////////////////////// 
    public static int[] rotacao(int Teta, int[] pontos){
        double[][] matriz_rotacao = {{Math.cos(Teta),-Math.sin(Teta),0},
                                     {Math.sin(Teta),Math.cos(Teta),0},
                                     {0,0,1}};
        
        System.out.println();
        System.out.println("Cos: " + Math.cos(Teta));
        System.out.println("Sen: " + Math.sin(Teta));
        System.out.println();
        int[] novos_pontos = new int[3];
        for(int a=0;a<3;a++){
            int soma=0;
            double n_p=0;
            for(int b=0;b<3;b++){             
         
                n_p = pontos[b] * matriz_rotacao[a][b];
                soma+=Math.round(n_p);
                
                System.out.println("multiplicacao: " + n_p);
            }
            novos_pontos[a] = soma;
        }
        System.out.println("x:" + novos_pontos[0]+ " y:" + novos_pontos[1] +" z:" + novos_pontos[2]);
        return novos_pontos; 
    }
    
    //EscalaArb//////////////////////////////////////////////////
    public static int[] escalaArb(int Ex,int Ey, int[] pontos, int[] pontosArb){
        int[][] matriz_escalaArb = {{Ex,0,pontosArb[0]*(1-Ex)},
                                    {0,Ey,pontosArb[1]*(1-Ey)},
                                    {0,0,1}};
                                   
        int[] novos_pontos = new int[3];
        for(int a=0;a<3;a++){
            int soma=0;
            int n_p=0;
            for(int b=0;b<3;b++){             
         
                n_p = pontos[b] * matriz_escalaArb[a][b];
                soma+=Math.round(n_p);
             
            }
            
            novos_pontos[a] = soma;
        }
        System.out.println("x:" + novos_pontos[0]+ " y:" + novos_pontos[1] +" z:" + novos_pontos[2]);
        return novos_pontos;
    }
    
    //RotaçãoArb//////////////////////////////////////////////////
    public static int[] rotacaoArb(int Teta, int[] pontos, int[] pontosArb){
        
        double[][] matriz_rotacaoArb = {{Math.cos(Teta),-Math.sin(Teta),pontosArb[0]*(1-Math.cos(Teta))+pontosArb[1]*Math.sin(Teta)},
                                        {Math.sin(Teta),Math.cos(Teta),pontosArb[1]*(1-Math.cos(Teta))-pontosArb[0]*Math.sin(Teta)},
                                        {0,0,1}};
        
        int[] novos_pontos = new int[3];
        for(int a=0;a<3;a++){
            int soma=0;
            double n_p=0;
            for(int b=0;b<3;b++){             
                //pontos[2]  matriz_rotacaoArb[0][2]
                //
                //
                //
                n_p = pontos[b] * matriz_rotacaoArb[a][b];
                soma+=Math.round(n_p);
                System.out.println("multiplicacao: " + n_p);
                
            }
            novos_pontos[a] = soma;
        }
        System.out.println("x:" + novos_pontos[0]+ " y:" + novos_pontos[1] +" z:" + novos_pontos[2]);
        return novos_pontos; 
    }
}
