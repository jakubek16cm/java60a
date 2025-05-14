/*
Program sprawdza poprawność wpisywanego imienia. W przypadku wystąpienia spacji w imieniu, funkcja wyrzuca zdefiniowany wyjątek WrongStudentName, który jest wyłapywany w pętli głównej Commit6_0.
Poniższe zadania będą się sprowadzały do modyfikacji bazowego kodu. Proces modyfikacji ogólnie może wyglądać następująco:
• Ustalenie jaki błąd chcę się sprawdzić i wyłapać.
• Decyzja, czy użyje się własnej klasy wyjątku, czy wykorzysta już istniejące (np. Exception, IOException).
• Napisanie kodu sprawdzającego daną funkcjonalność. W przypadku warunku błędu wyrzucany będzie wyjątek: throw new WrongStudentName().
• W definicji funkcji, która zawiera kod wyrzucania wyjątku dopisuje się daną nazwę wyjątku, np. public static String ReadName() throws WrongStudentName.
• We wszystkich funkcjach, które wywołują powyższą funkcję także należy dopisać, że one wyrzucają ten wyjątek – inaczej program się nie skompiluje.
• W pętli głównej, w main’ie, w zdefiniowanym już try-catch dopisuje się Nazwę wyjątku i go obsługuje, np. wypisuje w konsoli co się stało.
*/

//Commit6_1. Na podstawie analogii do wyjątku WrongStudentName utwórz i obsłuż wyjątki WrongAge oraz WrongDateOfBirth. 
//Niepoprawny wiek – gdy jest mniejszy od 0 lub większy niż 100. Niepoprawna data urodzenia – gdy nie jest zapisana w formacie DD-MM-YYYY, np. 28-02-2023.
import java.util.Collection;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDate extends Exception { }  
class WrongMenu extends Exception{ }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wpisać wiek i date urodzenia");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
          
        int wybor = -1;
        while(true){
        try{
            wybor = scan.nextInt();
            if (wybor < 0 || wybor > 3){
                System.out.println("Błędny wybór");
                continue;
            }
            break;
        }
        catch(InputMismatchException e)
            {
            System.out.println("Błędny wybór, wybierz ponownie");
            scan.nextLine();
        }
        }
          return wybor;
        
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

    public static void exercise1() throws IOException, WrongStudentName {
        String name = ReadName();
        int age;
        while(true)
        {
        System.out.println("Podaj wiek: ");
            if(scan.hasNextInt()){
                age = scan.nextInt();
        if(age > 0 && age < 100){
            break;
        }
            else{
                System.out.println("Podaj poprawny wiek");
            }
            }
            else{
                scan.next();
                System.out.println("Podaj poprawny wiek");
                continue;
            }
        }
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYYY");
        String date = scan.nextLine();
        (new Service()).addStudent(new Student(name, age, date));
    }
    
        public static void exercise2() throws IOException {        
                Collection<Student> students = (new Service()).getStudents();
                for(Student current : students) {
                    System.out.println(current.ToString());
            }
        }


        
    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        Student wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
    public static void exercise4() throws IOException{
        scan.nextLine();
        
    }
}
