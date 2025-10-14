public class CuratorJournalEntry {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phone;
    private String address;

    public CuratorJournalEntry(String lastName, String firstName, String birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Запис:\n" +
                "Прізвище: " + lastName + "\n" +
                "Ім'я: " + firstName + "\n" +
                "Дата народження: " + birthDate + "\n" +
                "Телефон: " + phone + "\n" +
                "Адреса: " + address + "\n";
    }
}
