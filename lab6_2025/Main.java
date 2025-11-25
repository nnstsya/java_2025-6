import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        System.out.println("--- Завантаження базового словника ---");
        initializeDictionary(translator);
        translator.displayDictionary();

        System.out.print("Чи бажаєте додати нові слова до словника? (так/ні): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("так") || response.equals("yes") || response.equals("y")) {
            translator.populateDictionaryFromInput(scanner);
            translator.displayDictionary();
        }

        while (true) {
            System.out.println("\n--- Переклад фрази ---");
            System.out.print("Введіть фразу англійською мовою (або 'exit' для виходу): ");
            String phrase = scanner.nextLine().trim();

            if (phrase.equalsIgnoreCase("exit")) {
                break;
            }

            if (phrase.isEmpty()) {
                System.out.println("Фраза не може бути пустою. Спробуйте ще раз.");
                continue;
            }

            String translation = translator.translatePhrase(phrase);
            System.out.println("Англійська: " + phrase);
            System.out.println("Українська: " + translation);
        }

        scanner.close();
    }

    private static void initializeDictionary(Translator translator) {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("good", "добрий");
        translator.addWord("morning", "ранок");
        translator.addWord("evening", "вечір");
        translator.addWord("night", "ніч");
        translator.addWord("day", "день");
        translator.addWord("yes", "так");
        translator.addWord("no", "ні");
    }
}
