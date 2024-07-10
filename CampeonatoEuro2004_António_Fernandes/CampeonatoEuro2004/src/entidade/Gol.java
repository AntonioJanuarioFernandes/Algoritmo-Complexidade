package entidade;

//import java.util.Objects;

public class Gol {
    private int id;
    private int jogoId;
    private int jogadorId;
    private int minuto;
    private String tipo;

    public Gol(int id, int jogoId, int jogadorId, int minuto, String tipo) {
        this.id = id;
        this.jogoId = jogoId;
        this.jogadorId = jogadorId;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public int getId() {return id;}
    public int getJogoId() {return jogoId;}
    public int getJogadorId() {return jogadorId;}
    public int getMinuto() {return minuto;}
    public String getTipo() {return tipo;}

    @Override
    public String toString() {
        return "Gol{" +
                "id=" + id +
                ", jogoId=" + jogoId +
                ", jogadorId=" + jogadorId +
                ", minuto=" + minuto +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}