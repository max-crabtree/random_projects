public class Box {
    private String text;

    public Box(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        int boxWidth = text.length() + 2;
        String dashes = "";

        for (int i = 0; i < boxWidth; i++) {
            dashes += '-';
        }

        return String.format("+%s+\n| %s |\n+%s+", dashes, text, dashes);
    }
}
