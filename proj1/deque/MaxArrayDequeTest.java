package deque;

import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * @author niannian
 * @date 2026/10/31 14:11
 * @description
 */
public class MaxArrayDequeTest {

    private static class FindMinInt implements Comparator<Integer> {
        @Override
        public int compare(Integer a,Integer b){
            return Integer.compare(b,a);
        }
    }

    private static class FindMaxLength implements Comparator<String>{
        @Override
        public int compare(String a,String b){
            return Integer.compare(a.length(),b.length());
        }
    }

    private static class FindMinLength implements Comparator<String>{
        @Override
        public int compare(String a,String b){
            return Integer.compare(b.length(),a.length());
        }
    }

    private static class FindMinWord implements Comparator<String>{
        @Override
        public int compare(String a,String b){
            return b.compareTo(a);
        }
    }


    @Test
    public void minIntTest(){
        Comparator<Integer> Min = new FindMinInt();
        MaxArrayDeque<Integer> a = new MaxArrayDeque<Integer>(Min);
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        Integer val = a.max();   // make sure val is not Null
        if (val == null)
            fail("val shouldn't be null");
        assertEquals(1,(int)val);
    }

    @Test
    public void minIntNullTest(){
        Comparator<Integer> Min = new FindMinInt();
        MaxArrayDeque<Integer> a = new MaxArrayDeque<Integer>(Min);
        Integer val = a.max();
        assertEquals(null,val);
    }

    @Test
    public void stringMinWordTest(){
        Comparator<String> min_Stingword = new FindMinWord();
        Comparator<String> maxLength = new FindMaxLength();
        MaxArrayDeque<String> a = new MaxArrayDeque<String>(maxLength);
        a.addFirst("ahello");
        a.addLast("niannian");
        assertEquals("ahello",a.max(min_Stingword));
        assertEquals("niannian",a.max());
    }



}
