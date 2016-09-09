import Lab.*;

class Main {
    public static void main(String [] args) {
        // New instance of Lab.Lab
        Lab lab1 = new Lab();

        // The name of the file to open
        String fileName = Config.getFileNameWithPath();

        // Process file
        lab1.work(fileName);
    }
}
