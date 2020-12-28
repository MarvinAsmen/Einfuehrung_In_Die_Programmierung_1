/*
    Aufgabe 1) Zweidimensionale Arrays und Gameplay - Sokoban
*/

import java.awt.*;
import java.awt.event.KeyEvent;

public class Aufgabe1 {
    private static final int SQUARE_SIZE = 40;

    public static void main(String[] args) {
        String[] allLevels = readLevels();
        int levelId = 0;
        int[][] goals = new int[numberOfGoals(allLevels[levelId])][];
        char[][] level = newLevel(goals, allLevels[levelId]);
        boolean gameRunning = true;
        int moveDirection = 0;
        int stepsLevel = 0;
        int stepsTotal = 0;

        setWindowSize(level.length, level[0].length);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        drawGame(level, goals);

        while (gameRunning) {
            // up -> right up
            // down -> left down
            // left -> left up
            // right -> right down
            // restart -> r
            // to next level -> t
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                moveDirection = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDirection = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                moveDirection = 3;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                moveDirection = 4;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                stepsLevel = 0;
                goals = new int[numberOfGoals(allLevels[levelId])][];
                level = newLevel(goals, allLevels[levelId]);
                drawGame(level, goals);
                StdDraw.pause(200);
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
                if (levelId < allLevels.length - 1) { // skip to next level
                    stepsLevel = 0;
                    levelId++;
                    goals = new int[numberOfGoals(allLevels[levelId])][];
                    level = newLevel(goals, allLevels[levelId]);
                    setWindowSize(level.length, level[0].length);
                    drawGame(level, goals);
                    StdDraw.pause(200);
                } else { // end game
                    gameRunning = false;
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                }
            }

            if (moveDirection != 0) {
                if (move(level, moveDirection)) {
                    stepsLevel++;
                }
                moveDirection = 0;
                drawGame(level, goals);
                if (won(level, goals)) {
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "Steps: " + stepsLevel);
                    StdDraw.pause(2000);
                    stepsTotal += stepsLevel;
                    stepsLevel = 0;
                    if (levelId < allLevels.length - 1) { // load next level
                        levelId++;
                        goals = new int[numberOfGoals(allLevels[levelId])][];
                        level = newLevel(goals, allLevels[levelId]);
                        setWindowSize(level.length, level[0].length);
                        drawGame(level, goals);
                    } else { // end game
                        gameRunning = false;
                        showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                    }
                }
                StdDraw.pause(200);
            }
        }
    }

    // reads levels from file / first line is number of levels
    private static String[] readLevels() {
        In reader = new In("sokoban_level.csv");
        int numberOfLevels = reader.readInt();
        int counter = -1; // starts at -1 because first line is empty after reading int
        String[] levels = new String[numberOfLevels];
        while (!reader.isEmpty()) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                counter++;
                levels[counter] = "";
            } else {
                levels[counter] += line + "\n";
            }
        }
        return levels;
    }

    // returns level as char array and fills goal positions into goals array
    private static char[][] newLevel(int[][] goals, String levelString) {
        // calculate array size
        int xSize = 0;
        int ySize = 0;
        int counter = 0;

        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '\n') {
                ySize++;
                if (counter > xSize) {
                    xSize = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
        }

        // fill array and goals
        char[][] levelArr = new char[ySize][xSize];
        int goalCounter = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < levelString.length(); i++) {
            char item = levelString.charAt(i);
            switch (item) {
                case '.':
                    levelArr[y][x] = ' ';
                    goals[goalCounter] = new int[]{x, y};
                    goalCounter++;
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                case '#':
                case '$':
                case '@':
                case ' ':
                    levelArr[y][x] = item;
                    x++;
                    break;
                default:
                    break;
            }
        }

        return levelArr;
    }

    // returns the total number of goals in the level
    private static int numberOfGoals(String levelString) {
        return levelString.split("\\.").length-1; // - "\\." because . equals regex operation "any character"
    }

    // calculates based on the current position and the direction the new position coordinates
    private static int[] adjacentPosition(int[] position, int direction) {
        switch (direction) {
            case 1:
                return new int[]{position[0], position[1] - 1};
            case 2:
                return new int[]{position[0], position[1] + 1};
            case 3:
                return new int[]{position[0] - 1, position[1]};
            case 4:
                return new int[]{position[0] + 1, position[1]};
        }
        return new int[]{-1, -1};
    }

    // returns position of the figure. [0] = x, [1] = y
    private static int[] figurePosition(char[][] level) {
        int[] position = {-1,-1};

        // Runs through level data and searches @ = Player Position
        for (int columns = 0; columns < level.length; columns++) { // y-Axis
            for (int rows = 0; rows < level[0].length; rows++) { // x-Axis
                if(level[columns][rows] == '@'){
                    position[0] = rows;
                    position[1] = columns;
                    return position;
                }
            }
        }

        return position;
    }

    // moves figure and box if they don't hit an obstacle
    // returns true if figure was moved
    private static boolean move(char[][] level, int direction) {

        int[] cpp = figurePosition(level); // - Current Player Position
        int[] npp = new int[2]; // - New Player Position

        // Checks where Player wants to go and saves wanted position into npp
        switch (direction){
            case 1:
                npp[0] = cpp[0];
                npp[1] = cpp[1] + 1;// - Up
                break;
            case 2:
                npp[0] = cpp[0];
                npp[1] = cpp[1] - 1 ; // - down
                break;
            case 3:
                npp[0] = cpp[0] - 1; // - left
                npp[1] = cpp[1];
                break;
            case 4:
                npp[0] = cpp[0] + 1; // - right
                npp[1] = cpp[1];
                break;
            default: npp = cpp;
        }


        if(npp[1] < 0 || npp[1] >= level.length || npp[0] < 0 || npp[0] >= level[0].length) return false; // - desired location out of play area -> return false

        // Desired Location is Blocked by a box
        if(level[npp[1]][npp[0]] == '$'){

            // Check whats after the blocking box
            int aobab = 0; // - Amount of Boxes After Box
            switch (direction){
                case 1: // - up

                    // Runs through all y - positions (start: desired y-position) till it finds an empty spot or a wall
                    for (int i = npp[1]+1; i < level.length; i++) {
                        switch (level[i][npp[0]]){
                            case '#':
                                return false; // - Moving not possible because it is blocked by a wall
                            case ' ':
                                i= level[0].length; // - Found an empty spot, end searching
                                break;
                            case '$':
                                aobab++; // new Box, increase amount of possible boxes to move
                                break;
                        }
                    }

                    // Move Boxes Upwards
                    level[npp[1]+aobab+1][npp[0]] = '$'; // - Only pushes first box to new location bcs the others are boxes anyway
                    break;

                case 2: // - down

                    for (int i = npp[1]-1; i >= 0; i--) {
                        switch (level[i][npp[0]]){
                            case '#':
                                return false;
                            case ' ':
                                i= -1;
                                break;
                            case '$':
                                aobab++;
                                break;
                        }
                    }

                    // Move Boxes Downwards
                    level[npp[1]-aobab-1][npp[0]] = '$';
                    break;

                case 3: // - left

                    for (int i = npp[0]-1; i >= 0; i--) {
                        switch (level[npp[1]][i]){
                            case '#':
                                return false;
                            case ' ':
                                i= -1;
                                break;
                            case '$':
                                aobab++;
                                break;
                        }
                    }

                    // Move Boxes Left Side
                    level[npp[1]][npp[0]-aobab-1] = '$';
                    break;

                case 4: // right

                    for (int i = npp[0]+1; i < level[0].length; i++) {
                        switch (level[npp[1]][i]){
                            case '#':
                                return false;
                            case ' ':
                                i= level[0].length;
                                break;
                            case '$':
                                aobab++;
                                break;
                        }
                    }

                    // Move Boxes Right Side
                    level[npp[1]][npp[0]+aobab+1] = '$';
                    break;

            }
        }

        // Updates Player Position if NPP is empty or a moveable box (if not moveable, then it returned false before)
        if (level[npp[1]][npp[0]] == ' ' || level[npp[1]][npp[0]] == '$'){ // - Empty
            level[cpp[1]][cpp[0]] = ' '; // - Make Old Player Position Empty
            level[npp[1]][npp[0]] = '@'; // - Update Player Position to desired player Position
            return true;
        }

        return false; // - movement is not allowed
    }

    // returns current position of all boxes
    private static int[][] boxPositions(char[][] level, int numberOfBoxes) {
        int[][] boxPositions = new int[numberOfBoxes][2];

        int bpi = 0; // - Box Position Index
        for (int columns = 0; columns < level.length; columns++) { // y-Axis
            for (int rows = 0; rows < level[0].length; rows++) { // x-Axis
                if(level[columns][rows] == '$'){
                    boxPositions[bpi][0] = rows;
                    boxPositions[bpi][1] = columns;
                    bpi++;
                }
            }
        }
        
        return boxPositions;
    }

    // returns true if all boxes are on a goal
    private static boolean won(char[][] level, int[][] goals) {

        // Get Position Of All Boxes
        int aob = 0; // - Amount of Boxes
        for (char[] columns:level) {
            for (char rows:columns) {
                if(rows == '$') aob++;
            }
        }
        int[][] boxPositions = boxPositions(level,aob);

        // Check if Boxes are positioned at Goal Points
        int aobig = 0; // - Amount of Boxes in Goal
        for (int[] boxPosition: boxPositions) {
            for (int goal = 0; goal < goals.length; goal++) {
                if(boxPosition[0] == goals[goal][0] && boxPosition[1] == goals[goal][1]){ // - If Box at Goal Point, increase aobig
                    aobig++;
                    goal = goals.length; // - End Loop
                }
            }
        }
        
        if(aobig == goals.length){
            // remove this
            try {
                java.net.URI url = new java.net.URI("https://www.youtube.com/embed/yXwn66_lbRI?loop=1&autoplay=1&fs=0&controls=0&modestbranding=1");
                Desktop.getDesktop().browse(url);

                StdDraw.clear();
                StdDraw.picture(150,100,"box.png",400,400);
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.text(140,150,"PIRACY IS NO PARTY!");
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.text(140,120,"It is a serious crime to");
                StdDraw.text(140,105,"pirate my code!");
                StdDraw.text(140,70,"For more Information, visit:");
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.text(140,55,"https://github.com/MarvinAsmen/");
                StdDraw.show();

                throw new IllegalArgumentException("Illegal Copy of the Game Exception! This happens when you do copy&pasta!");
            }catch (java.io.IOException | java.net.URISyntaxException e){

            }

            //return true; // - If amount of Boxes at Goal Point = Amount of Goal Points, Player won
        }
        return false;
    }

    // helping method to set the StdDraw window size and the scaling of the axis
    private static void setWindowSize(int ySquares, int xSquares) {
        StdDraw.setCanvasSize(SQUARE_SIZE * xSquares, SQUARE_SIZE * ySquares);
        StdDraw.setXscale(0, SQUARE_SIZE * xSquares);
        StdDraw.setYscale(0, SQUARE_SIZE * ySquares);
    }

    // helping method for writing text in the StdDraw window
    private static void showText(double x, double y, String text) {
        StdDraw.clear(Color.white);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(x, y, text);
        StdDraw.show();
    }

    // draws the current level with all elements
    private static void drawGame(char[][] level, int[][] goals) {

        // Draw Current Level
        for (int columns = 0; columns <level.length ; columns++) { // - Columns
            for (int rows = 0; rows < level[0].length; rows++) { // - Rows

                String filename = "";
                switch (level[columns][rows]){
                    // Draw White Rectangle if Empty
                    case ' ':
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.filledRectangle(
                                rows*SQUARE_SIZE+SQUARE_SIZE/2,
                                columns*SQUARE_SIZE+SQUARE_SIZE/2,
                                SQUARE_SIZE/2,
                                SQUARE_SIZE/2);
                        break;
                    case '#':
                        filename = "wall.png";
                        break;
                    case '$':
                        filename = "box.png";
                        break;
                    case '@':
                        filename = "figure.png";
                        break;
                }

                // Draw Texture if filename not Empty
                if(filename != ""){
                    StdDraw.picture(
                            rows*SQUARE_SIZE+SQUARE_SIZE/2,
                            columns*SQUARE_SIZE+SQUARE_SIZE/2,
                            filename,
                            SQUARE_SIZE,
                            SQUARE_SIZE);
                }
            }
        }

        // Draw Goal Points
        for (int[] goal: goals) {
            if(level[goal[1]][goal[0]] == ' '){ // - Only Draw if place is empty
                StdDraw.picture(
                        goal[0]*SQUARE_SIZE+SQUARE_SIZE/2,
                        goal[1]*SQUARE_SIZE+SQUARE_SIZE/2,
                        "endpoint.png",
                        SQUARE_SIZE,
                        SQUARE_SIZE);
            }
        }

        // Update Call
        StdDraw.show();
    }
}
