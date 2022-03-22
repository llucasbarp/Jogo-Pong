import java.awt.*;  //pacote de ferramentas de ambientação grafica do sistema operacional
import javax.swing.*;   //pacote de ferramentas de ambientação grafica do java 
import java.awt.event.*; //pacote de eventos relacionados ao precionar de teclado
import java.util.*;

public class Painel extends JPanel implements Runnable{

    static final int LARGURA_JOGO = 1000;   //define a largura da tela do jogo
    static final int ALTURA_JOGO = 600;     //define a altura da tela do jogo
    static final Dimension SCREEN_SIZE = new Dimension(LARGURA_JOGO,ALTURA_JOGO);   //define a dimensao do jogo em si;
    static final int BOLA_TAMANHO = 20; //define tamanho da bola
    static final int BARRA_LARGURA = 25;    //define a largura da barra
    static final int BARRA_ALTURA = 100;    //define a altura da barra

    Thread gameThread;      //instancias para chamar as classes posteriormente
    Image imagem;
    Graphics graficos;
    Random random;
    Barras barra1;
    Barras barra2;
    Bola bola;
    Pontuacao pontuacao;    //instancias devem ser iniciadas depois

    Painel(){
        novasBarras(); //chama primeiras barras
        novaBola(); //chama a primeira bola para o jogo
    
        pontuacao = new Pontuacao(LARGURA_JOGO,ALTURA_JOGO);    //inicializacao da instancia de pontuação enviando os alores de altura e largura do jogo
        this.setFocusable(true);    //usa o tamanho dos componentes do panel como base para o tamanho da tela
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this); //inicializa a instancia das thread, threads é responsavel pelo processamento paralelo
        gameThread.start(); //inicia o jogo
    }    

    public void novaBola(){ //responsavel por iniciar a bola do jogo
        random = new Random(); //gera um valor randomio
		bola = new Bola((LARGURA_JOGO/2)-(BOLA_TAMANHO/2),random.nextInt(ALTURA_JOGO-BOLA_TAMANHO),BOLA_TAMANHO,BOLA_TAMANHO); //define os parametros enviados para serem usados na bola
    }
    public void novasBarras(){//responsavel por iniciar as barras do jogo
        barra1 = new Barras(0,(ALTURA_JOGO/2)-(BARRA_ALTURA/2),BARRA_LARGURA,BARRA_ALTURA,1); //dados para barra 1
        barra2 = new Barras(LARGURA_JOGO-BARRA_LARGURA,(ALTURA_JOGO/2)-(BARRA_ALTURA/2),BARRA_LARGURA,BARRA_ALTURA,2); //dados para barra 2
    }
    public void paint(Graphics g){
        imagem = createImage(getWidth(),getHeight()); //pega o tamnho do jogo
        graficos = imagem.getGraphics(); //insere nos graficos
        desenha(graficos); //envia para serem desenhados no metodo desenha
        g.drawImage(imagem, 0, 0, this); //define o lugar para aparecer a imagem resultante
    }
    public void desenha(Graphics g){
        barra1.desenha(g); //chama metodo de desenho para a barra 1
        barra2.desenha(g); //chama metodo de desenho para barra 2
        bola.desenha(g); //chama metodo de desenho para bola
        pontuacao.desenha(g); //chama metodo de desenho da pontuação do jogo
    }
    public void mover(){ //chamado pelo run da classe painel
        barra1.mover(); //chama a classe barras para verificar o movimento da barra 1
        barra2.mover(); //chama a classe barras para verifica o movimento da barra 2
        bola.mover(); //atualiza o movimento da bola
    }
    public void testeColisao(){ //testes de colisão das barras e bolas do jogo
        
        if(bola.y <=0){ //ajusta a direção da bola caso ela acerte a parte inferior da janela
            bola.defineDirecaoY(-bola.velocidadeY);
        }
        if(bola.y >= ALTURA_JOGO-BOLA_TAMANHO){//ajusta a direção da bola caso ela acerte a parte superior da janela
            bola.defineDirecaoY(-bola.velocidadeY);
        }

        if(bola.intersects(barra1)){    //caso a bola atinja a primeira barra
            bola.velocidadeX = Math.abs(bola.velocidadeX); //envia para classe bola a velocidade atual dela
            bola.velocidadeX++; //aumenta a velocidade da bola
            if(bola.velocidadeY > 0)
                bola.velocidadeY++; //aumenta a velocidade
            else
                bola.velocidadeX--; //reduz a velocidade do eixo x
                bola.defineDirecaoX(bola.velocidadeX); //inverte a direção x da bola
                bola.defineDirecaoY(bola.velocidadeY);  //inverte a direção y da bola
        }
        if(bola.intersects(barra2)){
            bola.velocidadeX = Math.abs(bola.velocidadeX);
            bola.velocidadeX++;//aumenta a velocidade
            if(bola.velocidadeY > 0)
                bola.velocidadeY++; //aumenta a velocidade
            else
                bola.velocidadeY--;
                bola.defineDirecaoX(-bola.velocidadeX);
                bola.defineDirecaoY(bola.velocidadeY);
        }

        if(barra1.y<=0) //verifica a localização das barras dentro do jogo e as para caso cheguem ao limite se sim as bloqueia
            barra1.y = 0;
        if(barra1.y >= ALTURA_JOGO-BARRA_ALTURA)
            barra1.y = ALTURA_JOGO-BARRA_ALTURA;
        if(barra2.y<=0)
            barra2.y = 0;
        if(barra2.y >= ALTURA_JOGO-BARRA_ALTURA)
            barra2.y = ALTURA_JOGO-BARRA_ALTURA;
        
        if(bola.x <= 0){ //verifica se a ola passou dos limites x da janela e adiciona ponto ao jogador
            pontuacao.jogador2++; //aumenta a pontuacao do jogador 2
            novasBarras(); // redesenha as barras
            novaBola(); //redesenha as bolas
        }
        if(bola.x >= LARGURA_JOGO-BOLA_TAMANHO){ //verifica a se a bola passou do limete x da tela
            pontuacao.jogador1++; //aumenta a pontuacao do jogador 1
            novasBarras(); // redesenha as barras
            novaBola(); //redesenha as bolas
        }
    }
    public void run(){ //responsavel pelo loop do jogo
        long ultimoTempo = System.nanoTime(); //define a ultima hora
        double frames = 60.0; //define a quantidade de frames por seguind
        double momento = 1000000000/frames; //define em qual segundo o jogo vai ser redesenhado
        double delta = 0;   //comparativo
        while(true){ //enquanto o jogo estiver rodado
            long agora = System.nanoTime(); //verifica o tempo atual
            delta += (agora - ultimoTempo) / momento; //gera um valor para delta 
            ultimoTempo = agora; //reescreve a hora do jogo para o proximo loop
            if(delta >= 1){ //quando delta for maior ou igual a 1 o jogo vai executar os metodos abaixo
                mover();    //chama o metodo move do proprio painel, bolas e barras
                testeColisao(); //verifica se ocorreu colizoes
                repaint();  //redesenha o jogo na tela
                delta--;    //reduz o delta para que haja uma nova verificação posteriormente
                //se o delta nao for zerado, o jogo vai ser executado sem limite de velocidade
            }
        }
    }
    public class AL extends KeyAdapter{ //eventos de pressão, apertar e soltar botoes
        public void keyPressed(KeyEvent e){
            barra1.precionar(e);
            barra2.precionar(e);
        }
        public void keyReleased(KeyEvent e){
            barra1.soltar(e);
            barra2.soltar(e);
        }
    }
}