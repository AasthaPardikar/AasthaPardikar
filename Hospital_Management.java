import java.util.Scanner;

abstract class Patient {
    private String name;
    private int age;
    private String disease;

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public abstract void showRecord();
}

class GeneralPatient extends Patient {
    public GeneralPatient(String name, int age, String disease) {
        super(name, age, disease);
    }

    public void showRecord() {
        System.out.println("\nPatient Record:");
        System.out.println("Name    : " + getName());
        System.out.println("Age     : " + getAge());
        System.out.println("Disease : " + getDisease());
    }
}

public class Hospital_Management {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();

        Patient patient = new GeneralPatient(name, age, disease);
        patient.showRecord();

        scanner.close();
    }
}
