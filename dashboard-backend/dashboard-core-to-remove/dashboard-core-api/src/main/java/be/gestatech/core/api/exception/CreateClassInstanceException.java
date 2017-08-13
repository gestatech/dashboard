/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gestatech.core.api.exception;

/**
 *
 * @author gestatech
 */
public class CreateClassInstanceException extends Exception {

    private static final long serialVersionUID = 4056486218704297685L;

    public CreateClassInstanceException(String name) {
        super(name);
    }   
}
