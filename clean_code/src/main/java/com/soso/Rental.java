package com.soso;

public class Rental {

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie _movie, int _daysRented) {
        this._movie = _movie;
        this._daysRented = _daysRented;
    }

    public int getDaysRented(){
        return _daysRented;
    }

    public Movie getMovie(){
        return _movie;
    }

    double getCharge(){
        return _movie.getCharge(_daysRented);
    }

    /**
     * 如何根据影片类型而变化的所有东西,放到影片类型所属的类中
     * @return
     */
    public int getFrequentRenterPoints() {
        if((getMovie().getPriceCode() == Movie.NEW_RELEASE)&&getDaysRented()>1){
            return 2;
        }else{
            return 1;
        }
    }
}
