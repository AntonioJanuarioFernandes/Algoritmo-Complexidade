-- Tabela para armazenar os países
CREATE TABLE Paises (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela para armazenar as cidades
CREATE TABLE Cidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais_id INT,
    FOREIGN KEY (pais_id) REFERENCES Paises(id)
);

-- Tabela para armazenar os estádios
CREATE TABLE Estadios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade_id INT,
    capacidade INT,
    FOREIGN KEY (cidade_id) REFERENCES Cidades(id)
);

-- Tabela para armazenar as seleções
CREATE TABLE Selecoes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais_id INT,
    FOREIGN KEY (pais_id) REFERENCES Paises(id)
);

-- Tabela para armazenar os jogadores
CREATE TABLE Jogadores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    posicao VARCHAR(50),
    selecao_id INT,
    FOREIGN KEY (selecao_id) REFERENCES Selecoes(id)
);

-- Tabela para armazenar os grupos
CREATE TABLE Grupos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(2) NOT NULL
);


-- Tabela para armazenar o calendário dos jogos
CREATE TABLE Calendario (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    grupo_id INT,
    estadio_id INT,
    jornada INT,
    FOREIGN KEY (grupo_id) REFERENCES Grupos(id),
    FOREIGN KEY (estadio_id) REFERENCES Estadios(id)
);

-- Tabela para armazenar os jogos
CREATE TABLE Jogos (
    id SERIAL PRIMARY KEY,
    calendario_id INT,
    equipe1_id INT,
    equipe2_id INT,
    gols_equipe1 INT,
    gols_equipe2 INT,
    fase VARCHAR(20) DEFAULT 'Grupo',
    FOREIGN KEY (calendario_id) REFERENCES Calendario(id),
    FOREIGN KEY (equipe1_id) REFERENCES Selecoes(id),
    FOREIGN KEY (equipe2_id) REFERENCES Selecoes(id)
);

-- Tabela para armazenar as equipes que participaram de cada jogo
CREATE TABLE Equipes (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    selecao_id INT,
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (selecao_id) REFERENCES Selecoes(id)
);

-- Tabela para armazenar as substituições
CREATE TABLE Substituicoes (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    jogador_saiu_id INT,
    jogador_entrou_id INT,
    minuto INT,
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (jogador_saiu_id) REFERENCES Jogadores(id),
    FOREIGN KEY (jogador_entrou_id) REFERENCES Jogadores(id)
);

-- Tabela para armazenar os gols
CREATE TABLE Gols (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    jogador_id INT,
    minuto INT,
    tipo VARCHAR(50),
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (jogador_id) REFERENCES Jogadores(id)
);

-- Tabela para armazenar os cartões
CREATE TABLE Cartoes (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    jogador_id INT,
    minuto INT,
    tipo VARCHAR(50),
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (jogador_id) REFERENCES Jogadores(id)
);


-- Tabela para armazenar as estatísticas da equipe
CREATE TABLE Estatisticas_Equipe (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    selecao_id INT,
    remates INT,
    livres INT,
    foras_de_jogo INT,
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (selecao_id) REFERENCES Selecoes(id)
);

-- Tabela para armazenar as estatísticas do jogador
CREATE TABLE Estatisticas_Jogador (
    id SERIAL PRIMARY KEY,
    jogo_id INT,
    jogador_id INT,
    passes INT,
    assistencias INT,
    remates INT,
    minutos_jogados INT,
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id),
    FOREIGN KEY (jogador_id) REFERENCES Jogadores(id)
);

-- Tabela para armazenar as classificações dos grupos
CREATE TABLE Classificacoes (
    id SERIAL PRIMARY KEY,
    grupo_id INT,
    selecao_id INT,
    pontos INT DEFAULT 0,
    jogos INT DEFAULT 0,
    vitorias INT DEFAULT 0,
    empates INT DEFAULT 0,
    derrotas INT DEFAULT 0,
    gols_pro INT DEFAULT 0,
    gols_contra INT DEFAULT 0,
    saldo_gols INT DEFAULT 0,
    FOREIGN KEY (grupo_id) REFERENCES Grupos(id),
    FOREIGN KEY (selecao_id) REFERENCES Selecoes(id)
);

-- Tabela para armazenar as fases eliminatórias
CREATE TABLE Fases_Eliminatorias (
    id SERIAL PRIMARY KEY,
    fase VARCHAR(20),
    jogo_id INT,
    FOREIGN KEY (jogo_id) REFERENCES Jogos(id)
);


-- Criação da função principal
CREATE OR REPLACE FUNCTION AtualizaClassificacao(jogo_id INT) RETURNS VOID AS $$
DECLARE
    equipe1 INT;
    equipe2 INT;
    gols_equipe1 INT;
    gols_equipe2 INT;
BEGIN
    -- Obter detalhes do jogo
    SELECT j.equipe1_id, j.equipe2_id, j.gols_equipe1, j.gols_equipe2
    INTO equipe1, equipe2, gols_equipe1, gols_equipe2
    FROM Jogos j
    WHERE j.id = jogo_id;

    -- Atualizar estatísticas da equipe 1
    UPDATE Classificacoes
    SET 
        jogos = jogos + 1,
        gols_pro = gols_pro + gols_equipe1,
        gols_contra = gols_contra + gols_equipe2,
        saldo_gols = saldo_gols + (gols_equipe1 - gols_equipe2)
    WHERE selecao_id = equipe1;

    -- Atualizar estatísticas da equipe 2
    UPDATE Classificacoes
    SET 
        jogos = jogos + 1,
        gols_pro = gols_pro + gols_equipe2,
        gols_contra = gols_contra + gols_equipe1,
        saldo_gols = saldo_gols + (gols_equipe2 - gols_equipe1)
    WHERE selecao_id = equipe2;

    -- Atualizar pontos e resultados
    IF gols_equipe1 > gols_equipe2 THEN
        UPDATE Classificacoes 
        SET vitorias = vitorias + 1, pontos = pontos + 3 
        WHERE selecao_id = equipe1;
        
        UPDATE Classificacoes 
        SET derrotas = derrotas + 1 
        WHERE selecao_id = equipe2;
    ELSIF gols_equipe1 < gols_equipe2 THEN
        UPDATE Classificacoes 
        SET vitorias = vitorias + 1, pontos = pontos + 3 
        WHERE selecao_id = equipe2;
        
        UPDATE Classificacoes 
        SET derrotas = derrotas + 1 
        WHERE selecao_id = equipe1;
    ELSE
        UPDATE Classificacoes 
        SET empates = empates + 1, pontos = pontos + 1 
        WHERE selecao_id = equipe1;
        
        UPDATE Classificacoes 
        SET empates = empates + 1, pontos = pontos + 1 
        WHERE selecao_id = equipe2;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Criação do trigger
CREATE OR REPLACE FUNCTION AtualizaClassificacaoTrigger() RETURNS TRIGGER AS $$
BEGIN
    PERFORM AtualizaClassificacao(NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Definição do trigger
CREATE TRIGGER AtualizaClassificacaoTrigger
AFTER INSERT ON Jogos
FOR EACH ROW
EXECUTE FUNCTION AtualizaClassificacaoTrigger();


select *from paises;
select *from cidades;
select *from estadios;
select *from cidades;
select *from Estatisticas_equipe;
select *from selecoes;
select *from grupos;
select *from calendario;
select *from jogadores;

delete from selecoes where id  > 32;