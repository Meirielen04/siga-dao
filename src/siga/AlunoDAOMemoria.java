package siga;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlunoDAOMemoria implements AlunoDAO {
    
    private final Map<String, Aluno> armazem = new HashMap<>();

    @Override 
    public void inserir(Aluno aluno) {
        armazem.put(aluno.getMatricula(), aluno);
    }

    @Override 
    public Aluno buscarPorMatricula(String matricula) {
        return armazem.get(matricula);
    }

    @Override 
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem.values());
    }
    

    @Override 
    public void atualizar(Aluno aluno) {
        armazem.put(aluno.getMatricula(), aluno);
    }

    @Override 
    public void remover(String matricula) {
        armazem.remove(matricula);
    }
}
