package com.test.gameoflife.game;

import java.io.InvalidObjectException;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class InvalidInputFileException extends InvalidObjectException {
    /**
     * Constructs an <code>InvalidObjectException</code>.
     *
     * @param reason Detailed message explaining the reason for the failure.
     * @see ObjectInputValidation
     */
    public InvalidInputFileException(String reason) {
        super(reason);
    }
}
