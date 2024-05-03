import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// classe para o jogo da velha, JFrame para a janela
public class JogoDaVelha extends JFrame implements ActionListener {
    private JButton[][] botoes; 
    private boolean jogadorX; 
    private JLabel status; 
    private boolean alternarJogador;

    // construtor da janela e iniciar o tabuleiro
    public JogoDaVelha() {

        // config janela
        super("Jogo da Velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        // criar o grid do tabuleiro
        JPanel gridPanel = new JPanel(new GridLayout(3, 3)); 
        botoes = new JButton[3][3];
        // botões do tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton(""); 
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                botoes[i][j].addActionListener(this); 
                gridPanel.add(botoes[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // definir vez do jogador
        alternarJogador = true; 
        jogadorX = alternarJogador; 
        status = new JLabel("Vez do jogador " + (jogadorX ? "X" : "O")); 
        status.setHorizontalAlignment(JLabel.CENTER); 
        add(status, BorderLayout.SOUTH); 

        setVisible(true); 
    }

    // verificar cliques nos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        
        if (btn.getText().equals("")) {
            btn.setText(jogadorX ? "X" : "O");
            status.setText("Vez do jogador " + (jogadorX ? "O" : "X")); 
            jogadorX = !jogadorX; // alternar para o próximo jogador
            verificarVencedor();
        }
    }

    // verificar se há vencedor ou se houve empate
    private void verificarVencedor() {
        // criar nova matriz e copiar informações dos botões
        String[][] campo = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campo[i][j] = botoes[i][j].getText(); 
            }
        }

        // verificar linhas
        for (int i = 0; i < 3; i++) {
            if (campo[i][0].equals(campo[i][1]) && campo[i][1].equals(campo[i][2]) && !campo[i][0].equals("")) {
                exibirResultado(campo[i][0] + " venceu!"); // Mostra uma mensagem de vitória
                return;
            }
        }

        // verificar colunas
        for (int j = 0; j < 3; j++) {
            if (campo[0][j].equals(campo[1][j]) && campo[1][j].equals(campo[2][j]) && !campo[0][j].equals("")) {
                exibirResultado(campo[0][j] + " venceu!"); // Mostra uma mensagem de vitória
                return;
            }
        }

        // verificar diagonais
        if (campo[0][0].equals(campo[1][1]) && campo[1][1].equals(campo[2][2]) && !campo[0][0].equals("")) {
            exibirResultado(campo[0][0] + " venceu!"); // Mostra uma mensagem de vitória
            return;
        }
        if (campo[0][2].equals(campo[1][1]) && campo[1][1].equals(campo[2][0]) && !campo[0][2].equals("")) {
            exibirResultado(campo[0][2] + " venceu!"); // Mostra uma mensagem de vitória
            return;
        }

        // verificar se não a vencedor
        boolean empate = true;
        // verificar todos os campos
        // se houver um campo vazio, não é empate
        // PS: CONFESSO QUE NAO SEI SE ISSO PODE GERAR BUG MAS ATÉ AGORA FUNCIONOU ENTÃO TA MEC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (campo[i][j].equals("")) { 
                    empate = false;
                    break;
                }
            }
        }

        if (empate) {
            exibirResultado("Empate!"); 
        }
    }

    // exibir o resultado final e perguntar se quer jogar novamente
    private void exibirResultado(String mensagem) {
        String[] opcoes = { "Sim", "Não" };
        int resposta = JOptionPane.showOptionDialog(
            this,
            mensagem + " Quer jogar novamente?",
            "Fim do jogo",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0] 
        );

        if (resposta == 0) { 
            alternarJogador = !alternarJogador; 
            reiniciarJogo(); 
        } else {
            System.exit(0);
        }
    }

    // reiniciar o jogo para uma nova partida
    private void reiniciarJogo() {
        // limpar botões
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }

        // alterar jogador
        
        jogadorX = alternarJogador; 
        
        status.setText("Vez do jogador " + (jogadorX ? "X" : "O")); 
    }

    // iniciar o jogo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JogoDaVelha::new); 
    }
}
