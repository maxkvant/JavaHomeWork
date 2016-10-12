package task4;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws IOException, Maybe.MaybeException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
            PrintWriter writer = new PrintWriter("output.txt")) {
            String line;
            ArrayList<Integer> ints = new ArrayList<Integer>();
            while ((line = reader.readLine()) != null) {
                Maybe<Integer> curInt = Maybe.just(tryParse(line)).map(x -> x * x);
                if (curInt.isPresent()) {
                    ints.add(curInt.get());
                }
            }
            if (ints.size() == 0) {
                writer.write("null");
            } else {
                for (Integer x : ints) {
                    writer.println(x);
                }
            }
        }
    }

    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}