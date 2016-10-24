/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper.enums;

/**
 *
 * @author Owner
 */
public enum Providers {
    SendGrid("SendGrid");
    private final String name;

    private Providers(String name) {
        this.name = name;
    }

}
