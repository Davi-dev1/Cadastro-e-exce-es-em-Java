import br.com.dio.dao.UserDao;
import model.MenuOption;
import model.UserModel;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final  static UserDao dao = new UserDao();

    private final static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {



      while(true){
          System.out.println("Bem vindo ao cadastro de usuarios selecione a operação desejada : ");
          System.out.println("1 - Cadastrar   ");
          System.out.println("2 - Atualizar ");
          System.out.println("3 - Excluir ");
          System.out.println("4 - Buscar por identificador ");
          System.out.println("5 - Listar    ");
          System.out.println("6 - Sair");
          var userInput = scanner.nextInt();
          var selectedOPtion = MenuOption.values()[userInput -1]; //são 6 escolhas entao ele vai ter que decrementar para poder cair na opção que o usuário selecionou
       switch(selectedOPtion){
           case SAVE ->{

              var user = dao.save(requestTosave());

               System.out.printf("Usuário cadastrado com sucesso %s",user);
           }
           case UPDATE ->{
             var userUpdated =  dao.update(requestToUpdate());
               System.out.printf("Informações do Usuário atualizadas com sucesso %s" ,userUpdated);
           }
           case DELETE ->{
               dao.delete(requestId());
               System.out.println("Usuário excluido com sucesso");
           }
           case FIND_BY_ID -> {
               var id = requestId();
            var userFind = dao.findById(id);
               System.out.printf("Usuário encontrado com sucesso %s",userFind);
               System.out.printf("Usuario com id %s",id);
           }
           case FIND_ALL ->{

               var users = dao.findAll();
               System.out.println("Usuários cadastrados ");
               System.out.println("======================");
               users.forEach(System.out::println);
               System.out.println("======================");
           }
           case EXIT -> System.exit(0);
       }

      }
    }

    private static long requestId(){
        System.out.println("Informe o identificador do usuário");
        return scanner.nextLong();
    }

    private static UserModel requestTosave(){
        System.out.println("Informe o nome do usuário");
        var name = scanner.next();
        System.out.println("Informe o email do usuário");
        var email =  scanner.next();
        System.out.println("Informe a data de nascimento  do usuário (dd//MM/yyyy)");
       var BirthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM//yyyy");
        var Birthday = OffsetDateTime.parse(BirthdayString,formatter);
       return  new UserModel(0, name, email, Birthday); // vai retornar os dados obtidos
    }
    private static UserModel requestToUpdate(){
        System.out.println("Informe o identificador do usuário");
        var id = scanner.nextLong();
        System.out.println("Informe o nome do usuário");
        var name = scanner.next();
        System.out.println("Informe o email do usuário");
        var email =  scanner.next();
        System.out.println("Informe a data de nascimento  do usuário (dd//MM/yyyy)");
        var BirthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM//yyyy");
        var Birthday = OffsetDateTime.parse(BirthdayString,formatter);
        return  new UserModel(id, name, email, Birthday); // vai retornar os dados obtidos
    }


}