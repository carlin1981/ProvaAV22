package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class RemoverAluno {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aluno");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o ID do aluno a ser removido
        System.out.print("Digite o ID do aluno que deseja remover: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Busca o aluno pelo ID
        Aluno aluno = buscarAlunoPorId(id);

        if (aluno != null) {
            // Exibe os dados do aluno encontrado
            System.out.println("Aluno encontrado:");
            System.out.println(aluno);

            // Confirmação para remoção
            System.out.print("Deseja realmente remover este aluno? (S/N): ");
            String confirmacao = scanner.nextLine().toUpperCase();

            if (confirmacao.equals("S")) {
                removerAluno(aluno);
                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Operação de remoção cancelada.");
            }
        } else {
            System.out.println("Aluno não encontrado com o ID informado.");
        }

        scanner.close();
        emf.close();
    }

    private static Aluno buscarAlunoPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Aluno aluno = em.find(Aluno.class, id);
        em.close();
        return aluno;
    }

    private static void removerAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Busca o aluno novamente para garantir que está no contexto de persistência
        Aluno alunoParaRemover = em.find(Aluno.class, aluno.getId());
        if (alunoParaRemover != null) {
            em.remove(alunoParaRemover); // Remove o aluno do contexto de persistência
        } else {
            System.out.println("Aluno não encontrado para remoção.");
        }

        transaction.commit();
        em.close();
    }
}
