package com.test.gameoflife.game;


/**
 * Created by rohitkumar on 01/01/18.
 */
public enum State {

    ALIVE,
    DEAD;

    public static State findState(Byte value) {

        State result = DEAD;

        switch (value) {
            case 0 :
                result =  DEAD;
                break;

            case 1:
                result =  ALIVE;
        }

        return result;
    }

}

