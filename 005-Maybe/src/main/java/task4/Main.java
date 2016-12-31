package task4;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws IOException, Maybe.MaybeException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
            PrintWriter writer = new PrintWriter("output.txt")) {
            String line;
            ArrayList<Integer> ints = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                Maybe<Integer> curInt = new Maybe<>(tryParse(line)).map(x -> x * x);
                if (curInt.isPresent()) {
                    ints.add(curInt.get());
                }
            }
            if (ints.size() == 0) {
                writer.write("null");
            } else {
                ints.forEach(writer::println);
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