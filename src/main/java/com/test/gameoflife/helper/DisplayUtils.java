package com.test.gameoflife.helper;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class DisplayUtils {

    /**
     *
     * @param pattern
     * @return String representation of the pattern.
     */
    public static String display(byte[][] pattern) {

        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (int r=0; r<pattern.length; r++) {

            for(int c=0; c<pattern[0].length; c++) {
                result.append("| "+pattern[r][c]+" |");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
