/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;

/**
 *
 * @author Owner
 */
public class Utill {

    /**
     * This method gives the plain text version for the given html content
     *
     * @param html
     * @return
     */
    public static String getPlainTextVersion(String html) {
        try {
            String plainText = new HtmlToPlainText().getPlainText(Jsoup.parse(html));
            return plainText;
        } catch (Exception e) {
            System.out.print("\nError while converting the html to plaing text");
            e.printStackTrace(System.out);
        }
        return html;
    }
}
