import java.io.*;
import java.util.Base64;

public class CipherWriterFilter extends FilterWriter {
    private char keyChar;
    private StringBuilder buffer;

    public CipherWriterFilter(Writer out, char keyChar) {
        super(out);
        this.keyChar = keyChar;
        this.buffer = new StringBuilder();
    }

    @Override
    public void write(int c) throws IOException {
        char ch = (char) c;
        int encrypted = (ch + keyChar) & 0xFF;
        buffer.append((char) encrypted);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        for (char c : cbuf) {
            write(c);
        }
    }

    @Override
    public void write(String str) throws IOException {
        for (char c : str.toCharArray()) {
            write(c);
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void flush() throws IOException {
        if (buffer.length() > 0) {
            String encoded = Base64.getEncoder().encodeToString(buffer.toString().getBytes());
            out.write(encoded);
            buffer = new StringBuilder();
        }
        out.flush();
    }

    @Override
    public void close() throws IOException {
        flush();
        out.close();
    }
}

abstract class FilterWriter extends Writer {
    protected Writer out;

    public FilterWriter(Writer out) {
        this.out = out;
    }

    public void write(int c) throws IOException {
        out.write(c);
    }

    public void write(char[] cbuf) throws IOException {
        out.write(cbuf);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
    }

    public void write(String str) throws IOException {
        out.write(str);
    }

    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
