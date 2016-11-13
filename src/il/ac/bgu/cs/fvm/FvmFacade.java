package il.ac.bgu.cs.fvm;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.transitionsystem.AlternatingSequence;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;
import il.ac.bgu.cs.fvm.util.Pair;
import java.util.Map;

/**
 * Interface for the entry point to the solution for exercise 1.
 * <p>
 * More about facade: {@linkplain http://www.vincehuston.org/dp/facade.html}.
 */
public interface FvmFacade {

    @SuppressWarnings("unchecked")
    static FvmFacade createInstance() {
        try {
            return ((Class<FvmFacade>) Class.forName("il.ac.bgu.cs.fvm.impl.FvmFacadeImpl")).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException("Cannot instantiate from implementation class: " + ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Cannot find implementation class: " + ex.getMessage(), ex);
        }
    }

    <S, A, P> TransitionSystem<S, A, P> createTransitionSystem();

    <S, A, P> boolean isActionDeterministic(TransitionSystem<S, A, P> ts);

    <S, A, P> boolean isAPDeterministic(TransitionSystem<S, A, P> ts);

    <S, A, P> boolean isExecution(TransitionSystem<S, A, P> ts,
                                  AlternatingSequence<S, A> e);

    <S, A, P> boolean isExecutionFragment(TransitionSystem<S, A, P> ts,
                                          AlternatingSequence<S, A> e);

    <S, A, P> boolean isInitialExecutionFragment(TransitionSystem<S, A, P> ts,
                                                 AlternatingSequence<S, A> e);

    <S, A, P> boolean isMaximalExecutionFragment(TransitionSystem<S, A, P> ts,
                                                 AlternatingSequence<S, A> e);

    <S, A> boolean isStateTerminal(TransitionSystem<S, A, ?> ts, S s);

    <S> Set<S> post(TransitionSystem<S, ?, ?> ts, S s);

    <S> Set<S> post(TransitionSystem<S, ?, ?> ts, Set<S> c);

    <S, A> Set<S> post(TransitionSystem<S, A, ?> ts, S s, A a);

    <S, A> Set<S> post(TransitionSystem<S, A, ?> ts, Set<S> c, A a);

    <S> Set<S> pre(TransitionSystem<S, ?, ?> ts, S s);

    <S> Set<S> pre(TransitionSystem<S, ?, ?> ts, Set<S> c);

    <S, A> Set<S> pre(TransitionSystem<S, A, ?> ts, Set<S> c, A a);

    <S, A> Set<S> pre(TransitionSystem<S, A, ?> ts, S s, A a);

    <S, A> Set<S> reach(TransitionSystem<S, A, ?> ts);

    /**
     * Compute the synchronous product of two transition systems.
     *
     * @param <S1> Type of states in the first system.
     * @param <S2> Type of states in the first system.
     * @param <A>  Type of actions (in both systems).
     * @param <P>  Type of atomic propositions (in both systems).
     * @param ts1  The first transition system.
     * @param ts2  The second transition system.
     *
     * @return A transition system that represents the product of the two.
     */
    <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(
            TransitionSystem<S1, A, P> ts1, TransitionSystem<S2, A, P> ts2);

    /**
     * Compute the synchronous product of two transition systems.
     *
     * @param <S1>               Type of states in the first system.
     * @param <S2>               Type of states in the first system.
     * @param <A>                Type of actions (in both systems).
     * @param <P>                Type of atomic propositions (in both systems).
     * @param ts1                The first transition system.
     * @param ts2                The second transition system.
     * @param handShakingActions Set of actions both systems perform together.
     *
     * @return A transition system that represents the product of the two.
     */
    <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(
            TransitionSystem<S1, A, P> ts1, TransitionSystem<S2, A, P> ts2,
            Set<A> handShakingActions);

    <L, A> ProgramGraph<L, A> createProgramGraph();

    <L1, L2, A> ProgramGraph<Pair<L1, L2>, A> interleave(ProgramGraph<L1, A> pg1,
                                                         ProgramGraph<L2, A> pg2);

    TransitionSystem<Pair<List<Boolean>, List<Boolean>>, List<Boolean>, Object> 
        transitionSystemFromCircuit(Circuit c);

    <L, A> TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystemFromProgramGraph(
            ProgramGraph<L, A> pg, Set<ActionDef> actionDefs,
            Set<ConditionDef> conditionDefs);

    <Sts, Saut, A, P> TransitionSystem<Pair<Sts, Saut>, A, P> product(
            TransitionSystem<Sts, A, P> ts, Automaton<Saut> aut);

    TransitionSystem transitionSystemFromChannelSystem(ChannelSystem cs);

    ProgramGraph programGraphFromNanoPromela(String filename) throws Exception;

    ProgramGraph programGraphFromNanoPromelaString(String nanopromela) throws Exception;

    ProgramGraph programGraphFromNanoPromela(InputStream inputStream) throws Exception;

}
