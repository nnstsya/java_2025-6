import java.util.HashMap;
import java.util.Scanner;

public class Translator {
    private HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public boolean addWord(String englishWord, String ukrainianWord) {
        String lowerCaseWord = englishWord.toLowerCase();
        if (dictionary.containsKey(lowerCaseWord)) {
            return false;
        }
        dictionary.put(lowerCaseWord, ukrainianWord);
        return true;
    }

    public String translateWord(String word) {
        String translation = dictionary.get(word.toLowerCase());
        return translation != null ? translation : "[" + word + "]";
    }

    public String translatePhrase(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder translation = new StringBuilder();
        StringBuilder missingWords = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!dictionary.containsKey(word.toLowerCase())) {
                if (missingWords.length() > 0) {
                    missingWords.append(", ");
                }
                missingWords.append(word);
            }
            translation.append(translateWord(word));
            if (i < words.length - 1) {
                translation.append(" ");
            }
        }

        if (missingWords.length() > 0) {
            System.out.println("Слова не знайдені у словнику: " + missingWords.toString());
        }

        return translation.toString();
    }

    public void populateDictionaryFromInput(Scanner scanner) {
        System.out.println("\n--- Наповнення словника ---");
        System.out.println("Введіть пари слів (англійське слово та його український переклад)");
        System.out.println("Для завершення введіть \"exit\"");

        while (true) {
            System.out.print("Англійське слово: ");
            String englishWord = scanner.nextLine().trim();

            if (englishWord.equalsIgnoreCase("exit")) {
                break;
            }

            if (englishWord.isEmpty()) {
                System.out.println("Слово не може бути пустим. Спробуйте ще раз.");
                continue;
            }

            System.out.print("Український переклад: ");
            String ukrainianWord = scanner.nextLine().trim();

            if (ukrainianWord.isEmpty()) {
                System.out.println("Переклад не може бути пустим. Спробуйте ще раз.");
                continue;
            }

            if (addWord(englishWord, ukrainianWord)) {
                System.out.println("Слово \"" + englishWord + "\" успішно додано до словника\n");
            } else {
                System.out.println("Слово \"" + englishWord + "\" вже існує у словнику. Спробуйте інше слово.\n");
            }
        }
    }

    public int getDictionarySize() {
        return dictionary.size();
    }

    public void displayDictionary() {
        if (dictionary.isEmpty()) {
            System.out.println("Словник порожній.");
            return;
        }

        System.out.println("\n--- Вміст словника ---");
        for (String englishWord : dictionary.keySet()) {
            System.out.println(englishWord + " -> " + dictionary.get(englishWord));
        }
        System.out.println("------------------------\n");
    }
}