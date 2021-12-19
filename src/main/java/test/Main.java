package test;

public class Main {
    public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        builder.start().wait(0);
    }
}
