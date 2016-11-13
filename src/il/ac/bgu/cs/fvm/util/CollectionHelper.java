package il.ac.bgu.cs.fvm.util;

import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Some methods to support literal collections.
 *
 * @author michael
 */
public class CollectionHelper {

    public static PGTransition pgtransition(Object from, String cond, Object action, Object to) {
        return new PGTransition(from, cond,action, to);
    }

    public static Transition transition(Object from, Object action, Object to) {
        return new Transition(from, action, to);
    }

    public static Pair p(Object x, Object y) {
        return new Pair(x, y);
    }

    public static  Set set(Object... ses) {
        return new HashSet(Arrays.asList(ses));
    }

    public static  List seq(Object... ses) {
        return Arrays.asList(ses);
    }

    public static  Map map(Pair... pairs) {
        return IntStream.range(0, pairs.length)
                .mapToObj(i -> pairs[i])
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    public static  Stream product(Set s1, Set s2) {
        return s1.stream().flatMap(e1 -> s2.stream().map(e2
                -> new Pair(e1, e2)));
    }
}
