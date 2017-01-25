/*
 *  (C) Michael Bar-Sinai
 */
package il.ac.bgu.cs.fvm.util;

import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author michael
 */
public class ProgramGraphGraphvizPainter<L,A> {

    
    StringBuffer bf = new StringBuffer();
    
    public String makedotCode( ProgramGraph<L,A> pg ) {
        
        bf.append( " digraph pg {\n");
        bf.append("node [shape=box, fontname=\"Courier\"];\n");
        
        Map<L,String> ids = new HashMap<>();
        AtomicInteger cnt = new AtomicInteger(0);
        pg.getLocations().forEach( l -> ids.put(l, "l"+cnt.incrementAndGet()) );
        
        pg.getLocations().forEach( l -> {
            bf.append(ids.get(l)).append("[label=\"").append(locationTitle(l)).append("\"]\n");
            if ( pg.getInitialLocations().contains(l) ) {
                String startId = "start_" + ids.get(l);
                bf.append(startId).append( "[label=\"\", shape=none];\n");
                bf.append(startId).append("->").append(ids.get(l)).append(";\n");
                bf.append("{rank=source; ").append(ids.get(l)).append("; start_").append(ids.get(l)).append("}\n");
            }
        });
        
        pg.getTransitions().stream().forEach( t -> {
            bf.append( ids.get(t.getFrom()) )
              .append("->")
              .append( ids.get(t.getTo()) )
              .append(" [label=\"")
              .append( t.getCondition())
              .append( ":" )
              .append( actionTitle(t.getAction()) )
              .append("\"]\n");
            
        });
        
        bf.append( "}");
        
        return bf.toString();
    }
    
    
    private String locationTitle( L loc ) {
        return loc.toString();
    }
    
    private String actionTitle( A act ) {
        return act.toString();
    }
    
}
