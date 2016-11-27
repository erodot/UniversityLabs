package Config;

public final class Config {
    private Config(){}

    private final static String FILE_NAME_WITH_PATH = "txt/third.txt";

    public static String getFileNameWithPath(){
        return FILE_NAME_WITH_PATH;
    }
}
