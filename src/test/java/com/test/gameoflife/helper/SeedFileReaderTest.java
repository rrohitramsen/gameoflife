package com.test.gameoflife.helper;

import com.test.gameoflife.game.InvalidInputFileException;
import com.test.gameoflife.game.Seed;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class SeedFileReaderTest {

    String filePath;
    String fileName;

    @Before
    public void setUp() {


         filePath = "../gameoflife/src/test/resources/";
         fileName = filePath + "blinker_seed.txt";
    }

    @Test
    public void testReadSeedFromFile() throws IOException {


        assertTrue(SeedFileReader.readSeedFromFile(fileName) instanceof Seed);

    }

    @Test
    public void testReadSeedFromFile_verifyValues() throws IOException {

        byte[][] expectedPattern = {{0,0,0},
                {0,1,0},
                {0,1,0},
                {0,1,0},
                {0,0,0}};


        assertEquals(SeedFileReader.readSeedFromFile(fileName).getCol(), 3);
        assertEquals(SeedFileReader.readSeedFromFile(fileName).getRow(), 5);
        assertEquals(SeedFileReader.readSeedFromFile(fileName).getTicks(), 5);
        assertThat(SeedFileReader.readSeedFromFile(fileName).getPattern(), is(equalTo(expectedPattern)));

    }

    @Test(expected = InvalidInputFileException.class)
    public void testReadSeedFromFile_withEmptyFile() throws IOException {


        assertTrue(SeedFileReader.readSeedFromFile(filePath+"empty.txt") instanceof Seed);

    }

}
