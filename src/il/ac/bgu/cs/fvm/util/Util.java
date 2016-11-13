package il.ac.bgu.cs.fvm.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility methods helping the course staff implementation.
 * <p>
 */
public class Util {

    public static <T> Set<List<T>> getCartesianProduct(Set<T> s1, Set<T> s2) {
        return s1.stream().flatMap((T e1) -> s2.stream().map((T e2)
                -> (List<T>) Arrays.asList(e1, e2))).collect(Collectors.toSet());
    }

    public static <T1, T2> Set<Pair<T1, T2>> getPairs(Set<T1> s1, Set<T2> s2) {
        Stream<Pair<T1,T2>> str = s1.stream().flatMap(e1 -> s2.stream().map(e2 -> new Pair<>(e1, e2))); //
        return str.collect(Collectors.toSet());
    }

}
