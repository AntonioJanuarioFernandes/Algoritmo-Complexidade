package entidade;
import DAO.*;
import java.util.List;


public class Main {

    private static CrudJogador crudJogador = new CrudJogador();
    private static CrudSelecao crudSelecao = new CrudSelecao();
    private static CrudEstadio crudEstadio = new CrudEstadio();
    private static CrudCidade crudCidade = new CrudCidade();
    private static CrudJogo crudJogo = new CrudJogo();
    private static CrudGols crudGols = new CrudGols();
    private static CrudCartoes crudCartoes = new CrudCartoes();
    //private static CrudGrupo crudGrupo = new CrudGrupo();
    private static CrudClassificacao crudClassificacao = new CrudClassificacao();
    private static CrudEstatisticasEquipe crudEstatisticasEquipe = new CrudEstatisticasEquipe();
    private static CrudEstatisticasJogador crudEstatisticasJogador = new CrudEstatisticasJogador();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Menu Principal:");
            System.out.println("0. Gerir Jogador");
            System.out.println("1. Gerir Selecao");
            System.out.println("2. Gerir Estadio");
            System.out.println("3. Gerir Cidade");
            System.out.println("4. Realizar Jogo");
            System.out.println("5. Listar Melhores Marcadores");
            System.out.println("6. Estatisticas");
            System.out.println("7. Listar Grupos");
            System.out.println("8. Gerir Reset");
            System.out.println("9. Gerir Listas");
            System.out.println("10. Sair");

            int choice = InputUtils.getInt("Escolha uma opção: ");

