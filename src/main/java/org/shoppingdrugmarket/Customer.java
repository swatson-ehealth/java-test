package org.shoppingdrugmarket;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A customer of Shopping Drug Market
 */
public class Customer {
    private final String name;
    private final List<Prescription> prescriptions = new ArrayList();
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private int totalOptimalPoints;
    private double totalCost;

    /**
     * A customer must have a name
     *
     * @param name a string defining the customer's name
     */
    public Customer(String name) {
        this.name = name;
        this.totalOptimalPoints = 0;
        this.totalCost = 0;
    }

    /**
     * When a customer needs to add a prescription to their list of prescriptions
     *
     * @param newPrescription the new prescription being added for the customer
     */
    public void addNewPrescription(Prescription newPrescription) {
        prescriptions.add(newPrescription);
    }

    public String getName() {
        return name;
    }
    
    private String getTotalDecimalCost() {
        return decimalFormat.format(totalCost);
    }
    
    /**
     * Returns the middle section of the customer prescrition table based
     * on given input type
     * @param type
     * @return string of table data based on type given
     */
    private String buildTable(int type) {
        String result = "";
        totalOptimalPoints = 0;
        totalCost = 0.0;

        for (Prescription prescription : prescriptions) {
            int prescriptionCost = prescription.getTotalCost();
            int prescriptionOptimalPoints = prescription.getTotalOptimalPointBonus();
            String presciptionCostDecimal = decimalFormat.format(prescriptionCost/100.0) ;
           
            switch(type) {
                case 0: // text
                    // now we can add to the string, showing the cost and number of points gained per item
                    result +=   "\t " + prescription.getMedication().getMedicationName() + 
                                ":\t" + presciptionCostDecimal + 
                                "\t" + String.valueOf(prescriptionOptimalPoints) + "\n";
                    break;
                case 1: // html
                    result +=   "<tr><td>" + prescription.getMedication().getMedicationName() +
                                "</td><td>" + presciptionCostDecimal +
                                "</td><td>" + String.valueOf(prescriptionOptimalPoints) + "</td></tr>";
                    break;
            }
            
            totalCost += prescriptionCost / 100;
            totalOptimalPoints += prescriptionOptimalPoints;
        }
        
        return result;
    }

    /**
     * Generate a plain text receipt for the customer's purchases
     *
     * @return a string containing the items purchased, their cost and the number of points the customer received
     */
    public String generatePrescriptionReceiptText() {
        String result = "Prescription receipt for " + getName() + ":\n";

        result += buildTable(0);
        
        result += "Total cost:\t" + getTotalDecimalCost() + "\n";
        result += "Total optimal points earned:\t" + totalOptimalPoints + "\n";

        return result;
    }

    /**
     * Generate an HTML receipt for e-mailing to the customer
     *
     * @return an html string containing the items purchased, their cost and the number of points the customer received
     */
    public String generatePrescriptionReceiptHtml() {
        String result = "<html><body><p><h3>Prescription receipt for " + getName() + ":</h3></p>";
        result += "<table><tr><th>Medication</th><th>Price</th><th>Optimal Points</th></tr>";

        result += buildTable(1);

        result += "</table>";
        result += "<p>Total cost: " + getTotalDecimalCost() + "</p>";
        result += "<p>Total optimal points earned: " + totalOptimalPoints + "</p></body></html>";

        return result;
    }

}
