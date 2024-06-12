package actividad.pruebasestructurales;

public class CountWords {
    private static final char[] list = {'s', 'r'};
    public int count(String str) {
        str += " ";
        int words = 0;
        char last = ' ';
        for (char c : str.toCharArray()) {
            if (!isLetter(c) && isInList(last)) {
                words++;
            }
            last = c;
        }
        return words;
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
    private boolean isInList(Character c){
        for (int i = 0; i < list.length; i++) {
            if (c == list[i]) {
                return true;
            }
        }
        return false;
    }
}