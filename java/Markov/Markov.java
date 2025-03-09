import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;

/* 
 * Notes:
 *
 * - Array of Structs
 * - Split functions into own method
 * - HashMap<String, [method]> chain; ...
 * - ^ from Step chain[];...
 * - the depth?
 * - fuzziness meter (through use of Scanner class? to be used as a user
 *   program almost)
 *
 * 1: Compress Source
 * 2: Put through Markov
 * 3: Decompress Markov
 * 4: Output
 * (basically)
 *
 * So I need to get the Markov working (with the Array of Struct, Step class,
 * etc...), and get it then talking to the Compress class. It will take the
 * compressed source and generate a probabilistic output (map?) that then gets
 * fed into a decompress class (back in the compress class). This is then
 * outputted... hopefully!
 *
 * Also consider:
 * How is the program to be used? ie. how can users use it, what does it
 * respond to, diagnostics?, why do it in this way?.
 * What data structures are being used for the Markov Model? Any rejected?
 * Why?
 * Testing process! When and why, and what was outputted?
 *
 * Testing @ 19/8:
 * Input: "\nGood evening, my name is Jon."
 * Output: "ooee emgngysn  sieegnmem inmedme.G   iJ nm,,a  ,Gdmo.m, eenoJ
 *            m,ne oo,dg ed eiey"
 * I performed this test here to see how my Step class was working in my
 * Markov class. Obviously, it's not too good; the output was not what I
 * expected.
 *
 * Testing @ 27/8:
 * Input: "\nGood evening, my name is Jon."
 * Output: "nodmd geo odo oogGe ioGeeJed.vga,,das"
 * I am still receiving the same very random strings. I believe it is
 * something to do with the generate method in the Step class.
 */

/** Generate Probabilistic Text.
 *
 * Takes in compressed source from the Compress class. This is then added to
 * the Markov chain (Hidden Markov Model), and the output is generated through 
 * the Step class. It is then outputted with a variable amount of 'depth' and 
 * complexity.
 */
public class Markov {
    private static boolean assertsEnabled = false;
    static {
        assert assertsEnabled = true;
    }

    /** How Complex (Deep?) the Markov Process Goes. */
    public static final int DEPTH = 5;

    /** Encapsulated Class.
     *
     * Holds the Markov generation and token logic.
     */
    class Step {

        /** Token Frequency */
        private int freq;

        /** Next Token Array */
        private int[] next; //next token => probability 

        /** Initialise Step Variables.
         *
         * @param limit of tokens
         */
        Step(int limit) { //constructor
            freq = 0;
            next = new int[limit];
        }

        /** Add Char to Generate Probabilistic Output.
         *
         * @param c char to be added
         */
        void addChar(char c) {
            /* add char to array to generate probabilistic output */
            freq += 1; //freq of prev?
            next[c] += 1; //freq of next char
        }

        /** Generate Markov Output.
         *
         * @return probabilistic char
         */
        char generate() { //generate probability map
            int choose = (int)(Math.random() * freq);
            char c = 0; //initially at first char
            while (choose >= next[c]) { //while freq greater than...
                choose -= next[c]; //random - char freq 
                c += 1; //go to next char... etc.
            }
            return c;
        }
    }

    /** Temporary LIMIT Placeholder Value. */
    int temporary = 512;

    /** HashMap for Markov Chain. */
    Map<String, Step> chain = new HashMap<String, Step>();

    /** Place Values into Markov Chain.
     *
     * This method sourced from Duane with added help from Eli as well.
     *
     * @param source compressed source
     */
    public Markov(String source) {
        for (int i = 1; i < source.length(); i++) {
            char current = source.charAt(i);
            for (int j = 0; j < i && j < DEPTH; j++) {
                /* previous = [i - 1 - j] (through to) => [i] */
                String previous = source.substring(i - 1 - j, i);
                Step mar = chain.get(previous); //get previous Step value
                if (chain.get(previous) == null) { //if it is null...
                    mar = new Step(temporary); //make new previous object
                    chain.put(previous, mar); //put new previous value in map
                }
                mar.addChar(current); //add current char to Step
            }
        }
    }

