/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author sarin
 */
public class JaBench {

    public static final Integer[] INIT_PRIMES = new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
    //public static final Integer[] INIT_PRIMES = new Integer[]{2, 3, 5, 7};//, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //System.out.println(INIT_PRIMES.length);

        final int mc = 7;

        for (int tc = 1; tc < 33; tc *= 2) {
            System.out.print(tc);
            Integer[] m = INIT_PRIMES;
            m = test(mc, m, tc);
            System.out.println();
        }
    }

    private static Integer[] test(final int mc, Integer[] m, final int tc) throws InterruptedException {
        System.out.print('\t');
        System.out.print(mc);
        System.out.print('\t');

        for (int i = 0; i < mc; i++) {
            long st = System.currentTimeMillis();
            System.out.print(m.length);
            m = multiple(m, tc);
            long et = System.currentTimeMillis() - st;
            
//            List<Integer> ml = new ArrayList<>(new HashSet<Integer>(Arrays.asList(m)));
//            Collections.sort(ml);
            //System.out.println(Arrays.toString(ml.toArray()));
            
            
            System.out.print(',');
            System.out.print(et);
            System.out.print(' ');
        }

        return m;
    }

    private static Integer[] multiple(Integer[] init, int i) throws InterruptedException {
        final Integer[] result = new Integer[init.length * 2];
        System.arraycopy(init, 0, result, 0, init.length);
        int n = init[init.length - 1];
        for (int j = init.length; j < result.length; j++) {
            n++;
            result[j] = n;
        }
        Thread[] threads = new Thread[i];
        final int il = init.length;
        final int tstep = i;
        for (int j = 0; j < threads.length; j++) {
            final int start = j;
//            threads[j] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = il + start; i < result.length; i += tstep) {
//                        for (int k = 0; k < il; k++) {
//                            final int rk = result[k];
//                            final int ri = result[i];
//                            final int dv = ri / rk;
//                            final int mlp = dv * rk;
//                            if (rk != 1 && mlp == ri) {
//                                result[i] = 1;
//                            }
//                        }
//                    }
//                }
//            });
            threads[j] = new FilterThread(result, il, start, tstep);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        return result;

    }

}
