package com.soso;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title ;
    private int _priceCode;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int _priceCode) {
        this._priceCode = _priceCode;
    }

    /**
     * 租期长度作为参数传递进去,租期长度来自 Rental 对象
     * 计算费用时需要两项数据: 租期长度和影片类型
     * 为什么选择将租期长度传给 Movie 对象实例
     * 因为系统中可能发生的变化是加入新影片类型
     *
     * @param daysRented
     * @return
     */
    double getCharge(int daysRented){
        double result = 0;
        switch(getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                if(daysRented > 2){
                    result += (daysRented - 2)*1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += daysRented*3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(daysRented>3){
                    result += (daysRented-3)*1.5;
                }
                break;
        }
        return result;
    }

}


