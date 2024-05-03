import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static final double[] NOTAS = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0};
    private static final double[] MOEDAS = {1.0, 0.5, 0.25, 0.1, 0.05, 0.01};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o valor total da compra (UTILIZANDO VIRGULA PARA QUANDO HÁ CENTAVOS):");
        double valorCompra = scanner.nextDouble();

        System.out.println("Infome o valor recebido:");
        double valorRecebido = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;
        System.out.println("Troco: R$" + String.format("%.2f", troco));

        Map<Double, Integer> notas = new HashMap<>();
        Map<Double, Integer> moedas = new HashMap<>();

        if (troco > 0) {
            for (double valorNota : NOTAS) {
                int quantidadeNotas = (int) (troco / valorNota);
                if (quantidadeNotas > 0) {
                    notas.put(valorNota, quantidadeNotas);
                    troco -= quantidadeNotas * valorNota;
                }
            }

            for (double valorMoeda : MOEDAS) {
                int quantidadeMoedas = (int) (troco / valorMoeda);
                if (quantidadeMoedas > 0) {
                    moedas.put(valorMoeda, quantidadeMoedas);
                    troco -= quantidadeMoedas * valorMoeda;
                }
            }
        }

        for (Map.Entry<Double, Integer> nota : notas.entrySet()) {
            System.out.println(nota.getValue() + " nota(s) de R$" + String.format("%.2f", nota.getKey()));
        }

        for (Map.Entry<Double, Integer> moeda : moedas.entrySet()) {
            System.out.println(moeda.getValue() + " moeda(s) de R$" + String.format("%.2f", moeda.getKey()));
        }

        scanner.close();
    }
}
