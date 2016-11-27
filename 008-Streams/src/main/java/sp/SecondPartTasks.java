package sp;

import java.util.List;
import java.util.Map;
import java.util.Random;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        throw new UnsupportedOperationException();
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        int n = 1 << 20;
        Random rnd = new Random();

        double res = rnd.doubles()
                  .map(x -> {double a = rnd.nextDouble(); return a * a + x * x > 1 ? 0 : 1;})
                  .limit(n)
                  .summaryStatistics()
                  .getAverage();
        return res;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.keySet().stream().
                max((name1, name2) ->
                        compositions.get(name1).stream().mapToInt(String::length).sum() -
                        compositions.get(name2).stream().mapToInt(String::length).sum())
                .orElse("");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        throw new UnsupportedOperationException();
    }
}
