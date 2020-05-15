package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);



    // Initialize the tiles
    private static void fillNOTHING(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    //
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(8);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.WATER;
            case 5: return Tileset.LOCKED_DOOR;
            case 6: return Tileset.TREE;
            case 7: return Tileset.PLAYER;
            default: return Tileset.SAND;
        }
    }

    private static int width(int nCall, int s) {
        return 2 * s + (s - 2) - 2 * nCall;

    }

    // Tests for helper methods above
    @Test
    public void testWidth() {
        assertEquals(4, width(0, 2));
        assertEquals(2, width(1, 2));
        assertEquals(13, width(0, 5));
        assertEquals(11, width(1, 5));
        assertEquals(9, width(2, 5));
        assertEquals(7, width(3, 5));
        assertEquals(5, width(4, 5));
    }
    
    private static void recursiveFillLines(int nCall, int s, TETile[][] world, int xMiddle, int yMiddle, TETile tile) {
        if (nCall == s) {
            return;
        }
        int width = width(nCall, s);
        int xUpper1 = xMiddle + nCall;
        int yUpper1 = yMiddle + nCall;
        int yUpper2 = yMiddle - nCall - 1;
        fillLine(width, world, xUpper1, yUpper1, tile);
        fillLine(width, world, xUpper1, yUpper2, tile);
        recursiveFillLines(nCall + 1, s, world, xMiddle, yMiddle, tile);

    }

    private static void fillLine(int width, TETile[][] world, int xUpper, int yUpper, TETile tile) {
        for (int i = 0; i < width; i++) {
            world[xUpper + i][yUpper] = tile;
        }
    }


    /**
     * Add a hexagon of side length s to a specified position in the world
     * @param s             side length of the hexagon
     * @param world         world to which the hexagon will be added
     * @param xMiddle
     * @param yMiddle
     */
    private static void addHexagon(int s, TETile[][] world, int xMiddle, int yMiddle) {
        TETile tile = randomTile();
        recursiveFillLines(0, s, world, xMiddle, yMiddle, tile);
    }

    // helper method for tessellate
    private static void expand(int nExpand, TETile[][] world, int xMiddle, int yMiddle) {
        addHexagon(3, world, xMiddle, yMiddle + 6);
        addHexagon(3, world, xMiddle + 5, yMiddle + 3);
        addHexagon(3, world, xMiddle + 5, yMiddle - 3);
        addHexagon(3, world, xMiddle, yMiddle - 6);
        addHexagon(3, world, xMiddle - 5, yMiddle - 3);
        addHexagon(3, world, xMiddle - 5, yMiddle + 3);
        recursiveExpand(nExpand - 1, world, xMiddle, yMiddle + 6);
        recursiveExpand(nExpand - 1, world, xMiddle + 5, yMiddle + 3);
        recursiveExpand(nExpand - 1, world, xMiddle + 5, yMiddle - 3);
        recursiveExpand(nExpand - 1, world, xMiddle, yMiddle - 6);
        recursiveExpand(nExpand - 1, world, xMiddle - 5, yMiddle - 3);
        recursiveExpand(nExpand - 1, world, xMiddle - 5, yMiddle + 3);
    }

    private static void recursiveExpand(int nExpand, TETile[][] world, int xUpper, int yUpper) {
        if (nExpand == 0) {
            return;
        }
        addHexagon(3, world, xUpper, yUpper);
        expand(nExpand, world, xUpper, yUpper);
    }

    private static void tessellate(TETile[][] world) {
        recursiveExpand(2, world, 20, 20);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexagon = new TETile[WIDTH][HEIGHT];
        fillNOTHING(hexagon);
        tessellate(hexagon);
        //addHexagon(5, hexagon, 10, 10);
        ter.renderFrame(hexagon);

    }
}
