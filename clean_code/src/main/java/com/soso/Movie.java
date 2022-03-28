package com.soso;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title ;
    private Price price;

    /**
     * 针对类型代码使用 SelfEncapsulate Field, 确保任何时候都通过取值函数和设置函数访问类型代码
     */
    public Movie(String title, int priceCode) {
        this._title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch(arg){
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
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


