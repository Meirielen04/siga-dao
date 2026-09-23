package siga;

import java.util.List;
import java.util.ArrayList;

public class AlunoDAOBanco implements AlunoDAO {
    @Override
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula, media) VALUES ('"
                + aluno.getNome() + "', '"
                + aluno.getMatricula() + "', "
                + aluno.getMedia() + ")";
        String linha = aluno.getMatricula() + ";" + aluno.getNome() + ";" + aluno.getMedia();
        BancoSimulado.executar(sql, linha);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT nome, matricula, media FROM aluno WHERE matricula = '" + matricula + "'";
        List<String> linhas = BancoSimulado.consultar(sql);

        for (String linha : linhas) {
            Aluno aluno = parseLinhaAluno(linha);
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }

        return null;
    }

    @Override 
    public List<Aluno> listarTodos() {
        String sql = "SELECT nome, matricula, media FROM aluno";
        List<String> linhas = BancoSimulado.consultar(sql);

        List<Aluno> alunos = new ArrayList<>();

        for (String linha : linhas) {
            alunos.add(parseLinhaAluno(linha));
        }

        return alunos;
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = '" + aluno.getNome()
                + "', media = " + aluno.getMedia()
                + " WHERE matricula = '" + aluno.getMatricula() + "'";
        String linha = aluno.getMatricula() + ";" + aluno.getNome() + ";" + aluno.getMedia();
        BancoSimulado.executar(sql, linha);
    }

    @Override
    public void remover(String matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = '" + matricula + "'";
        BancoSimulado.executar(sql, "");
    }

    private Aluno parseLinhaAluno(String linha) {
        String[] partes = linha.split(";");
        String matricula = partes[0];
        String nome = partes[1];
        double media = Double.parseDouble(partes[2]);

        return new Aluno(nome, matricula, media);
    }

}