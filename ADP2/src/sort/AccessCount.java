package sort;

public class AccessCount {

    private int read = 0;
    private int write = 0;
    
    public AccessCount(int readAccessCount, int writeAccessCount) {
        read = readAccessCount;
        write = writeAccessCount;
    }

    public int getRead() {
        return read;
    }

    public int getWrite() {
        return write;
    }
    
    public int getAccessCount() {
        return write + read;
    }
}
