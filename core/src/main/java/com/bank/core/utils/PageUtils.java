package com.bank.core.utils;

import java.util.List;

public class PageUtils {
    public static List startPage(List list,Integer pageNum,Integer pageSize){
        if(list==null){
            return null;
        }
        if(list.size()==0){
            return null;
        }
        Integer count=list.size();
        Integer pageCount=0;
        if(count%pageSize==0){
            pageCount=count/pageSize;
        }else{
            pageCount=count/pageSize+1;
        }

        int fromIndex=0;
        int toIndex=0;
        if(pageNum!=pageCount){
            fromIndex=(pageNum-1)*pageSize;
            toIndex=fromIndex+pageSize;
        }else{
            fromIndex=(pageNum-1)*pageSize;
            toIndex=count;
        }
        List pageList=list.subList(fromIndex,toIndex);
        return pageList;
    }
}
