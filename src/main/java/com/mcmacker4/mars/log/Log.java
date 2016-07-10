package com.mcmacker4.mars.log;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class Log {

    public static void info(String msg) {
        printTime();
        String m = msg.replace("\r\n", "\r\n\t\t\t\t");
        System.out.println("INFO: " + m);
        System.out.flush();
    }

    public static void warning(String msg) {
        printTime();
        String m = msg.replaceAll("\r\n", "\r\n\t\t\t\t");
        System.out.println("WARNING: " + m);
        System.out.flush();
    }

    public static void error(String msg) {
        printTime(System.err);
        String m = msg.replace("\r\n", "\r\n\t\t\t\t");
        System.err.println("ERROR: " + m);
        System.err.flush();
    }

    public static void error(Throwable th) {
        error(th.getMessage());
        th.printStackTrace();
    }

    private static void printTime() {
        printTime(System.out);
    }

    private static void printTime(PrintStream out) {
        GregorianCalendar cal = new GregorianCalendar();
        out.print("[" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] ");
    }

}
