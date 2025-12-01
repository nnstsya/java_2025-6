public class AnimalNotInCageException extends Exception {
    public AnimalNotInCageException(String message) {
        super(message);
    }

    public AnimalNotInCageException(String message, Throwable cause) {
        super(message, cause);
    }
}
