import java.awt.*;  //pacote de ferramentas de ambientação grafica do sistema operacional

public class Pontuacao extends Rectangle{

    static int largura_game;
    static int altura_game;
    int jogador1;
    int jogador2;

    Pontuacao(int largura, int altura){ //recebe os dados enviados pelo painel e define a altura e largura do jogo
        Pontuacao.largura_game = largura;
        Pontuacao.altura_game = altura;

    }
    public void desenha(Graphics g){
        g.setColor(Color.white); //define a cor do texto como branco
        g.setFont(new Font("Consolas", Font.PLAIN,60)); //define fonte e tamanho da letra

        g.drawLine(largura_game/2, 0, largura_game/2, altura_game); //desenha uma linha no centro do jogo

        g.drawString(String.valueOf(jogador1/10)+String.valueOf(jogador1%10), (largura_game/2)-85, 50); //escreve a pontuação do jogador 1
		g.drawString(String.valueOf(jogador2/10)+String.valueOf(jogador2%10), (largura_game/2)+20, 50); //escreve a pontuação do jogador 2
    }
    
}