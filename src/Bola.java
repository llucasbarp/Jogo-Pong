import java.awt.*; //pacote de ferramentas de ambientação do windows
import java.util.*;

public class Bola extends Rectangle{
    
    Random random;	//variavel randomica
    int velocidadeX;	//velocidade eixo x da bola
    int velocidadeY;	//velociddade eixo y da bola
    int velocInicial = 2;	//velocidade inicial
    
    Bola(int x, int y, int largura, int altura){	//define o metodo e os parametros a serem recebidos
		super(x,y,largura,altura); //recebe os valores enviados pelo painel
		random = new Random();
		
		int randomDirecaoX = random.nextInt(2);	//randomiza a direção inicial da bola no eixo x
		if(randomDirecaoX == 0)
			randomDirecaoX--;	//caso o random de 0 sera transformado em -1 oque define a direção da bola
		defineDirecaoX(randomDirecaoX*velocInicial); //envia para o metodo abaixo a direção horizontal e a valocidade
		
		int randomDirecaoY = random.nextInt(2);	//randomiza a direção inicial da bola no eixo y
		if(randomDirecaoY == 0)
			randomDirecaoY--;	//caso o random de 0 sera transformado em -1 oque define a direção da bola
		defineDirecaoY(randomDirecaoY*velocInicial); //envia para o metodo abaixo a direção vertical e a valocidade
		
	}

    public void defineDirecaoX(int randomDirecaoX) {	
		velocidadeX = randomDirecaoX;
	}
	public void defineDirecaoY(int randomDirecaoY) {
		velocidadeY = randomDirecaoY;
	}
	public void mover() {
		x += velocidadeX;
		y += velocidadeY;
	}
	public void desenha(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}