package org.shoppingdrugmarket;

/**
 * A medication that gets sold at Shopping Drug Market
 */
public class Medication {
    public static final int PAINKILLER = 2;
    public static final int DECONGESTANT = 1;
    public static final int ANTIHISTAMINE = 0;

    private String medicationName;
    private int medicationType;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined enum
     */
    public Medication(String medicationName, int medicationType) {
        this.medicationName = medicationName;
        this.medicationType = medicationType;
    }

    public int getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(int newDosage) {
        medicationType = newDosage;
    }

    public String getMedicationName() {
        return medicationName;
    }
    
    /**
     * Custom medication cost per type
     * @param amount
     * @return cost of medication
     */
    public int calculateMedicationCost(int amount) {
        int returnCost = 0;
        
        switch(this.medicationType) {
            case Medication.ANTIHISTAMINE:
                int s = amount;
                if (s > 100) {
                    returnCost += s * 0.8;
                } else if (s > 50) {
                    returnCost += s * 0.9;
                } else {
                    returnCost += s;
                }
                break;
            case Medication.DECONGESTANT:
                returnCost += amount * 2;
                break;
            case Medication.PAINKILLER:
                int z = amount;
                if (z > 200) {
                    returnCost += z * 1.5;
                } else if (z > 100) {
                    returnCost += z * 2;
                } else {
                    returnCost += z * 3;
                }
        }
        
        return returnCost;
    }
    
    public int getOptimalPointBonus() {
        int optimalPoints = 100;

        // we're running a promo to give bonus optimal points for decongestants!
        if (this.medicationType == Medication.DECONGESTANT) {
            optimalPoints += 200;
        }
        
        return optimalPoints;
    }
}
