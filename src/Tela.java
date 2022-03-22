import java.awt.*;  //pacote de ferramentas de ambientação grafica do sistema operacional
import javax.swing.*;   //pacote de ferramentas de ambientação grafica do java 

public class Tela extends JFrame{ //jframe permite a implementação da tela do jogo

    Painel painel;    //panel atribuido como chamada da classe.

    Tela(){ //define as propriedades da tela do jogo

        painel = new Painel();  //define como a classe painel sera chamada

        this.setTitle("Pong Game"); //titulo da barra superior
        this.setResizable(false);   //bloqueia o redimensionamento da tela
        this.setBackground(Color.black);    //define o background como preto
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //fecha o programa totalmente

        this.add(painel);   //adiciona a execução da classe painel na tela de exibição
        this.pack();    //faz com que a janela seja aberta já no tamanho dos componentes
        this.setVisible(true); //ativa a visualização da tela

        this.setLocationRelativeTo(null); //define a localização da tela na qual o programa sera exibido pode ser trocao por setlocation

    }
}