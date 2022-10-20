/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonvx.handlers.exceptions;

/**
 *
 * @author l5
 */
public class ExcelImportException extends Exception {

    private final int nrow;
    private final int ncol;
    private final String msg;

    public ExcelImportException(String msg, int nrow, int ncol) {
        this.msg = msg;
        this.nrow = nrow;
        this.ncol = ncol;
    }

    @Override
    public String getMessage() {
        return "Error [" + msg + "] in nrow = " + nrow + ", ncol = " + ncol;
    }

}
