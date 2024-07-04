package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ListarAlunos {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aluno");

    public static void main(String[] args) {
        // Listar todos os alunos por nome
        System.out.println("Listagem de Alunos por Nome:");
        listarAlunosPorNome();

        // Listar todos os alunos por ID
        System.out.println("\nListagem de Alunos:");
        listarAlunosPorId();

        emf.close();
    }

    private static void listarAlunosPorNome() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a ORDER BY a.nome", Aluno.class);
        List<Aluno> alunos = query.getResultList();

        System.out.println("\nLista de Alunos ordenados por Nome:");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        em.close();
    }

    private static void listarAlunosPorId() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a ORDER BY a.id", Aluno.class);
        List<Aluno> alunos = query.getResultList();

        System.out.println("\nLista de Alunos ordenados por ID:");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        em.close();
    }
}
