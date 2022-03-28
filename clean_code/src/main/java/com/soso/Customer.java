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
     *  3. thisAmount 多余了 Replace Temp with Query(120)
     *  4. frequentRenterPoints 在extract方法中并没重新读数,因此重构时不需要作为参数传入
     *  5. Repalce Temp with Query
     * @return
     */
    public String statement(){

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while(rentals.hasMoreElements()){

            Rental each = (Rental) rentals.nextElement();
            result += "\t"+each.getMovie().getTitle()+"\t"+String.valueOf(each.getCharge())+"\n";

        }

        result += "Amount owed is " + String.valueOf(getTotalCharge())+"\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;

    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getFrequentRenterPoints(int frequentRenterPoints, Rental each) {
        frequentRenterPoints++;
        if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)&& each.getDaysRented()>1){
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    /**
     * 重构过程中,返回类型发生了错误,但是因为double的结果自动转化为int而没有发现
     * 重构后函数的局部变量可以修改,变量名称是代码清晰的关键
     *
     * 问题 金额计算逻辑 aRental.getDaysRented() 并没有使用 Customer 类的数据；
     * 绝大多数情况下,函数应该放在它所使用的数据的所属对象内
     * moveMethod(142)
     *
     * @param aRental
     * @return
     */
    private double amountFor(Rental aRental){

        return aRental.getCharge();

    }

}
