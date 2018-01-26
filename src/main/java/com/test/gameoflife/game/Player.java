package com.test.gameoflife.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class Player {

    /**
     *
     * @param universe
     * @return Grid
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static byte[][] play(Universe universe) throws ExecutionException, InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * Submit all human callable to Thread Pool.
         */
        List<Future<Boolean>> humanResponses = new ArrayList<Future<Boolean>>();

        for (int i=0; i<universe.getUniverseGridRow(); i++) {
            for (int j=0; j<universe.getUniverseGridCol(); j++) {
                Future<Boolean> response = executorService.submit(universe.getUniverseGrid()[i][j]);
                humanResponses.add(response);
            }
        }

        /**
         * Await all callable to be done (blocking)
         */
        for(Future<?> currentResponse : humanResponses)
            currentResponse.get();

        executorService.shutdown();

        /**
         * TODO Use the seed to start next generation instead of using byte array.
         */
        byte[][] nextGeneration = humanGridToByteGrid(universe);

        return nextGeneration;
    }

    /**
     * Convert Universe human grid into byte grid.
     * @param universe
     * @return
     */
    private static byte[][] humanGridToByteGrid(Universe universe) {

        int row = universe.getUniverseGridRow();
        int col = universe.getUniverseGridCol();

        byte [][] nextGeneration = new byte[row][col];
        Human[][] humanGrid = universe.getUniverseGrid();

        for (int r=0; r<row; r++) {

            for (int c=0; c<col; c++) {

                if (humanGrid[r][c].getState().equals(State.ALIVE)) {
                    nextGeneration[r][c] = Byte.parseByte("1");
                } else {
                    nextGeneration[r][c] = Byte.parseByte("0");
                }
            }
        }

        /**
         * In case human is growing in the universe then we have to resize the universe human grid.
         */
        if (universe.isHumanGridGrowingInUniverse()) {

            universe.resizeHumanGrid();
            return resizeNextGeneration(nextGeneration);

        }

        return nextGeneration;
    }

    /**
     * Resize next generation if human is growing in the universe.
     * @param nextGeneration
     * @return
     */
    private static byte[][] resizeNextGeneration(byte[][] nextGeneration) {

        int currentNextGenerationRow = nextGeneration.length;
        int currentNextGenerationCol = nextGeneration[0].length;
        int resizeNextGenerationRow = currentNextGenerationRow * 2;
        int resizeNextGenerationCol = currentNextGenerationCol * 2;
        byte [][] resizeNextGeneration = new byte[resizeNextGenerationRow][resizeNextGenerationCol];

        /**
         * Copy Old Human grid into new human grid.
         */
        int nextRow = currentNextGenerationRow/2;
        int nextCol = currentNextGenerationCol/2;
        for (int r=0; r<currentNextGenerationRow; r++) {

            for (int c = 0; c < currentNextGenerationCol; c++) {
                resizeNextGeneration[nextRow + r][nextCol + c] = nextGeneration[r][c];
            }
        }

        return resizeNextGeneration;
    }

}
