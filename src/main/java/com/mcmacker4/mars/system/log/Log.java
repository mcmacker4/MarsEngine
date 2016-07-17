package com.mcmacker4.mars.system.log;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class Log {

    public static void info(String msg) {
        printTime();
        System.out.println("INFO: " + msg);
        System.out.flush();
    }

    public static void info(Object object) {
        info(object.toString());
    }

    public static void warning(String msg) {
        printTime();
        System.out.println("WARNING: " + msg);
        System.out.flush();
    }

    public static void warning(Object object) {
        warning(object.toString());
    }

    public static void error(String msg) {
        printTime(System.err);
        System.err.println("ERROR: " + msg);
        System.err.flush();
    }

    public static void error(Throwable th) {
        error(th.getMessage());
        th.printStackTrace();
    }

    public static void error(Object object) {
        error(object.toString());
    }

    private static void printTime() {
        printTime(System.out);
    }

    private static void printTime(PrintStream out) {
        GregorianCalendar cal = new GregorianCalendar();
        out.print("[" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] ");
    }

}
