package com.soso;

public class RegularPrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

}
