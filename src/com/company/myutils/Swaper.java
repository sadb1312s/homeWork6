package com.company.myutils;

import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

public class Swaper {

    public void swap(List<?> list, int i, int j){
        if(list instanceof RandomAccess){
            swapHelper(list,i,j);

        }
    }

    private <T> void swapHelper(List<T> elements, int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }




}