            switch (choice) {
                case 0:
                    gerirJogador();
                    break;
                case 1:
                    gerirSelecao();
                    break;
                case 2:
                    gerirEstadio();
                    break;
                case 3:
                    gerirCidade();
                    break;
                case 4:
                    simularJogo();
                    break;
                case 5:
                    listarMelhoresMarcadores();
                    break;
                case 6:
                    estatisticas();
                    break;
                case 7:
                    listarGrupos();
                    break;
                case 8:
                    gerirReset();
                    break;
                case 9:
                    gerirListas();
                    break;
                case 10:
                    running = false;
                    InputUtils.closeScanner();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void gerirReset() {
        System.out.println("Gerir reset:");
        System.out.println("1. Resetar tabela classificacoes");
        System.out.println("2. Resetar tabela golo");
        System.out.println("3. Resetar tabela Cartoes");
        System.out.println("4. Resetar tabela jogos");
        System.out.println("5. Resetar tabela Estatisticas jogador");
        System.out.println("6. Resetar tabela Estatisticas Equipa");
        System.out.println("7. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
            	crudClassificacao.resetarClassificacoes();
                break;
            case 2:
                crudGols.resetarGols();
                break;
            case 3:
                crudCartoes.resetarCartoes();
                break;
            case 4:
                crudJogo.resetarJogo();
                break;
            case 5:
                crudEstatisticasJogador.resetarEstatisticasJogador();
                break;
            case 6:
                crudEstatisticasEquipe.resetarEstatisticasEquipe();
                break;
            case 7:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void gerirListas() {
        System.out.println("Gerir Listas:");
        System.out.println("1. Listar tabela classificacoes");
        System.out.println("2. Listar tabela golo");
        System.out.println("3. Listar tabela Cartoes");
        System.out.println("4. Listar tabela jogos");
        System.out.println("5. Listar tabela Estatisticas jogador");
        System.out.println("6. Listar tabela Estatisticas Equipa");
        System.out.println("7. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                crudClassificacao.consultarClassificacoes();
                break;
            case 2:
                crudGols.consultarGols();
                break;
            case 3:
                crudCartoes.consultarCartoes();
                break;
            case 4:
                crudJogo.consultarJogos();
                break;
            case 5:
                crudEstatisticasJogador.consultarEstatisticasJogador();
                break;
            case 6:
                crudEstatisticasEquipe.consultarEstatisticasEquipe();
                break;
            case 7:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void gerirJogador() {
        System.out.println("Gerir Jogador:");
        System.out.println("1. Adicionar Jogador");
        System.out.println("2. Consultar Jogador por Nome");
        System.out.println("3. Atualizar Jogador");
        System.out.println("4. Deletar Jogador");
        System.out.println("5. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                adicionarJogador();
                break;
            case 2:
                consultarJogadorPorNome();
                break;
            case 3:
                atualizarJogador();
                break;
            case 4:
                deletarJogador();
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void adicionarJogador() {
        String nome = InputUtils.getString("Nome do jogador: ");
        String posicao = InputUtils.getString("Posição do jogador: ");
        int selecaoId = InputUtils.getInt("ID da seleção: ");

        Jogador jogador = new Jogador(0, nome, posicao, selecaoId);
        crudJogador.adicionarJogador(jogador);
    }

    private static void consultarJogadorPorNome() {
        String nome = InputUtils.getString("Nome do jogador: ");
        crudJogador.consultarPorNome(nome);
    }

    private static void atualizarJogador() {
        int id = InputUtils.getInt("ID do jogador: ");
        String nome = InputUtils.getString("Nome do jogador: ");
        String posicao = InputUtils.getString("Posição do jogador: ");
        int selecaoId = InputUtils.getInt("ID da seleção: ");

        Jogador jogador = new Jogador(id, nome, posicao, selecaoId);
        crudJogador.atualizarJogador(jogador);
    }

    private static void deletarJogador() {
        int id = InputUtils.getInt("ID do jogador: ");
        crudJogador.deletarJogador(id);
    }

    private static void gerirSelecao() {
        System.out.println("Gerir Selecao:");
        System.out.println("1. Adicionar Selecao");
        System.out.println("2. Consultar selecao por Nome");
        System.out.println("3. Atualizar Selecao");
        System.out.println("4. Deletar Selecao");
        System.out.println("5. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                adicionarSelecao();
                break;
            case 2:
                consultarSelecaoPorNome();
                break;
            case 3:
                atualizarSelecao();
                break;
            case 4:
                deletarSelecao();
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    private static void adicionarSelecao() {
        String nome = InputUtils.getString("Nome da Selecao: ");
        int paisId = InputUtils.getInt("Id da seleção: ");
        int grupoId = InputUtils.getInt("Grupo Id: ");
        Selecao selecao = new Selecao(0, nome, paisId, grupoId);
        crudSelecao.adicionarSelecao(selecao);
    }
    
    private static void consultarSelecaoPorNome() {
        String nome = InputUtils.getString("Nome da selecao: ");
        crudSelecao.consultarPorNome(nome);
    }
    
    private static void atualizarSelecao() {
        int id = InputUtils.getInt("ID da selecao: ");
        String nome = InputUtils.getString("Nome do jogador: ");
        int paisId = InputUtils.getInt("ID da seleção: ");
        int grupoId = InputUtils.getInt("Grupo Id: ");
        Selecao selecao = new Selecao(id, nome, paisId, grupoId);
        crudSelecao.atualizarSelecao(selecao);
    }
    
    private static void deletarSelecao() {
        int id = InputUtils.getInt("ID da Selecao: ");
        crudSelecao.deletarSelecao(id);
    }
    
    
    private static void gerirEstadio() {
        System.out.println("Gerir Estadio:");
        System.out.println("1. Adicionar Estadio");
        System.out.println("2. Consultar Estadio por Nome");
        System.out.println("3. Atualizar Estadio");
        System.out.println("4. Deletar Estadio");
        System.out.println("5. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                adicionarEstadio();
                break;
            case 2:
                consultarEstadioPorNome();
                break;
            case 3:
                atualizarEstadio();
                break;
            case 4:
                deletarEstadio();
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    private static void adicionarEstadio() {
        String nome = InputUtils.getString("Nome do estadio: ");
        int cidadeId = InputUtils.getInt("cidade do estadio: ");
        int capacidade = InputUtils.getInt("Capacidade do estadio: ");

        Estadio estadio = new Estadio(0, nome, cidadeId, capacidade);
        crudEstadio.atualizarEstadio(estadio);
    }
    
    private static void consultarEstadioPorNome() {
        String nome = InputUtils.getString("Nome do estadio: ");
        crudEstadio.consultarPorNome(nome);
    }
    
    private static void atualizarEstadio() {
        int id = InputUtils.getInt("ID do estadio: ");
        String nome = InputUtils.getString("Nome do estadio: ");
        int cidadeId = InputUtils.getInt("cidade do estadio: ");
        int capacidade = InputUtils.getInt("Capacidade do estadio: ");

        Estadio estadio = new Estadio(id, nome, cidadeId, capacidade);
        crudEstadio.atualizarEstadio(estadio);
    }
    
    private static void deletarEstadio() {
        int id = InputUtils.getInt("ID do estadio: ");
        crudEstadio.deletarEstadio(id);
    }
    

    private static void gerirCidade() {
        System.out.println("Gerir Cidade:");
        System.out.println("1. Adicionar Cidade");
        System.out.println("2. Consultar Cidade por Nome");
        System.out.println("3. Atualizar Cidade");
        System.out.println("4. Deletar Cidade");
        System.out.println("5. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                adicionarCidade();
                break;
            case 2:
                consultarCidadePorNome();
                break;
            case 3:
                atualizarCidade();
                break;
            case 4:
                deletarCidade();
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    private static void adicionarCidade() {
        String nome = InputUtils.getString("Nome da cidade: ");
        int paisId = InputUtils.getInt("Id do Pais: ");

        Cidade cidade = new Cidade(0, nome, paisId);
        crudCidade.adicionarCidade(cidade);
    }
    
    private static void consultarCidadePorNome() {
        String nome = InputUtils.getString("Nome da cidade: ");
        crudCidade.consultarPorNome(nome);
    }
    
    private static void atualizarCidade() {
        int id = InputUtils.getInt("ID da cidade: ");
        String nome = InputUtils.getString("Nome da cidade: ");
        int paisId = InputUtils.getInt("Id do Pais: ");

        Cidade cidade = new Cidade(id, nome, paisId);
        crudCidade.atualizarCidade(cidade);
    }
    
    private static void deletarCidade() {
        int id = InputUtils.getInt("ID da cidade: ");
        crudCidade.deletarCidade(id);
    }

    private static void simularJogo() {
        System.out.println("Realizar Jogo:");
        Simulacao simulacao = new Simulacao();

        // Fase de Grupos não precisa ser simulada novamente se já foi simulada antes
        simulacao.simularFaseDeGrupos();

        // Obter seleções classificadas para as quartas de final
        List<Selecao> selecoesClassificadasParaQuartas = simulacao.obterSelecoesClassificadasParaQuartas();
        System.out.println("Número de seleções classificadas para as quartas de final: " + selecoesClassificadasParaQuartas.size());

        // Simular jogos das quartas de final
        List<Selecao> vencedoresQuartas = simulacao.simularQuartas(selecoesClassificadasParaQuartas);
        System.out.println("Número de seleções classificadas para as semifinais: " + vencedoresQuartas.size());


        // Simular jogos das semifinais
        List<Selecao> vencedoresSemifinais = simulacao.simularSemifinais(vencedoresQuartas);
        System.out.println("Número de seleções classificadas para a final: " + vencedoresSemifinais.size());

        // Simular jogo final
        Selecao campeao = simulacao.simularFinal(vencedoresSemifinais);
        System.out.println("Campeão: " + campeao.getNome());

        System.out.println("Jogos Realizados e resultados exibidos.");
    }

    
    private static void listarMelhoresMarcadores() {
        System.out.println("Listar Melhores Marcadores:");
        List<Jogador> melhoresMarcadores = crudJogador.consultarMelhoresMarcadores();
        for (Jogador jogador : melhoresMarcadores) {
            System.out.println(jogador.getNome() + " - Gols: " + jogador.getGols());
        }
    }

    
    /*private static void listarGrupos() {
        System.out.println("Listar grupos:");

        listarGrupo("A");
        listarGrupo("B");
        listarGrupo("C");
        listarGrupo("D");
    }*/

    private static void listarGrupos() {
        System.out.println("Listar grupos:");

        // Definir os IDs dos grupos correspondentes aos grupos A, B, C e D
        int[] idsGrupos = {5, 6, 7, 8};
        String[] nomesGrupos = {"A", "B", "C", "D"};

        // Iterar sobre os grupos e listar as seleções de cada um
        for (int i = 0; i < idsGrupos.length; i++) {
            int idGrupo = idsGrupos[i];
            String nomeGrupo = nomesGrupos[i];
            
            // Consultar as seleções do grupo usando o ID do grupo na tabela classificacoes
            List<Selecao> selecoes = crudSelecao.consultarPorGrupo(idGrupo);
            
            // Exibir as seleções do grupo com seus IDs
            System.out.println("Seleções do Grupo " + nomeGrupo + ":");
            for (Selecao selecao : selecoes) {
                System.out.println("- ID: " + selecao.getId() + ", Nome: " + selecao.getNome());
            }
        }
    }

    private static void estatisticas() {
        System.out.println("Estatisticas:");
        System.out.println("1. Estatísticas da Equipe");
        System.out.println("2. Estatísticas do Jogador");
        System.out.println("3. Voltar");

        int choice = InputUtils.getInt("Escolha uma opção: ");

        switch (choice) {
            case 1:
                consultarEstatisticasEquipe();
                break;
            case 2:
                consultarEstatisticasJogador();
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void consultarEstatisticasEquipe() {
        int idSelecao = InputUtils.getInt("ID da selecao: ");
        List<EstatisticasEquipe> estatisticas = crudEstatisticasEquipe.consultarPorSelecaoId(idSelecao);
        for (EstatisticasEquipe estatistica : estatisticas) {
            System.out.println("Jogo ID: " + estatistica.getJogoId() + " - Remates: " + estatistica.getRemates() + " - Livres: " + estatistica.getLivres() + " - Foras de Jogo: " + estatistica.getForasDeJogo());
        }
    }

    private static void consultarEstatisticasJogador() {
        int idJogador = InputUtils.getInt("ID do jogador: ");
        List<EstatisticasJogador> estatisticas = crudEstatisticasJogador.consultarPorJogadorId(idJogador);
        
        if (estatisticas.isEmpty()) {
            System.out.println("Nenhuma estatística encontrada para o jogador com ID: " + idJogador);
        } else {
            for (EstatisticasJogador estatistica : estatisticas) {
                System.out.println("Estatísticas do jogador para o jogo ID: " + estatistica.getJogoId());
                System.out.println(" - Passes: " + estatistica.getPasses());
                System.out.println(" - Assistências: " + estatistica.getAssistencias());
                System.out.println(" - Remates: " + estatistica.getRemates());
                System.out.println(" - Minutos Jogados: " + estatistica.getMinutosJogados());
                System.out.println(); // Para separar as estatísticas de diferentes jogos
            }
        }
    }
}