    /** Output Markov Generation.
     *
     * @param count how long should the generation be (in compressed tokens)
     * @return Compressed Markov string
     */
    String output(int count) { //output Markov process
        StringBuffer sb = new StringBuffer();
        String previous = "\n";
        for (int i = 0; i < count; i++) {
            Step out = chain.get(previous); //gets previous value
            for (int j = 0; out == null && j < DEPTH; j++) {
                /* get previous from start of j? */
                out = chain.get(previous.substring(j));
            }
            char current = out.generate(); //current = probabilistically generated char
            sb.append(current); //add current char to output
            previous += current; //update previous value
        }
        return sb.toString();
    }
}

/** Compresses the Source Text Through Tokenisation.
 *
 * Takes in source text defined in main class. Scans through to find most
 * common digram, and finds unused characters as well. The most common digrams
 * are replaced with the unused characters in the form of Tokens, which allows
 * for decompression as well.
 * <p>
 * This class was sourced from Duane.
 */
class Compress {
    private static boolean assertsEnabled = false;
    static {
        assert assertsEnabled = true;
    }

    /** Encapsulated Class to Hold Tokens. 
     *
     * This class isolates the Tokens and allows for decompression.
     */
    class Token {

        /** Frequency of Token. */
        int freq;

        /** The Token. */
        String to;

        /** Initialise Token Variables.
         *
         * @param to the token string
         */
        Token(String to) {
            this.to = to;
            this.freq = 1;
        }
    }

    /** Original Length of Source Text. */
    public final int originalLength;

    /** The Source Text */
    String source;

    /** Token Class Array Object. */
    Token[] map;

    /** Previous Token. */
    int lastToken;

    /** Initialise the Compress Variables.
     *
     * @param source the source text
     * @param limit the maximum number of tokens
     */
    public Compress(String source, int limit) {
        originalLength = source.length();
        this.source = source;
        map = new Token[limit];

        /* Build Frequency Map */
        lastToken = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (map[c] == null) {
                map[c] = new Token("" + c);
                if (c > lastToken) {
                    lastToken = c;
                }
            } else {
                map[c].freq += 1;
            }
        }

        if (assertsEnabled) {
            System.err.println("Source Document " + originalLength + " characters long");
        }
    }

    /** Returns Compressed Source.
     *
     * @return compressed source
     */
    public String returnSource() {
        return source;
    }

    /** Return Unused Chars in Source.
     *
     * @return char if unused
     */
    public char unused() {
        for (int i = 0; i < map.length; i++) {
            if (map[i] == null) {
                return (char)i;
            }
        }
        return (char)-1;
    }

    /** Decompress Compressed Source.
     *
     * This method was sourced from Eli.
     *
     * @param source compressed source
     * @return decompressed source
     */
    public String decompress(String source) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char symb = source.charAt(i); //symb = Token at i
            if (symb != 0) { //if Token exists...
                sb.append(map[symb].to); //append original char
            } else { //if no Token...
                sb.append(symb); //append symb at i
            }
        }
        return sb.toString();
    }

    /** Replace Pair with Unknown Char.
     *
     * @param symb unknown char
     * @param token token to be replaced
     */
    public void replace(char symb, Token token) {
        /* Perform Replacement */
        source = source.replace(token.to, "" + symb);
        map[symb] = token;
        if (symb > lastToken) {
            lastToken = symb;
        }

        char fst = token.to.charAt(0);
        char snd = token.to.charAt(1);

        /* Update `to` to final value (not simple digram) */
        token.to = map[fst].to + map[snd].to;

        if (assertsEnabled) {
            String fmt = "\\u%04X[%c] -> {%s}\t(%d replaced, %d remaining, %.3f%% reduction, %4.1f%% compression)";
            char repr = (char)symb;
            if (Character.isISOControl(symb)) {
                repr = 0xFFFD; // unknown replacement character
            }
            System.err.println(String.format(fmt, (int)symb, repr, token.to,
                        token.freq, source.length(),
                        (float)token.freq / source.length() * 100,
                        (float)source.length() / originalLength * 100));
        }

        /* Decrease frequencies by new token */
        map[fst].freq -= token.freq;
        if (map[fst].freq == 0) {
            map[fst] = null;
        }
        map[snd].freq -= token.freq;
        if (map[snd].freq == 0) {
            map[snd] = null;
        }
    }

    /** Build Digram Frequency.
     *
     * @return most frequent digram
     */
    public Token freqDigram() {
        /* Only allocate up to the largest token we have so far */
        int[][] digramCount = new int[lastToken + 1][lastToken + 1];

        /* Build Digram Count */
        char p = source.charAt(0);
        for (int i = 1; i < source.length(); i++) {
            char c = source.charAt(i);
            if (p == ' ' || p == '\n' || c == ' ' || c == '\n') {
                /* Ignore digrams with space or newline characters */
            } else {
                digramCount[p][c] += 1;
            }
            p = c;
        }

        /* Find Frequent Digram */
        String digram = null;
        int most = 0;
        for (int i = 0; i < digramCount.length; i++) {
            for (int j = 0; j < digramCount[i].length; j++) {
                if (digramCount[i][j] > most) {
                    most = digramCount[i][j];
                    digram = "" + (char)i + (char)j;
                }
            }
        }

        Token tok = new Token(digram);
        tok.freq = most;
        return tok;
    }

    /** Output Tokenised Text.
     *
     * @return colourised token map
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        int wrap = 0;
        for (int i = 0; i < source.length(); i++) {
            String repl = map[source.charAt(i)].to;
            wrap += repl.length();
            if ((wrap >= 80 && repl.equals(" ")) || repl.equals("\n")) {
                sb.append("\n");
                wrap = 0;
            }
            /* Colourise per Token */
            sb.append("[3" + (1+(i%4)) + "m" + repl);
        }
        return sb.toString();
    }
}

