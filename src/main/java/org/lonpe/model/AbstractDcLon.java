/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.model;

import java.io.Serializable;

/**
 *
 * @author l5
 */
public abstract class AbstractDcLon implements IDcLon, Serializable {

    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    private String pkey;

    @Override
    public String getPkey() {
        return this.pkey;
    }

    @Override
    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

}
