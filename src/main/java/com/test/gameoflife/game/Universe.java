package com.test.gameoflife.game;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class Universe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Universe.class);


    private Human[][] universeGrid;
    private int universeGridRow;
    private int universeGridCol;
    private Seed seed;
    private boolean humanGrowth;


    public Universe(Seed seed) {

        this.seed = seed;
        universeGridRow = seed.getRow();
        universeGridCol = seed.getCol();
        universeGrid = new Human[seed.getRow()][seed.getCol()];

        initializeUniverse(seed);
    }

    /**
     * This method initialize universe with Humans as per their state in given seed.
     * Every instance of Human has access to the Universe.
     * Human also know its location in the universe.
     */
    private void initializeUniverse(Seed seed) {

        for (int row = 0; row < universeGridRow; row++ ) {
            for (int col = 0; col < universeGridCol; col++) {

                Human human = new Human(this, new Location(row, col));
                human.setState(State.findState(seed.getPattern()[row][col]));

                universeGrid[row][col] = human;

            }
        }

    }

    /**
     *
     * 1. Any live cell with fewer than two live neighbours dies, as if caused by under population.
     * 2. Any live cell with two or three live neighbours lives on to the next generation.
     * 3. Any live cell with more than three live neighbours dies, as if by over population.
     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     * @param human
     * @return State
     */
    public void updateHumanState(Human human) {

        LOGGER.debug(" updateHumanState for human ::"+human);

        synchronized (human) {

            int liveHumanCount = findLiveHumanCount(human);

            switch (human.getState()) {

                case ALIVE:
                    /**
                     * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
                     * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
                     */
                    if (liveHumanCount < 2 || liveHumanCount > 3) {

                        LOGGER.debug("Human State updated ::"+State.DEAD);
                        human.setState(State.DEAD);
                        return;
                    }

                    /**
                     * 2. Any live cell with two or three live neighbours lives on to the next generation.
                     */
                    if (liveHumanCount >= 2 && liveHumanCount <= 3) {

                        LOGGER.debug("Human State updated ::"+State.ALIVE);
                        human.setState(State.ALIVE);

                        updateHumanGrowthInUniverse(human);
                    }

                    break;

                case DEAD:
                    /**
                     * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                     */
                    if (liveHumanCount == 3) {

                        LOGGER.debug("Human State is updated ::"+State.ALIVE);
                        human.setState(State.ALIVE);

                        updateHumanGrowthInUniverse(human);
                    }
            }



        }

    }

    /**
     *
     * For any human we have to search in below given eight neighbours only.
     *
     *                      [row-1][col-1]<--[row-1][col]-->[row-1][col+1]
     *                                          |
     *                      [row][col-1]<--[row][col]-->[row][col+1]
     *                                          |
     *                      [row+1][col-1]<--[row+1][col]-->[row+1][col+1]
     *
     *
     * @param human
     * @return live human count
     */
    private int findLiveHumanCount(Human human) {

        LOGGER.debug(" findLiveHumanCount for human ::"+human);

        synchronized (human) {

            int row = human.getLocation().getRow();
            int col = human.getLocation().getCol();

            int rowIndex = row - 1 < 0 ? 0 : row - 1;


            int liveHumanCount = 0;
            for (; rowIndex<=row+1 && rowIndex<universeGridRow; rowIndex++) {

                int colIndex = col - 1 < 0 ? 0 : col - 1;
                for (; colIndex<=col+1 && colIndex<universeGridCol; colIndex++) {

                    if ( (this.seed.getPattern()[rowIndex][colIndex] == 1)
                            && !(rowIndex == row && colIndex == col) ) {

                        liveHumanCount++;
                    }
                }
            }

            LOGGER.debug(" Live Human count for human ::"+human+" is :: "+liveHumanCount);
            return liveHumanCount;
        }
    }

    /**
     * This method will check the direction of human growth in the universe.
     * So that we can resize the universe human grid in case of infinite grid.
     * @param human
     */
    private void updateHumanGrowthInUniverse(Human human) {

        int humanRow = human.getLocation().getRow();
        int humanCol = human.getLocation().getCol();

        if (humanRow-1 < 0 || humanCol-1 < 0 || humanCol+1 >= this.universeGridCol || humanRow+1 >= this.universeGridRow) {
            humanGrowth = true;
        }

    }


    /**
     * Return true or false for human growth direction in universe.
     * @return
     */
    public boolean isHumanGridGrowingInUniverse() {

        return humanGrowth;
    }


    /**
     * Resize human grid if human growing.
     */
    public void resizeHumanGrid() {

        this.humanGrowth = false;
        int updatedUniverseGridRow = this.universeGridRow * 2;
        int updatedUniverseGridCol = this.universeGridCol * 2;
        Human [][] updateHumanGrid = new Human[updatedUniverseGridRow][updatedUniverseGridRow];

        for (int row = 0; row < updatedUniverseGridRow; row++ ) {
            for (int col = 0; col < updatedUniverseGridCol; col++) {

                Human human = new Human(this, new Location(row, col));
                human.setState(State.DEAD);
                updateHumanGrid[row][col] = human;

            }
        }


        /**
         * Copy Old Human grid state into new human grid.
         */
        int nextRow = this.universeGridRow/2;
        int nextCol = this.universeGridCol/2;
        for (int r=0; r<this.universeGridRow; r++) {

            for (int c = 0; c < this.universeGridCol; c++) {

                if (this.universeGrid[r][c].getState().equals(State.ALIVE)) {
                    updateHumanGrid[nextRow + r][nextCol + c].setState(State.ALIVE);
                }

            }
        }

        this.universeGridRow = updatedUniverseGridRow;
        this.universeGridCol = updatedUniverseGridCol;
        this.universeGrid = updateHumanGrid;

    }

    /**
     * Update the current seed of the universe using the given next generation.s
     * @param nextGeneration
     */
    public void updateSeed(byte[][] nextGeneration) {

        int row = nextGeneration.length;
        int col = nextGeneration[0].length;

        this.seed.setRow(row);
        this.seed.setCol(col);
        this.seed.setPattern(nextGeneration);
    }

    public Human[][] getUniverseGrid() {
        return universeGrid;
    }

    public int getUniverseGridRow() {
        return universeGridRow;
    }

    public int getUniverseGridCol() {
        return universeGridCol;
    }
}
