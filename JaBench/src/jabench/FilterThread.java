/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabench;

/**
 *
 * @author sarin
 */
public class FilterThread extends Thread {
    
    private Integer[] result;
    private int il;
    private int start;
    private int tstep;

    public FilterThread(Integer[] result, int il, int start, int tstep) {
        reInit(result, il, start, tstep);
    }

    public final void reInit(Integer[] r, int il, int start, int tstep) {
        this.result = r;
        this.il = il;
        this.start = start;
        this.tstep = tstep;
    }
    
    

    @Override
    public void run() {
        for (int i = il + start; i < result.length; i += tstep) {
            for (int k = 0; k < il; k++) {
                final int rk = result[k];
                final int ri = result[i];
                final int dv = ri / rk;
                final int mlp = dv * rk;
                if (rk != 1 && mlp == ri) {
                    result[i] = 1;
                }
            }
        }
    }

}
