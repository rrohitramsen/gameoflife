package com.test.gameoflife.helper;

import com.test.gameoflife.game.InvalidInputFileException;
import com.test.gameoflife.game.Seed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

;

/**
 * Created by rohitkumar on 01/01/18.
 */
public class SeedFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeedFileReader.class);


    /**
     * Read seed from input file name.
     * @param fileName
     * @return Seed
     * @throws IOException
     */
    public static Seed readSeedFromFile(String fileName) throws IOException {

        if (Objects.isNull(fileName) || fileName.isEmpty()) {
            LOGGER.info("File name is null or empty."+fileName);
            throw new FileNotFoundException("File name must not be empty or null");
        }

        BufferedReader buffer = Files.newBufferedReader(Paths.get(fileName));

        List<String[]> stringArrayList = new ArrayList<String[]>();
        int ticks;

        String line = buffer.readLine();
        if (Objects.nonNull(line)) {
            ticks = Integer.parseInt(line.trim());
        } else {
            LOGGER.warn("File is Empty");
            throw new InvalidInputFileException("File is Empty.");
        }

        while ((line = buffer.readLine()) != null) {
            String[] lineValues = line.trim().split("");
            stringArrayList.add(lineValues);
        }

        byte[][] inputGrid = convertStringToByteMatrix(stringArrayList);

        return new Seed(ticks, inputGrid.length, inputGrid[0].length, inputGrid);

    }

    /**
     * Convert string matrix to byte matrix.
     * @param stringMatrix
     * @return
     */
    private static byte[][] convertStringToByteMatrix(List<String[]> stringMatrix) {

        LOGGER.info("Converting String Pattern(Matrix) to Byte Pattern(Matrix).");

        int row = stringMatrix.size();
        int col = stringMatrix.get(0).length;

        byte[][] byteMatrix = new byte[row][col];

        for (int r=0; r<row; r++) {

            for (int c=0; c<col; c++) {

                byteMatrix[r][c] = Byte.parseByte(stringMatrix.get(r)[c]);
            }
        }

        return byteMatrix;
    }


}
