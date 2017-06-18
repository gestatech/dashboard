package be.gestatech.core.api.bookmark;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gestatech
 */
public interface Bookmarkable extends Serializable {

    String PARAM_PATH = "path";

    String getPath();

    void setPath(String path);
}
