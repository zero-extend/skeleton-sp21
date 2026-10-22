package randomizedtest;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * @author niannian
 * @date 2026/10/22 15:34
 * @description
 */
public class TestThreeAddThreeRemoveJunit {

    @Test
    public void TestThreeAddsThreeRemoveJunit(){
        AListNoResizing<Integer> a1 = new AListNoResizing<Integer>();
        BuggyAList<Integer> B_a2 = new BuggyAList<Integer>();
        a1.addLast(4);
        B_a2.addLast(4);
        a1.addLast(5);
        B_a2.addLast(5);
        a1.addLast(6);
        B_a2.addLast(6);
        a1.removeLast();
        B_a2.removeLast();
        assertEquals(a1.get(0),B_a2.get(0));
        assertEquals(a1.get(1),B_a2.get(1));
        a1.removeLast();
        B_a2.removeLast();
        assertEquals(a1.get(0),B_a2.get(0));
        a1.removeLast();
        B_a2.removeLast();
        assertEquals(a1.get(0),B_a2.get(0));
    }

}
