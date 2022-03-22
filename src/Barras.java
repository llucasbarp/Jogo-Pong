import java.awt.*;  //pacote de ferramentas de ambientação grafica do sistema operacional
import java.awt.event.*; //pacote de ferramentas de eventos do teclado

public class Barras extends Rectangle{
    
    int id; //identidade para definir qual barra estamos referenciando 
    int velocidadeY;    //variavel para velocidade da barra
    int velocidade = 10;    //devine velocidade das barras do jogo
    
    Barras(int x, int y, int BARRA_LARGURA, int BARRA_ALTURA, int id){
        super(x,y,BARRA_LARGURA, BARRA_ALTURA); //envia/recebe o tamanho das barras
        this.id = id;
    }

    public void precionar(KeyEvent e){ //faz com que quando precionado os botoes w,s,cima,baixo movam as barras
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    mudadirecaoY(-velocidade);
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    mudadirecaoY(+velocidade);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    mudadirecaoY(-velocidade);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    mudadirecaoY(+velocidade);
                }
                break;
        }
    }
    public void soltar(KeyEvent e){    //faz com que quando soltos os botões as barras parem de se mover
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    mudadirecaoY(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    mudadirecaoY(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    mudadirecaoY(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    mudadirecaoY(0);
                }
                break;
        }
    }
    
    public void mudadirecaoY(int direcaoY){ //recebe o valor do teclado precionado e muda a posição das barras
        velocidadeY = direcaoY;     //esse metodo em si que executa os eventos de teclado
    }
    public void mover(){    //recebe o movimento do painel
        y = y+velocidadeY;  //atualiza o valor de y mudando onde ele e desenhado
    }
    public void desenha(Graphics g){    //define e desenha as barras na tela
        if(id==1)            //condicional que verifica o id da barra do jogador
            g.setColor(Color.blue); //define a cor da barra de id 1 como azul
        else
            g.setColor(Color.red); //define a cor da barra de id 2 como vermelha
        g.fillRect(x, y, width, height);    //retorna o valor das barras para a tela do jogo
    }
}