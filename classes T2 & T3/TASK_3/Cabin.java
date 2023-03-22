package com.company;

public class Cabin {
    public String CruiseCabin;
    public static Passenger[][] P_details = new Passenger[12][3];

    public Cabin(String CruiseCabin, Passenger[][] P_details ) {
        this.CruiseCabin = CruiseCabin;
        this.P_details = P_details;
    }
}
