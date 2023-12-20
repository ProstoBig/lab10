package lab10;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Клас, що представляє контактну інформацію про особу.
 */
public class Contact {
    private String name;
    private String birthDate;
    private String phoneNumber;

    /**
     * Конструктор для створення нового контакту.
     *
     * @param name        Ім'я контакту.
     * @param birthDate   Дата народження контакту.
     * @param phoneNumber Номер телефону контакту.
     */
    public Contact(String name, String birthDate, String phoneNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Метод для отримання імені контакту.
     *
     * @return Ім'я контакту.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для отримання дати народження контакту.
     *
     * @return Дата народження контакту.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Метод для отримання номера телефону контакту.
     *
     * @return Номер телефону контакту.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Метод для встановлення нового імені контакту.
     *
     * @param name Нове ім'я контакту.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод для встановлення нової дати народження контакту.
     *
     * @param birthDate Нова дата народження контакту.
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Метод для встановлення нового номера телефону контакту.
     *
     * @param phoneNumber Новий номер телефону контакту.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Метод для збереження контакту до файлу.
     * Відомості про контакт додаються до файлу у форматі: ім'я, дата народження, номер телефону.
     */
    public void saveContactToFile() {
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";
        String filePath = desktopPath + File.separator + "contacts.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + birthDate + "," + phoneNumber + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean isPhoneNumberExists(String filePath, String phoneNumber) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

            String[] contacts = fileContent.split("\n");
            for (String contact : contacts) {
                String[] contactDetails = contact.split(",");
                if (contactDetails.length >= 3 && contactDetails[2].equals(phoneNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isNameExists(String filePath, String name) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

            // Перевірка наявності імені у файлі
            return fileContent.contains(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

