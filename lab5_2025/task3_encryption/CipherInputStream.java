import java.io.*;

public class CipherInputStream extends FilterInputStream {
    private char keyChar;

    public CipherInputStream(InputStream in, char keyChar) {
        super(in);
        this.keyChar = keyChar;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b == -1) {
            return -1;
        }
        char c = (char) b;
        char decrypted = (char) (c - keyChar);
        return decrypted;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int len = in.read(b);
        if (len == -1) {
            return -1;
        }
        for (int i = 0; i < len; i++) {
            b[i] = (byte) ((char) b[i] - keyChar);
        }
        return len;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead = in.read(b, off, len);
        if (bytesRead == -1) {
            return -1;
        }
        for (int i = off; i < off + bytesRead; i++) {
            b[i] = (byte) ((char) b[i] - keyChar);
        }
        return bytesRead;
    }
}
