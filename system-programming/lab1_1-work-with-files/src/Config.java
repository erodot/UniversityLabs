final class Config {
    private Config(){}

    private final static String FILE_NAME_WITH_PATH = "txt/verylongfile.txt";

    static String getFileNameWithPath(){
        return FILE_NAME_WITH_PATH;
    }
}