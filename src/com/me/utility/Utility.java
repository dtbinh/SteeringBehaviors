package com.me.utility;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Utility<T> {

    //TODO Does this work correctly?
    public List<T> insertAt(List<T> listToInsert, int index, T val) {
        
        if(index > listToInsert.size()){
            throw new IllegalArgumentException("Index must be smaller than size");
        }
        List<T> ret = new LinkedList<T>();
        for (int i = 0; i < index; i++) {
            ret.add(listToInsert.get(i));
        }

        ret.add(val);

        for (int i = index; i < listToInsert.size(); i++) {
            ret.add(listToInsert.get(i));
        }

        return ret;
    }

    public List<T> concat(List<T> left, List<T> right) {
        List<T> ret = new LinkedList<T>();

        for (int i = 0; i < left.size(); i++) {
            ret.add(left.get(i));
        }

        for (int i = 0; i < right.size(); i++) {
            ret.add(right.get(i));
        }
        
        return ret;
    }
}
