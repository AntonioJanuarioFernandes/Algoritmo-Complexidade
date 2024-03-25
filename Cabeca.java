/**
 * Classe que representa informações de frequência cardíaca de uma pessoa.
 */
import java.util.Calendar;

public class Cabeca {
    private String nome;
    private String sobrenome;
    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    /**
     * Construtor da classe Cabeca.
     * @param nome Nome da pessoa.
     * @param sobrenome Sobrenome da pessoa.
     * @param diaNascimento Dia de nascimento da pessoa.
     * @param mesNascimento Mês de nascimento da pessoa.
     * @param anoNascimento Ano de nascimento da pessoa.
     */
    public Cabeca(String nome, String sobrenome, int diaNascimento, int mesNascimento, int anoNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(int diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public int getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(int mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    /**
     * Calcula a idade da pessoa em anos.
     * @return Idade da pessoa em anos.
     */
    // Método para calcular a idade em anos
    public int calcularIdade() {
        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - anoNascimento;

        if (hoje.get(Calendar.MONTH) < mesNascimento - 1 || 
            (hoje.get(Calendar.MONTH) == mesNascimento - 1 && hoje.get(Calendar.DAY_OF_MONTH) < diaNascimento)) {
            idade--;
        }

        return idade;
    }
    /**
     * Calcula a frequência cardíaca máxima da pessoa.
     * @return Frequência cardíaca máxima da pessoa.
     */
    // Método para calcular a frequência cardíaca máxima
    public int calcularFrequenciaMaxima() {
        return 220 - calcularIdade();
    }
    /**
     * Calcula o intervalo de frequência cardíaca alvo da pessoa.
     * @return Intervalo de frequência cardíaca alvo da pessoa.
     */

    // Método para calcular o intervalo de frequência cardíaca alvo
    public String calcularFrequenciaAlvo() {
        int frequenciaMaxima = calcularFrequenciaMaxima();
        int alvoMin = (int) (frequenciaMaxima * 0.5);
        int alvoMax = (int) (frequenciaMaxima * 0.85);
        return alvoMin + "-" + alvoMax;
    }
}