/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.dashboard.infra.audit.string;

import java.util.Objects;

/**
 *
 * @author gestatech
 */
public class StringUtil {

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String DASH = "-";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String NUMBER_SIGN = "#";
    public static final String LF = "\n";
    public static final String CR = "\r";

    private StringUtil() {
        throw new RuntimeException();
    }

    public static boolean isEmpty(final CharSequence cs) {
        return Objects.isNull(cs) || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isAnyString(String input) {
        return input != null && (!input.trim().equals(""));
    }

}
