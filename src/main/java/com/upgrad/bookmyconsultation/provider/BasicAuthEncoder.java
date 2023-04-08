
package com.upgrad.bookmyconsultation.provider;

import java.util.Base64;

import lombok.Data;

/**
 * Provider to decode basic auth credentials.
 */
@Data
public final class BasicAuthEncoder {

    private final String email;
    private final String password;
    private final int token;

    public BasicAuthEncoder(String email, String password) {
    	this.email = email;
        this.password = password;
        this.token = Base64.getEncoder().encode(email.getBytes(), password.getBytes());
    }
}