import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CuratorJournalApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<CuratorJournalEntry> journal = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Журнал куратора — консольна програма\n");

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Додати новий запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вийти");

            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry();
                    break;
                case "2":
                    showEntries();
                    break;
                case "3":
                    System.out.println("Завершення програми.");
                    return;
                default:
                    return;
            }
        }
    }

    private static void addEntry() {
        String lastName = inputValidated("Прізвище", "[А-Яа-яЇїІіЄєҐґ\\-]{2,}");
        String firstName = inputValidated("Ім'я", "[А-Яа-яЇїІіЄєҐґ\\-]{2,}");
        String birthDate = inputDate("Дата народження (дд.мм.рррр)");
        String phone = inputValidated("Телефон (тільки цифри)", "\\d{9,}");
        String address = inputValidated("Адреса (вул. Приклад 1, кв. 1)", ".{5,}");

        CuratorJournalEntry entry = new CuratorJournalEntry(lastName, firstName, birthDate, phone, address);
        journal.add(entry);
        System.out.println("Запис додано!\n");
    }

    private static String inputValidated(String fieldName, String pattern) {
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            if (input.matches(pattern)) {
                return input;
            } else {
                System.out.println("Невірний формат. Повторіть спробу.");
            }
        }
    }

    private static String inputDate(String fieldName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Неправильна дата. Приклад правильного формату: 25.05.2006");
            }
        }
    }

    private static void showEntries() {
        if (journal.isEmpty()) {
            System.out.println("Журнал порожній.\n");
            return;
        }

        System.out.println("Всі записи в журналі:\n");
        for (CuratorJournalEntry entry : journal) {
            System.out.println(entry);
        }
    }
}
