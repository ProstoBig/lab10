package lab10;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Цей клас реалізує просту систему управління контактами за допомогою меню.
 */
public class menu {
    /**
     * Головний метод для запуску системи управління контактами.
     *
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--------------------------");
            System.out.println("1. Add a new contact");
            System.out.println("2. Edit a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Show contact list");
            System.out.println("5. Exit");
            System.out.println("--------------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    /**Код для додавання нового контакту**/
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter birth date:");
                    String birthDate = scanner.nextLine();

                    System.out.println("Enter phone number:");
                    String phoneNumber = scanner.nextLine();

                    Contact contact = new Contact(name, birthDate, phoneNumber);
                    contact.saveContactToFile();

                    System.out.println("Contact saved to file.");
                    break;
                case 2:
                    /**Код для редагування контакту**/
                    System.out.println("Enter the name of the contact to edit:");
                    String editName = scanner.nextLine();

                    try {
                        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\Олександр\\Desktop\\contacts.txt"));
                        String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

                        if (fileContent.contains(editName)) {
                            System.out.println("Enter new name:");
                            String newName = scanner.nextLine();

                            System.out.println("Enter new birth date:");
                            String newBirthDate = scanner.nextLine();

                            System.out.println("Enter new phone number:");
                            String newPhoneNumber = scanner.nextLine();

                            Contact updatedContact = new Contact(newName, newBirthDate, newPhoneNumber);

                            String updatedFileContent = fileContent.replaceAll(editName + ",.*", updatedContact.getName() + "," + updatedContact.getBirthDate() + "," + updatedContact.getPhoneNumber());
                            Files.write(Paths.get("C:\\Users\\Олександр\\Desktop\\contacts.txt"), updatedFileContent.getBytes());
                            System.out.println("Contact updated successfully.");
                        } else {
                            System.out.println("Contact not found.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    /**Код для видалення контакту**/
                    System.out.println("Enter the name of the contact to delete:");
                    String deleteName = scanner.nextLine();

                    try {
                        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\Олександр\\Desktop\\contacts.txt"));
                        String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

                        if (fileContent.contains(deleteName)) {
                            String updatedFileContent = fileContent.replaceAll(deleteName + ",.*\n", "");
                            Files.write(Paths.get("C:\\Users\\Олександр\\Desktop\\contacts.txt"), updatedFileContent.getBytes());
                            System.out.println("Contact deleted successfully.");
                        } else {
                            System.out.println("Contact not found.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    /**Код для показу списку контактів**/
                    try {
                        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\Олександр\\Desktop\\contacts.txt"));
                        String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

                        if (!fileContent.isEmpty()) {
                            System.out.println("Contact List:");
                            System.out.println(fileContent);
                        } else {
                            System.out.println("Contact list is empty.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Неправильний вибір.");
                    break;
            }
        }

        scanner.close();
    }
}
