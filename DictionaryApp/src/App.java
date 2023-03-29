
public class App {
    public static void main(String[] args) throws Exception {
        DictionaryEN2VN.getInstance();
        DictionaryVN2EN.getInstance();
        new MainFrame();
    }
}
