/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

/**
 *
 * @author vako
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        
    }
     public ResourceNotFoundException(String message) {
         super(message);
     }
     public ResourceNotFoundException(String message, Throwable cause) {
        super(message,cause);
     }
     public ResourceNotFoundException(Throwable cause) {
      super(cause);  
     }
     public ResourceNotFoundException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
         super(message,cause,enableSupression,writableStackTrace);
     }
}
