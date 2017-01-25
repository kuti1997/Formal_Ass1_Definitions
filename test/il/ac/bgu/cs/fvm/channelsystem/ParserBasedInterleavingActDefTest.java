package il.ac.bgu.cs.fvm.channelsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author michael
 */
public class ParserBasedInterleavingActDefTest extends TestCase {
    
    public static void main(String[] args) {
        new ParserBasedInterleavingActDefTest().parserTest();
    }
    
    @Test
    public void parserTest() {
        
        String code = "_C!0";
        ParserBasedInterleavingActDef sut = new ParserBasedInterleavingActDef();
        
        Map<String,Object> initialEval = new HashMap<>();
        initialEval.put("_C", new Vector<>() );
        Map<String,Object> next = sut.effect(initialEval, code);
        
        System.out.println(next);
        
        assertEquals("a", "a");
    }
    
}
