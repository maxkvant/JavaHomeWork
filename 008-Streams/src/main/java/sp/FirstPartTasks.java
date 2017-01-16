package sp;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FirstPartTasks {

    private FirstPartTasks() {}

    // Список названий альбомов
    public static List<String> allNames(Stream<Album> albums) {
        return albums.map(Album::getName).collect(Collectors.toList());
    }

    // Список названий альбомов, отсортированный лексикографически по названию
    public static List<String> allNamesSorted(Stream<Album> albums) {
        return allNames(albums).stream().sorted().collect(Collectors.toList());
    }

    // Список треков, отсортированный лексикографически по названию, включающий все треки альбомов из 'albums'
    public static List<String> allTracksSorted(Stream<Album> albums) {
        return albums
                   .flatMap(album -> album.getTracks().stream())
                   .map(Track::getName)
                   .sorted()
                   .collect(Collectors.toList());
    }

    // Список альбомов, в которых есть хотя бы один трек с рейтингом более 95, отсортированный по названию
    public static List<Album> sortedFavorites(Stream<Album> albums) {
        return albums
                   .filter(album -> album
                                        .getTracks()
                                        .stream()
                                        .mapToInt(Track::getRating)
                                        .max()
                                        .orElse(0) > 95)
                   .sorted((album1, album2) -> album1.getName().compareTo(album2.getName()))
                   .collect(Collectors.toList());
    }

    // Сгруппировать альбомы по артистам
    public static Map<Artist, List<Album>> groupByArtist(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getArtist));
    }

    // Сгруппировать альбомы по артистам (в качестве значения вместо объекта 'Artist' использовать его имя)
    public static Map<Artist, List<String>> groupByArtistMapName(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(
                   Album::getArtist,
                   Collectors.mapping(Album::getName, Collectors.toList())
        ));
    }

    // Число повторяющихся альбомов в потоке
    public static long countAlbumDuplicates(Stream<Album> albums) {
        List<Album> list = albums.collect(Collectors.toList());
        return list.stream().count() - list.stream().distinct().count();
    }

    // Альбом, в котором максимум рейтинга минимален
    // (если в альбоме нет ни одного трека, считать, что максимум рейтинга в нем --- 0)
    public static Optional<Album> minMaxRating(Stream<Album> albums) {
        return albums.sorted((album1, album2) -> {
            return album1.getTracks().stream().mapToInt(Track::getRating).max().orElse(0) -
                   album2.getTracks().stream().mapToInt(Track::getRating).max().orElse(0);
        }).findFirst();
    }

    // Список альбомов, отсортированный по убыванию среднего рейтинга его треков (0, если треков нет)
    public static List<Album> sortByAverageRating(Stream<Album> albums) {
        return albums
                   .sorted((album1, album2) -> {
                       double a = album1.getTracks().stream().collect(Collectors.summarizingInt(Track::getRating)).getAverage();
                       double b = album2.getTracks().stream().collect(Collectors.summarizingInt(Track::getRating)).getAverage();
                       if (a == b) {
                           return 0;
                       }
                       return a > b ? -1 : 1;
                   })
                   .collect(Collectors.toList());
    }

    // Произведение всех чисел потока по модулю 'modulo'
    // (все числа от 0 до 10000)
    public static int moduloProduction(IntStream stream, int modulo) {
        return stream.reduce((a, b) -> (a * b) % modulo).orElse(1);
    }

    // Вернуть строку, состояющую из конкатенаций переданного массива, и окруженную строками "<", ">"
    // см. тесты
    public static String joinTo(String... strings) {
        return "<" + Stream.of(strings).map(x -> String.valueOf(x)).reduce((a, b) ->  a + ", " + b).orElse("") + ">";
    }

    // Вернуть поток из объектов класса 'clazz'
    public static <R> Stream<R> filterIsInstance(Stream<?> s, Class<R> clazz) {
       return s.filter(x -> clazz.isInstance(x)).map(clazz::cast);
    }
}