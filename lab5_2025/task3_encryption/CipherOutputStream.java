import java.io.*;

public class CipherOutputStream extends FilterOutputStream {
    private char keyChar;

    public CipherOutputStream(OutputStream out, char keyChar) {
        super(out);
        this.keyChar = keyChar;
    }

    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        char encrypted = (char) (c + keyChar);
        out.write(encrypted);
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (byte value : b) {
            write(value);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(b[i]);
        }
    }
}
