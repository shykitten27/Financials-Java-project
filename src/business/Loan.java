/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author Donna Seidel
 */
public class Loan {
    private double loanamt, rate;
    private int term;
    private double mopmt;
    private double[] bbal, intchg, ebal; //arrays 
    private boolean built;

    public Loan(double a, double r, int t) { //class constructor
            this.loanamt = a;
            this.rate = r;
            this.term = t;
            this.mopmt = 0;
            built = false;
}
    
    public double getAmount() {
        return this.loanamt;
    }
    
    public int getTerm() {
        return this.term;
    }
    
    public void buildLoan() {
        double mRate = (this.rate / 12.0);
        double denom = Math.pow(1+mRate, this.term) - 1;
        this.mopmt = (mRate + mRate/denom) * this.loanamt;
        // instantiate array sizes
        bbal = new double[term];
        intchg = new double[term];
        ebal = new double[term];

        //calc arrays
        bbal[0] = this.loanamt;
        for (int i=0; i < this.term; i++){
            if (i > 0){
                bbal[i] = ebal[i-1];
            }
        intchg[i] = (bbal[i] * mRate);
        ebal[i] = (bbal[i] + intchg[i] - this.mopmt);
        }
        built = true;
    } //end of buildLoan
    
    public double getMoPmt() {
            if (!built) {
                buildLoan();
            }
            return this.mopmt;
    }
   
    public double getBeginBal(int term) {
        if (term <= 0 || term > this.term) {
            return -999;
        }
        if (!built){
            buildLoan();
        }
        return bbal[term-1];
    }
    
    public double getIntCharged(int term){
        if (term <= 0 || term > this.term) {
            return -999;
        }
        if (!built) {
            buildLoan();
        }
        return intchg[term-1];
    }
    
    public double getEndBal(int term) {
        if (term <= 0 || term > this.term){
            return -999;
        }
        if (!built){
            buildLoan();
        }
        return ebal[term-1];
    }
}
