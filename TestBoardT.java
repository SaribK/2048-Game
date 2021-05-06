package src;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class TestBoardT {

    private BoardT model;

    private int[][] board = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    @Before
    public void setUp()
    {
        model = new BoardT(board);
    }

    @After
    public void tearDown()
    {
        model = null;
    }

    @Test
    public void test_getBoard()
    {
        assertTrue(Arrays.deepEquals(model.getBoard(), new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
    }

    @Test
    public void test_getStatus()
    {
        assertFalse(model.getStatus());
    }

    @Test
    public void test_getStatus2()
    {
        model.addValue();
        assertTrue(model.getStatus());
    }

    @Test
    public void test_getStatus3()
    {
        int[][] temp = new int[][]{{2, 4, 8, 16}, {16, 8, 4, 2}, {4, 2, 16, 8}, {8, 16, 2, 4}};
        model = new BoardT(temp);
        assertFalse(model.getStatus());
    }

    @Test
    // normal case: adds 1 value
    public void test_addValue()
    {
        int count = 0;
        model.addValue();
        for (int[] row : model.getBoard())
        {
            for (int num : row)
            {
                if (num != 0)
                    count += 1;
            }
        }
        assertEquals(count, 1);
    }

    @Test
    // normal case: adds 3 values
    public void test_addValue2()
    {
        int count = 0;
        model.addValue();
        model.addValue();
        model.addValue();
        for (int[] row : model.getBoard())
        {
            for (int num : row)
            {
                if (num != 0)
                    count += 1;
            }
        }
        assertEquals(count, 3);
    }

    @Test
    // edge case: adding 20 values to a board that can only fit 16 values
    public void test_addValue3()
    {
        int count = 0;
        for (int i = 0; i < 20; i++)
            model.addValue();
        for (int[] row : model.getBoard())
        {
            for (int num : row)
            {
                if (num != 0)
                    count += 1;
            }
        }
        assertEquals(count, 16);
    }

    @Test
    // boundary case: adds exactly 16 values into 4x4 grid
    public void test_addValue4()
    {
        int count = 0;
        for (int i = 0; i < 16; i++)
            model.addValue();
        for (int[] row : model.getBoard())
        {
            for (int num : row)
            {
                if (num != 0)
                    count += 1;
            }
        }
        assertEquals(count, 16);
    }

    @Test
    // normal case: just initialized it, score should be 0
    public void test_getScore()
    {
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case: board should not change
    public void test_shiftLeft()
    {
        int[][] temp = copyArray();
        model.shiftBoard(ControlsT.left);
        assertTrue(Arrays.deepEquals(temp, model.getBoard()));
    }

    @Test
    // normal case: board should not change
    public void test_getScore2()
    {
        model.shiftBoard(ControlsT.left);
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case: board should not change
    public void test_shiftRight()
    {
        int[][] temp = copyArray();
        model.shiftBoard(ControlsT.right);
        assertTrue(Arrays.deepEquals(temp, model.getBoard()));
    }

    @Test
    // normal case: board should not change
    public void test_getScore3()
    {
        model.shiftBoard(ControlsT.right);
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case: board should not change
    public void test_shiftUp()
    {
        int[][] temp = copyArray();
        model.shiftBoard(ControlsT.up);
        assertTrue(Arrays.deepEquals(temp, model.getBoard()));
    }

    @Test
    // normal case: board should not change
    public void test_getScore4()
    {
        model.shiftBoard(ControlsT.up);
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case: board should not change
    public void test_shiftDown()
    {
        int[][] temp = copyArray();
        model.shiftBoard(ControlsT.down);
        assertTrue(Arrays.deepEquals(temp, model.getBoard()));
    }

    @Test
    // normal case: board should not change
    public void test_getScore5()
    {
        model.shiftBoard(ControlsT.down);
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case
    public void test_shiftLeft2()
    {
        model = new BoardT(new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.left);
        assertTrue(Arrays.deepEquals(new int[][] {{4,0,0,0}, {4,0,0,0}, {8,4,0,0}, {0,0,0,0}}, model.getBoard()));
    }

    @Test
    // normal case
    public void test_getScore6()
    {
        int[][] temp = new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}};
        model = new BoardT(temp);
        model.shiftBoard(ControlsT.left);
        assertEquals(20, model.getScore());
    }

    @Test
    // normal case
    public void test_shiftRight2()
    {
        model = new BoardT(new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.right);
        assertTrue(Arrays.deepEquals(new int[][] {{0,0,0,4}, {0,0,0,4}, {0,0,8,4}, {0,0,0,0}}, model.getBoard()));
    }

    @Test
    // normal case
    public void test_getScore7()
    {
        int[][] temp = new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}};
        model = new BoardT(temp);
        model.shiftBoard(ControlsT.right);
        assertEquals(20, model.getScore());
    }

    @Test
    // normal case
    public void test_shiftUp2()
    {
        model = new BoardT(new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.up);
        assertTrue(Arrays.deepEquals(new int[][] {{4,2,4,2}, {4,4,0,0}, {0,0,0,0}, {0,0,0,0}}, model.getBoard()));
    }

    @Test
    // normal case
    public void test_getScore8()
    {
        int[][] temp = new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}};
        model = new BoardT(temp);
        model.shiftBoard(ControlsT.up);
        assertEquals(8, model.getScore());
    }

    @Test
    // normal case
    public void test_shiftDown2()
    {
        model = new BoardT(new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.down);
        assertTrue(Arrays.deepEquals(new int[][] {{0,0,0,0}, {0,0,0,0}, {4,2,0,0}, {4,4,4,2}}, model.getBoard()));
    }

    @Test
    // normal case
    public void test_getScore9()
    {
        int[][] temp = new int[][]{{2, 2, 0, 0}, {2, 0, 2, 0}, {4, 4, 2, 2}, {0, 0, 0, 0}};
        model = new BoardT(temp);
        model.shiftBoard(ControlsT.down);
        assertEquals(8, model.getScore());
    }

    @Test
    // edge case: big numbers
    public void test_shiftLeft3()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.left);
        assertTrue(Arrays.deepEquals(new int[][] {{4096,0,0,0}, {8192,0,0,0}, {4,2,0,0}, {0,0,0,0}}, model.getBoard()));
    }

    @Test
    // edge case: big numbers
    public void test_getScore10()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.left);
        assertEquals(12288, model.getScore());
    }

    @Test
    // edge case: big numbers
    public void test_shiftRight3()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.right);
        assertTrue(Arrays.deepEquals(new int[][] {{0,0,0,4096}, {0,0,0,8192}, {0,0,4,2}, {0,0,0,0}}, model.getBoard()));
    }

    @Test
    // edge case: big numbers
    public void test_getScore11()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.right);
        assertEquals(12288, model.getScore());
    }

    @Test
    // edge case: big numbers
    public void test_shiftDown3()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.down);
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0, 0}, {0, 0, 0, 0}, {2048, 2048, 0, 4096}, {4096, 4, 0, 2}}, model.getBoard()));
    }

    @Test
    // edge case: big numbers
    public void test_getScore12()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.down);
        assertEquals(0, model.getScore());
    }

    @Test
    // edge case: big numbers
    public void test_shiftUp3()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.up);
        assertTrue(Arrays.deepEquals(new int[][] {{2048, 2048, 0, 4096}, {4096, 4, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}}, model.getBoard()));
    }

    @Test
    // edge case: big numbers
    public void test_getScore13()
    {
        model = new BoardT(new int[][]{{2048, 2048, 0, 0}, {4096, 0, 0, 4096}, {0, 4, 0, 2}, {0, 0, 0, 0}});
        model.shiftBoard(ControlsT.up);
        assertEquals(0, model.getScore());
    }

    @Test
    // normal case: checking for two values in constructor
    public void test_boardConstructor()
    {
        BoardT temp = new BoardT();
        int count = 0;
        for (int[] row : temp.getBoard())
        {
            for (int num : row)
            {
                if (num != 0)
                    count += 1;
            }
        }
        assertEquals(count, 2);
    }

    private int[][] copyArray()
    {
        int[][] temp = new int[BoardT.getSize()][BoardT.getSize()];
        for (int i = 0; i < BoardT.getSize(); ++i)
            for (int j = 0; j < BoardT.getSize(); ++j)
                temp[i][j] = model.getBoard()[i][j];
        return temp;
    }
}
