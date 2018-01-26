package com.test.gameoflife.game;

import com.test.gameoflife.helper.SeedFileReader;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class PlayerTest {

    private String filePath = "../gameoflife/src/test/resources/";

    @Test
    public void testPlay_blinker_seed() throws ExecutionException, InterruptedException, IOException {

        String fileName = filePath + "blinker_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        Universe universe = new Universe(seed);

        byte[][] expectedNextGeneration = {
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,1,1,1,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0}
        };

        assertThat(Player.play(universe), equalTo(expectedNextGeneration));

    }

    @Test
    public void testPlay_beacon_seed() throws ExecutionException, InterruptedException, IOException {

        String fileName = filePath + "beacon_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        Universe universe = new Universe(seed);

        byte[][] expectedNextGeneration = {
                {0,0,0,0,0,0},
                {0,1,1,0,0,0},
                {0,1,0,0,0,0},
                {0,0,0,0,1,0},
                {0,0,0,1,1,0},
                {0,0,0,0,0,0}
        };

        assertThat(Player.play(universe), equalTo(expectedNextGeneration));

    }


    @Test
    public void testPlay_toad_seed() throws ExecutionException, InterruptedException, IOException {

        String fileName = filePath + "toad_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        Universe universe = new Universe(seed);

        byte[][] expectedNextGeneration = {
                {0,0,0,0,0,0},
                {0,0,0,1,0,0},
                {0,1,0,0,1,0},
                {0,1,0,0,1,0},
                {0,0,1,0,0,0},
                {0,0,0,0,0,0}
        };

        assertThat(Player.play(universe), equalTo(expectedNextGeneration));

    }


    @Test
    public void testPlay_tub_seed() throws ExecutionException, InterruptedException, IOException {

        String fileName = filePath + "tub_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        Universe universe = new Universe(seed);

        /**
         * Same output because its a still seed.
         */
        byte[][] expectedNextGeneration = {
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,1,0,1,0},
                {0,0,1,0,0},
                {0,0,0,0,0}
        };

        assertThat(Player.play(universe), equalTo(expectedNextGeneration));

    }


    @Test
    public void testPlay_glider_seed() throws ExecutionException, InterruptedException, IOException {

        String fileName = filePath + "glider_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        Universe universe = new Universe(seed);

        /**
         * Same output because its a still seed.
         */
        byte[][] expectedNextGeneration = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0},
                {0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}

        };

        assertThat(Player.play(universe), equalTo(expectedNextGeneration));

    }

}
