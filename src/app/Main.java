package app;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Aluno");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Aluno:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        Date dataNascimento = dateFormat.parse(scanner.nextLine());
        System.out.print("Naturalidade: ");
        String naturalidade = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setCpf(cpf);
        aluno.setDataNascimento(dataNascimento);
        aluno.setNaturalidade(naturalidade);
        aluno.setEndereco(endereco);

        persistirAluno(aluno);

        System.out.println("Aluno cadastrado com sucesso!");

        scanner.close();
        emf.close();
    }

    private static void persistirAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        em.close();
    }
}
