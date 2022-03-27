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
     *  2. 计费标准发生变化,如何修改 statement 和 htmlStatement, 如果程序保存了很长时间,而且可能需要修改,复制粘贴就会有潜在威胁
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

            thisAmount = amountFor(each);

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

    /**
     * 重构过程中,返回类型发生了错误,但是因为double的结果自动转化为int而没有发现
     * 重构后函数的局部变量可以修改
     * @param aRental
     * @return
     */
    private double amountFor(Rental aRental){

        double result = 0;

        switch(aRental.getMovie().getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                if(aRental.getDaysRented()>2){
                    result += (aRental.getDaysRented()-2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented()*3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(aRental.getDaysRented()>3){
                    result += (aRental.getDaysRented()-3)*1.5;
                }
                break;
        }

        return result;

    }

}
