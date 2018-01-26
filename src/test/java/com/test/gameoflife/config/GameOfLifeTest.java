package com.test.gameoflife.config;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Created by rohitkumar on 31/12/17.
 */
public class GameOfLifeTest {

    private String filePath = "../gameoflife/src/test/resources/";


    @Test
    public void testStart_toad_seed() throws InterruptedException, ExecutionException, IOException {

        GameOfLife.start(filePath + "toad_seed.txt");
    }

    @Test
    public void testStart_tub_seed() throws InterruptedException, ExecutionException, IOException {

        GameOfLife.start(filePath + "tub_seed.txt");
    }

    @Test
    public void testStart_blinker_seed() throws InterruptedException, ExecutionException, IOException {

        GameOfLife.start(filePath + "blinker_seed.txt");
    }

    @Test
    public void testStart_beacon_seed() throws InterruptedException, ExecutionException, IOException {

        GameOfLife.start(filePath + "beacon_seed.txt");
    }

    /**
     * Here Universe will resize the human universe grid whenever it grows in any direction.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    @Test
    public void testStart_glider_seed() throws InterruptedException, ExecutionException, IOException {

        GameOfLife.start(filePath + "glider_seed.txt");
    }



}
