/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author sonnt
 */
public class HtmlHelper {

    public static String hyperlink(String label, String href) {

        return "<li><a class=\"page_hyperlink\" href=\"" + href + "\">" + label + "</a><li>";
    }

    public static String pager(int pageindex, int pagecount, int gap, String s) {
        String result = "<ul class=\"pagination\">\n";

        if (pageindex > gap) {
            result += hyperlink("First", "List?page=" + 1 + s);
        }
        for (int i = gap; i > 0; i--) {
            if (pageindex - gap > 0) {
                result += hyperlink("" + (pageindex - i), "List?page=" + (pageindex - i) + s);
            }
        }
        if (pagecount <= gap) {
            if (pageindex > 2) {
                result += hyperlink("First", "List?page=" + 1 + s);
            }
            for (int i = pageindex-1; i > 0; i--) {
                if (pageindex > 0) {
                    result += hyperlink("" + (pageindex - i), "List?page=" + (pageindex - i) + s);
                }
            }
        }
        result += "<li class=\"active\"><span>" + pageindex + "<span class=\"sr-only\"></span></span></li>";
        if (pagecount <= gap) {
            for (int i = 1; i < pagecount-pageindex+1; i++) {
                if (pageindex < pagecount) {
                    result += hyperlink("" + (pageindex + i), "List?page=" + (pageindex + i) + s);
                }
            }
            if (pageindex< pagecount-1) {
                result += hyperlink("Last", "List?page=" + pagecount + s);
            }
        }
        for (int i = 1; i <= gap; i++) {
            if (pageindex + gap < pagecount) {
                result += hyperlink("" + (pageindex + i), "List?page=" + (pageindex + i) + s);
            }
        }

        if (pageindex + gap < pagecount) {
            result += hyperlink("Last", "List?page=" + pagecount + s);
        }
        return result + "</ul>";
    }
}
