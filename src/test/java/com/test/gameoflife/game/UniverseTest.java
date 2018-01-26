package com.test.gameoflife.game;

import com.test.gameoflife.helper.SeedFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class UniverseTest {

    private Universe universe;
    private String filePath;

    @Before
    public void setUp() throws IOException {

        filePath = "../gameoflife/src/test/resources/";
        String fileName = filePath + "blinker_seed.txt";
        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        universe = new Universe(seed);
    }


    @Test
    public void testUniverse() throws IOException {

        assertTrue(universe.getUniverseGrid() instanceof Human[][]);
    }

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
     * 000
     * 010
     * 010
     * 010
     * 000
     */
    @Test
    public void testUpdateHumanState_FirstCondition() {

        Human testHuman = universe.getUniverseGrid()[1][1];
        State currentHumanState = testHuman.getState(); // Alive

        universe.updateHumanState(testHuman);
        State updateHumanState = testHuman.getState(); // Dead.

        assertThat(currentHumanState, equalTo(State.ALIVE));
        assertThat(updateHumanState, equalTo(State.DEAD));
    }

    /**
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 000
     * 010
     * 010
     * 010
     * 000
     */
    @Test
    public void testUpdateHumanState_SecondCondition() {

        Human testHuman = universe.getUniverseGrid()[2][1];
        State currentHumanState = testHuman.getState(); // Alive

        universe.updateHumanState(testHuman);
        State updateHumanState = testHuman.getState(); // Alive.

        assertThat(currentHumanState, equalTo(State.ALIVE));
        assertThat(updateHumanState, equalTo(State.ALIVE));
    }

    /**
     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
     * 000
     * 010
     * 010
     * 010
     * 010
     * 010
     */
    @Test
    public void testUpdateHumanState_ThirdCondition() throws IOException {

        String fileName = "blinker_seed_third_condition.txt";
        Seed seed = SeedFileReader.readSeedFromFile(filePath + fileName);
        Universe universe = new Universe(seed);

        Human testHuman = universe.getUniverseGrid()[1][1];
        State currentHumanState = testHuman.getState(); // Alive

        universe.updateHumanState(testHuman);
        State updateHumanState = testHuman.getState(); // Dead.

        assertThat(currentHumanState, equalTo(State.ALIVE));
        assertThat(updateHumanState, equalTo(State.DEAD));
    }


    /**
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     * 000
     * 010
     * 010
     * 010
     * 000
     */
    @Test
    public void testUpdateHumanState_FourthCondition() {

        Human testHuman = universe.getUniverseGrid()[2][0];
        State currentHumanState = testHuman.getState(); // Dead

        universe.updateHumanState(testHuman);
        State updateHumanState = testHuman.getState(); // Alive.

        assertThat(currentHumanState, equalTo(State.DEAD));
        assertThat(updateHumanState, equalTo(State.ALIVE));
    }

}
