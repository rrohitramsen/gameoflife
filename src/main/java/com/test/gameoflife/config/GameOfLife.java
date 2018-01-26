package com.test.gameoflife.config;

import com.test.gameoflife.game.Player;
import com.test.gameoflife.game.Seed;
import com.test.gameoflife.game.Universe;
import com.test.gameoflife.helper.DisplayUtils;
import com.test.gameoflife.helper.SeedFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Created by rohitkumar on 31/12/17.
 */
public class GameOfLife {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameOfLife.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        if (args.length == 0) {

            LOGGER.info(" Please provide file name ::");
        } else {

            LOGGER.info("File Name :: "+args[0]);
            start(args[0]);
        }

    }

    /**
     *
     * @param fileName
     * @throws IOException
     */
    public static void start(String fileName) throws IOException, ExecutionException, InterruptedException {

        Seed seed = SeedFileReader.readSeedFromFile(fileName);
        LOGGER.info("Starting Game of life with seed :: "+ seed);

        Universe universe = new Universe(seed);

        for (int tick = 0; tick < seed.getTicks(); tick++) {

            byte[][] nextGeneration = Player.play(universe);

            LOGGER.info("******************** Generation - | "+(tick+1)+" | *****************");
            LOGGER.info(DisplayUtils.display(nextGeneration));
            LOGGER.info("********************************************************************");

            universe.updateSeed(nextGeneration);

        }


    }

}
