import java.io.*;
import java.util.Base64;

public class CipherReaderFilter extends FilterReader {
    private char keyChar;
    private StringBuilder buffer;
    private String decodedData;
    private int position;

    public CipherReaderFilter(Reader in, char keyChar) {
        super(in);
        this.keyChar = keyChar;
        this.buffer = new StringBuilder();
        this.position = 0;
    }

    private void loadDecodedData() throws IOException {
        if (decodedData != null) {
            return;
        }

        char[] temp = new char[4096];
        int charsRead;
        while ((charsRead = in.read(temp)) != -1) {
            buffer.append(temp, 0, charsRead);
        }

        String encoded = buffer.toString();
        byte[] decoded = Base64.getDecoder().decode(encoded);
        decodedData = new String(decoded);
    }

    @Override
    public int read() throws IOException {
        loadDecodedData();
        if (position >= decodedData.length()) {
            return -1;
        }
        char encrypted = decodedData.charAt(position++);
        return (encrypted - keyChar) & 0xFF;
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        loadDecodedData();
        if (position >= decodedData.length()) {
            return -1;
        }
        int len = Math.min(cbuf.length, decodedData.length() - position);
        for (int i = 0; i < len; i++) {
            char encrypted = decodedData.charAt(position + i);
            cbuf[i] = (char) ((encrypted - keyChar) & 0xFF);
        }
        position += len;
        return len;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        loadDecodedData();
        if (position >= decodedData.length()) {
            return -1;
        }
        int charsToRead = Math.min(len, decodedData.length() - position);
        for (int i = 0; i < charsToRead; i++) {
            char encrypted = decodedData.charAt(position + i);
            cbuf[off + i] = (char) ((encrypted - keyChar) & 0xFF);
        }
        position += charsToRead;
        return charsToRead;
    }
}

abstract class FilterReader extends Reader {
    protected Reader in;

    public FilterReader(Reader in) {
        this.in = in;
    }

    public int read() throws IOException {
        return in.read();
    }

    public int read(char[] cbuf) throws IOException {
        return in.read(cbuf);
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        return in.read(cbuf, off, len);
    }

    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    public boolean ready() throws IOException {
        return in.ready();
    }

    public boolean markSupported() {
        return in.markSupported();
    }

    public void mark(int readAheadLimit) throws IOException {
        in.mark(readAheadLimit);
    }

    public void reset() throws IOException {
        in.reset();
    }

    public void close() throws IOException {
        in.close();
    }
}
