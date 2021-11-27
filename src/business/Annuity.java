
package business;

/**
 *
 * @author Donna Seidel
 */
public class Annuity {
    private double amt, rate;
    private int term;
    private double [] bbal, intearn, ebal; //arrays beg balance, int earned, end balance
    private boolean built;

    public Annuity() { //class constructor
        amt = 0;
        rate = 0;
        term = 0;
        built = false;
    }
    public void setAmount(double amt) {
        this.amt = amt; //this.amt is the global of the class
        built = false;
    }
    public double getAmount() {
        return this.amt;
    }
        public void setRate(double rate) {
        this.rate = rate; //this.rate is the global of the class
        built = false;
    }
    public double getRate() {
        return this.rate;
    }

    public void setTerm(int term) {
        this.term = term; //this.term is the global of the class
        built = false;
    }
    public int getTerm() {
        return this.term;
    }
    private void buildAnnuity() {
        bbal = new double[term];
        intearn = new double[term];
        ebal = new double[term];

        bbal[0] = 0;
        for (int i=0; i<this.term; i++ ) {
            if (i > 0) {
                bbal[i] = ebal[i-1];
            }
            intearn[i] = (bbal[i]+this.amt) * (this.rate / 12.0);
            ebal[i] = bbal[i] + intearn[i] + this.amt;
        }
        built = true;

    } //end of buildAnnuity

    public double getFinalValue() {
        if (!built) {
            buildAnnuity();
        }
        return this.ebal[term-1];
    }
    public double getBeginBal(int mo) {
        if (mo <= 0 || mo > this.term) {
            return -999;
        }
        if(!built) {
            buildAnnuity();
        }
        return bbal[mo-1];
    }
        public double getIntEarned(int mo) {
        if (mo <= 0 || mo > this.term) {
            return -999;
        }
        if(!built) {
            buildAnnuity();
        }
        return intearn[mo-1];
    }
        public double getEndBal(int mo) {
        if (mo <= 0 || mo > this.term) {
            return -999;
        }
        if(!built) {
            buildAnnuity();
        }
        return ebal[mo-1];
    }
}
