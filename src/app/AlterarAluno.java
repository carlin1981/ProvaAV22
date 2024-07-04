package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AlterarAluno {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aluno");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        // Solicita o ID do aluno a ser alterado
        System.out.print("Digite o ID do aluno que deseja alterar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Busca o aluno pelo ID
        Aluno aluno = buscarAlunoPorId(id);

        if (aluno != null) {
            // Exibe os dados atuais do aluno
            System.out.println("Dados atuais do aluno:");
            System.out.println(aluno);

            // Solicita as novas informações do aluno
            System.out.print("\nDigite o novo nome do aluno: ");
            aluno.setNome(scanner.nextLine());
            System.out.print("Digite o novo e-mail do aluno: ");
            aluno.setEmail(scanner.nextLine());
            System.out.print("Digite o novo CPF do aluno: ");
            aluno.setCpf(scanner.nextLine());
            System.out.print("Digite a nova Data de Nascimento do aluno (dd/MM/yyyy): ");
            aluno.setDataNascimento(dateFormat.parse(scanner.nextLine()));
            System.out.print("Digite a nova Naturalidade do aluno: ");
            aluno.setNaturalidade(scanner.nextLine());
            System.out.print("Digite o novo Endereço do aluno: ");
            aluno.setEndereco(scanner.nextLine());

            // Atualiza o aluno no banco de dados
            atualizarAluno(aluno);

            System.out.println("Aluno atualizado com sucesso!");
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

    private static void atualizarAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(aluno); // Atualiza o aluno no contexto de persistência
        transaction.commit();
        em.close();
    }
}
