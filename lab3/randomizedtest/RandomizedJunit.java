package randomizedtest;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author niannian
 * @date 2026/10/22 15:52
 * @description
 */
public class RandomizedJunit {

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<Integer>();
        BuggyAList<Integer> B = new BuggyAList<Integer>();
        int N = 5000;

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.size(),B.size());
//                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size_b = B.size();
                assertEquals(size,size_b);
//                System.out.println("size: " + size);
            }
            else if (operationNumber == 2){
                // get last
                if (L.size() == 0) {
//                    System.out.println("error");
                    return;
                }int last = L.getLast();

                if (B.size() == 0) {
//                    System.out.println("error");
                    return;
                }int last_b = B.getLast();

                assertEquals(last,last_b);
//                System.out.println("get last("+last+")");
            }else if (operationNumber == 3){
                // remove last
                if (L.size() == 0) {
//                    System.out.println("error");
                    return;
                }
                int remove_last = L.removeLast();

                if (B.size() == 0) {
//                    System.out.println("error");
                    return;
                }
                int remove_last_b = B.removeLast();

                assertEquals(remove_last,remove_last_b);
//                System.out.println("remove last("+remove_last+")");
            }
        }
    }

}
