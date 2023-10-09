package com.kejin.utils;

/**
 * @author 陈
 */
public class CommonUtils {

    public static boolean numIsNull(Integer num){
        return (num==null || num <=0 );
    }

    public static boolean strIsNull(String str){
        return (str==null || str =="" );
    }

    public static boolean objectIsNull(Object object){
        return object==null ;
    }

}
