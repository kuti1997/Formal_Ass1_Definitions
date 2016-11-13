package il.ac.bgu.cs.fvm.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Automaton<STATE> {

	private final Map<STATE, Map<Set<String>, Set<STATE>>>	transitions;
	private final Set<STATE>                                initial;
	private final Set<STATE>                                accepting;

	public Automaton() {
		transitions = new HashMap<>();
		initial = new HashSet<>();
		accepting = new HashSet<>();
	}

	public void addTransition(STATE source, Set<String> symbol, STATE destination) {
		if (!transitions.containsKey(source))
			addState(source);

		if (!transitions.containsKey(destination))
			addState(destination);

		Set<STATE> set = transitions.get(source).get(symbol);
		if (set == null) {
			set = new HashSet<>();
			transitions.get(source).put(symbol, set);
		}
		set.add(destination);
	}

	public void addState(STATE s) {
		if (!transitions.containsKey(s))
			transitions.put(s, new HashMap<>());
	}

	public Iterable<STATE> nextStates(STATE source, Set<String> symbol) {
		if (!transitions.containsKey(source))
			throw new IllegalArgumentException();
		else
			return transitions.get(source).get(symbol);
	}

	public void setInitial(STATE s) {
		addState(s);
		initial.add(s);
	}

	public Iterable<STATE> getInitialStates() {
		return initial;
	}

	public void setAccepting(STATE s) {
		addState(s);
		accepting.add(s);
	}

	public Iterable<STATE> getAcceptingStates() {
		return accepting;
	}

}
