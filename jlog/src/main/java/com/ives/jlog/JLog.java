package com.ives.jlog;

import android.util.Log;

/**
 * A log util to save coding.
 * Created by IvesWang on 2016/6/18.
 */

public class JLog {

    private static final int VERBOSE = 2;
    private static final int DEBUG = 3;
    private static final int INFO = 4;
    private static final int WARN = 5;
    private static final int ERROR = 6;
    private static final int ASSERT = 7;

    /**
     * you can set your own different level while in test, develop or release time.
     */
    private static int sLogLevel = VERBOSE;

    /**
     * print Object.toString() or not.if set to false, will print 'xxx is not null' only.
     */
    private static boolean sPrintToString = true;
    /**
     * a print mode about to some inset string to compose the result print string.
     *
     */
    private static PrintMode sPrintMode = PrintMode.IS_EMPTY;

    private JLog(){
    }

    public static void setLogLevel(int logLevel){
        sLogLevel = logLevel;
    }
    /**
     * if set to false, JLog will not print Object.toString() if obj is not null, default true.
     * @param needPrintString false, print fixed string if null
     */
    public static void setPrintToString(boolean needPrintString){
        sPrintToString = needPrintString;
    }

    /**
     * set your own print mode.default is PrintMode.IS_EMPTY
     * @param printMode describe as {@link PrintMode}
     */
    public static void setPrintMode(PrintMode printMode){
        sPrintMode = printMode;
    }

    public static void v(String tag, String str){
        if(sLogLevel <=VERBOSE) {
            Log.v(tag, str);
        }
    }
    public static void d(String tag,String str){
        if(sLogLevel <=DEBUG){
            Log.d(tag,str);
        }
    }
    public static void i(String tag, String str){
        if(sLogLevel <=INFO) {
            Log.i(tag, str);
        }
    }
    public static void w(String tag, String str){
        if(sLogLevel <=WARN) {
            Log.w(tag, str);
        }
    }
    public static void e(String tag, String str){
        if(sLogLevel <=ERROR) {
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

    public static void v(String tag,Object[] nullableObjs,String[] objNames){
        v(tag, getRuleString(nullableObjs, objNames));
    }
    public static void d(String tag, Object[] nullableObjs, String[] objNames){
        d(tag, getRuleString(nullableObjs, objNames));
    }
    public static void i(String tag,Object[] nullableObjs,String[] objNames){
        d(tag, getRuleString(nullableObjs, objNames));
    }
    public static void w(String tag,Object[] nullableObjs,String[] objNames){
        d(tag, getRuleString(nullableObjs, objNames));
    }
    public static void e(String tag,Object[] nullableObjs,String[] objNames){
        d(tag, getRuleString(nullableObjs, objNames));
    }

    private static String getRuleString(Object nullableObj,String objName){
        //TODO we can support more modes for user, but I think use builder design mode will be better
        //TODO as there are too many mode if we want adding a mode representing print obj directly without any judge.
        return objName + sPrintMode.isChar +(nullableObj==null?sPrintMode.nullChar:(sPrintToString?nullableObj.toString():"not null"));
    }

    private static String getRuleString(Object[] nullableObjs, String[] objNames){
        if(nullableObjs==null||objNames==null)return "";
        if(nullableObjs.length!=objNames.length){
            Log.w("JLog", "Length of obj names array not fits obj array's length, you should fix it!");
        }

        StringBuilder sb = new StringBuilder();
        int length = Math.min(nullableObjs.length, objNames.length);
        for (int i = 0; i < length; i++) {
            sb.append(getRuleString(nullableObjs[i], objNames[i])).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()-1);
        System.out.println("d");

        return sb.toString();
    }
    public enum PrintMode {
        IS_NULL("is","null"),IS_EMPTY("is", ""),COLON_NULL(":","null"),COLON_EMPTY(":","");

        private String isChar;
        private String nullChar;
        PrintMode(String isChar,String nullChar){
            this.isChar = isChar;
            this.nullChar = nullChar;
        }
    }

}
