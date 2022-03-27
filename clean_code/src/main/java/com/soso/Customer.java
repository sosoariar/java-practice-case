package com.soso;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg){
        _rentals.addElement(arg);
    }

    public String getName(){
        return _name;
    }

    /**
     *  statement 负责的逻辑过于繁重
     *  应对系统功能变化的修改
     *  1. 当前以普通String的方式,换成以 Html 的方式输出, htmlStatement()
     *  2.
     * @return
     */
    public String statement(){

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while(rentals.hasMoreElements()){

            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            switch(each.getMovie().getPriceCode()){
                case Movie.REGULAR:
                    thisAmount += 2;
                    if(each.getDaysRented()>2){
                        thisAmount += (each.getDaysRented()-2)*1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented()*3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if(each.getDaysRented()>3){
                        thisAmount += (each.getDaysRented()-3)*1.5;
                    }
                    break;
            }

            frequentRenterPoints++;
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)&&each.getDaysRented()>1){
                frequentRenterPoints++;
            }

            result += "\t"+each.getMovie().getTitle()+"\t"+String.valueOf(thisAmount)+"\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + String.valueOf(totalAmount)+"\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}
