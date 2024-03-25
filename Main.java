/**
 * Classe principal que solicita informações sobre uma pessoa e imprime informações de frequência cardíaca.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite o sobrenome:");
        String sobrenome = scanner.nextLine();

        System.out.println("Digite o dia de nascimento:");
        int diaNascimento = scanner.nextInt();

        System.out.println("Digite o mês de nascimento:");
        int mesNascimento = scanner.nextInt();

        System.out.println("Digite o ano de nascimento:");
        int anoNascimento = scanner.nextInt();

        Cabeca pessoa = new Cabeca(nome, sobrenome, diaNascimento, mesNascimento, anoNascimento);

        System.out.println("\nInformações da pessoa:");
        System.out.println("Nome completo: " + pessoa.getNome() + " " + pessoa.getSobrenome());
        System.out.println("Data de nascimento: " + pessoa.getDiaNascimento() + "/" + pessoa.getMesNascimento() + "/" + pessoa.getAnoNascimento());
        System.out.println("Idade: " + pessoa.calcularIdade() + " anos");
        System.out.println("Frequência cardíaca máxima: " + pessoa.calcularFrequenciaMaxima() + " bpm");
        System.out.println("Frequência cardíaca alvo: " + pessoa.calcularFrequenciaAlvo() + " bpm");

        scanner.close();
    }
}
