package ejercicio3;
// dummy class for creating regular expressions


public class Test {

    Test test = new Test();
    int test_posint = 0;
    int test_int = 0;
    int test_float = 0;
    String test_ip = "";
    String test_splitter = "";
    public void reset() {
        test_posint = 0;
        test_int = 0;
        test_float = 0;
        test_ip = "";
        test_splitter = "";
    }
    public void showInt(int test_int) {
        System.out.println("test_int true for: " + test_int);
    }
    public void showFloat(float test_float) {
        System.out.println("test_float true for: " + test_float);
    }
    public void showPosInt(int test_posint) {
        System.out.println("test_posint true for: " + test_posint);
    }
    public void showIP(String test_ip) {
        System.out.println("test_ip_address true for: " + test_ip);
    }
    public void showSplitter(String test_splitter) {
        System.out.println("test_splitter true for: " + test_splitter);
    }
    public boolean test_posint(int number) {
        return number > 0;
    }

    public boolean test_int(int number) {
        return true;
    }
    public boolean test_float(float number) {
        return true;
    }
    public boolean test_ip_address(String ip) {
        return true;
    }
    public boolean test_splitter(String splitter) {
        return true;
    }
    
}
