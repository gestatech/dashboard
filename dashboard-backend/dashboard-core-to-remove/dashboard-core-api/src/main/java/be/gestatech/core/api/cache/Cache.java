/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author gestatech
 */
public interface Cache extends Serializable {

    <T> T readFromCache(String key);

    <T> void storeToCache(String key, T value);

    void removeFromCache(String key);

    void clear();
}
