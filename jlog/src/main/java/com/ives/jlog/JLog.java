package com.ives.jlog;

import android.util.Log;

/**
 *
 * Created by IvesWang on 2016/6/18.
 */

public class JLog {

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    private static final int LOG_LEVEL = VERBOSE;
    private String tag;

    public static void d(String tag,String str){
        if(LOG_LEVEL<=DEBUG){
            Log.d(tag,str);
        }
    }
    public static void i(String tag, String str){
        if(LOG_LEVEL<=INFO) {
            Log.i(tag, str);
        }
    }
    public static void w(String tag, String str){
        if(LOG_LEVEL<=WARN) {
            Log.w(tag, str);
        }
    }
    public static void e(String tag, String str){
        if(LOG_LEVEL<=ERROR) {
            Log.e(tag, str);
        }
    }
    public static void e(String tag, Exception e) {
        if (null != e) {
            JLog.e(tag, e.getClass().getCanonicalName() + " follow stack is:");
            StackTraceElement[] eles = e.getStackTrace();
            if (null != eles) {
                for (StackTraceElement stackTraceElement : eles) {

                    JLog.e(tag, stackTraceElement.toString());

                }
            }
        }

    }

    private static String getNullExpressionString(Object nullableObj,String objName){
        return objName +" is "+(nullableObj==null?"null":"not null");
    }

    private static String getNullExpressionString(Object[] nullableObjs,String[] objNames){
        if(nullableObjs==null||objNames==null)return "";
        if(nullableObjs.length!=objNames.length)return "log obj name length not fits obj length, you need to fix it but not Suppress ParamLength Warning";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nullableObjs.length; i++) {
            sb.append(getNullExpressionString(nullableObjs[i], objNames[i])).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()-1);
        System.out.println("d");

        return sb.toString();
    }

    public static void d(String tag, Object[] nullableObj, String[] objName){
        d(tag, getNullExpressionString(nullableObj, objName));
    }
    public static void d(Object... obj){
        d(2);
    }
}
