package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class ListarInicial {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aluno");

    public static void main(String[] args) {
        // Listar alunos filtrados por inicial do nome
        System.out.print("Digite a letra inicial do nome para filtrar os alunos: ");
        Scanner scanner = new Scanner(System.in);
        String letraInicial = scanner.nextLine().toUpperCase();
        listarAlunosPorInicial(letraInicial);

        scanner.close();
        emf.close();
    }

    private static void listarAlunosPorInicial(String letraInicial) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Aluno> query = em.createQuery(
                "SELECT a FROM Aluno a WHERE UPPER(SUBSTRING(a.nome, 1, 1)) = :letraInicial", Aluno.class);
        query.setParameter("letraInicial", letraInicial);
        List<Aluno> alunos = query.getResultList();

        System.out.println("\nLista de Alunos com nome iniciando com '" + letraInicial + "':");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        em.close();
    }
}
