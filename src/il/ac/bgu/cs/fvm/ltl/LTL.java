package il.ac.bgu.cs.fvm.ltl;

/**
 * A representation of an LTL formula as a parse tree.
 *
 * @param <L> The type of the atomic propositions.
 */
public abstract class LTL<L> {
	public static <L> And<L> and(LTL<L> l, LTL<L> r) {
		return new And<>(l, r);
	}

	public static <L> Until<L> until(LTL<L> l, LTL<L> r) {
		return new Until<>(l, r);
	}

	public static <L> Not<L> not(LTL<L> l) {
		return new Not<>(l);
	}

	public static <L> Next<L> next(LTL<L> l) {
		return new Next<>(l);
	}

	public static <L> TRUE<L> true_() {
		return new TRUE<>();
	}

}
