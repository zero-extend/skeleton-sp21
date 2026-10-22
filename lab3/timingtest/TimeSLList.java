package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> ops = new AList<>();
        SLList<Integer> test_time = new SLList<>();

        int first_current = 1000;
        int M = 10000; // count for call the getlast ,decided by lab handout
        int op_count = M;

        for (first_current = 1000;first_current <= 128000;first_current *= 2) {
            Ns.addLast(first_current);
            for (int i = 0; i < first_current;i++){
                test_time.addLast(1);
            }
            Stopwatch watch = new Stopwatch();
            for (int i = 0; i < M;i++){
            test_time.getLast();}
            double t = watch.elapsedTime();
            time.addLast(t);
            ops.addLast(op_count);
            test_time = new SLList<Integer>();
        }
        printTimingTable(Ns,time,ops);
    }

}
