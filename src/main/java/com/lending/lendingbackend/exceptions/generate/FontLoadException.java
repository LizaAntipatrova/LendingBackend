package com.lending.lendingbackend.exceptions.generate;

public class FontLoadException  extends RuntimeException{
    public FontLoadException(){
        super("Failed font load");
    }
}
