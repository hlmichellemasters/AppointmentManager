/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for Divisions
 */
package model;

/**
 * creates Division objects, including the getters, setters, and toString function
 */
public class Division {

    private int divisionID;
    private String divisionName;

    /**
     * constructor for creating a division object
     * @param divisionID ID for the division
     * @param divisionName name of the division
     */
    public Division(int divisionID, String divisionName) {

        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * returns division ID for a division object
     * @return ID of the division
     */
    public int getDivisionID() { return divisionID; }

    /**
     * sets the division ID for a division object
     * @param divisionID ID to set for the division
     */
    public void setDivisionID(int divisionID) { this.divisionID = divisionID; }

    /**
     * gets the division name for a division object
     * @return name of the division
     */
    public String getDivisionName() { return divisionName; }

    /**
     * sets the division name for a division object
     * @param divisionName name to set for the division
     */
    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    /**
     * overrides the default toString method to represent a division as a combo of its ID and name
     * @return the String representation of the division object
     */
    @Override
    public String toString() { return "[" + divisionID + "] " + divisionName; }

}