/** Format Output and Compile Code.
 *
 * Runs the code from the above classes, and formats accordingly.
 */
class Main {
    private static boolean assertsEnabled = false;
    static {
        assert assertsEnabled = true;
    }

    /** Source String */
    public static final String DEFAULT_FILENAME = "pandp_clean.txt";

    /** Runs code, reads and processes file.
     *
     * @param args String array type, stores arguments
     * @throws IOException if there is an Input/Output issue
     */
    public static void main(String[] args) throws IOException {
        System.err.println("Hello World!");

        /* Select File */
        String filename = DEFAULT_FILENAME;
        if (args.length >= 1) {
            filename = args[0];
        }

        /* Define Token Limit */
        int limit = 256;
        if (args.length >= 2) {
            limit = Integer.parseInt(args[1]);
        }

        /* Read File Contents */
        String original = Files.readString(Path.of(filename), Charset.forName("US-ASCII"));

        /* Process File */
        Compress comp = new Compress(original, limit);
        original = null; // We discard the original string to free up memory

        char next = comp.unused();
        while (next != (char)-1) {
            comp.replace(next, comp.freqDigram());
            next = comp.unused();
        }

        if (assertsEnabled) {
            /* Sourced from Eli */
            System.err.println("Testing Mode Active");

            String source = comp.returnSource(); //return compressed source
            Markov mk = new Markov(source);
            String gen = mk.output(80);
            String decompressed = comp.decompress(gen); //decompress Markov string
            String newSource = comp.decompress(source); //decompress source
            //System.out.println(newSource); //print decompressed source
            System.out.println(decompressed); //print decompressed Markov string
            return;
        }

        if (!assertsEnabled) {
            /* Don't output the text if I'm testing */
            output("" + comp, false);
        }
    }

    /** Format Output.
     *
     * @param text to be formatted
     * @param split the strings lines?
     */
    public static void output(String text, boolean split) {
        String[] lines = text.split("\\R");

        /* Write out file by lines */
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            /* Break long lines */
            while (split && line.length() > 80) {
                int splitAt = line.lastIndexOf(' ', 79);

                /* Couldn't find a space before 80, so find the next one */
                if (splitAt == -1) {
                    splitAt = line.indexOf(' ');
                }

                /* No space character at all, so give up breaking and output
                 * the line as is */
                if (splitAt == -1) {
                    break;
                }

                System.out.println(line.substring(0, splitAt));
                line = line.substring(splitAt + 1); // +1 skips the space
            }

            System.out.println(line);
            //System.out.println(); // extra blank line between source lines
        }
    }
}

