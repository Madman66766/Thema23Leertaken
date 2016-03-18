package model.robot;

import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.StringTokenizer;

/**
 * Title    :   The Mobile Robot Explorer Simulation Environment v2.0
 * Copyright:   GNU General Public License as published by the Free Software Foundation
 * Company  :   Hanze University of Applied Sciences
 *
 * @author Dustin Meijer        (2012)
 * @author Alexander Jeurissen  (2012)
 * @author Davide Brugali       (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

	private final int OFFSET = 10;

	private final OccupancyMap map;
	private final MobileRobot robot;

	private boolean running;

	private BufferedReader input;
	private char[][] grid;
	private double[] position;
	private double[] measures;

	private String result;

	/**
	 * closestObstacle[0] = direction to closest obstacle
	 * closestObstacle[1] = distance to closest obstacle
	 */
	private double[] closestObstacle = new double[2];

	private boolean hasWallToFollow = false;

	public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
		this.map = map;
		this.robot = robot;

	}

	/**
	 * In this method the gui.controller sends commands to the robot and its devices.
	 * At the moment all the commands are hardcoded.
	 * The exercise is to let the gui.controller make intelligent decisions based on
	 * what has been discovered so far. This information is contained in the OccupancyMap.
	 */
	public void run() {
		this.running = true;
		position = new double[3];
		measures = new double[360];

		while (running) {
			try {

				PipedInputStream pipeIn = new PipedInputStream();
				input = new BufferedReader(new InputStreamReader(pipeIn));
				PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

				robot.setOutput(output);

//      ases where a variable value is never used after its assignment, i.e.:
//				System.out.println("intelligence running");

//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.MOVEBW 60");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.ROTATERIGHT 90");
//				result = input.readLine();
//
//				robot.sendCommand("P1.MOVEFW 100");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.ROTATELEFT 45");
//				result = input.readLine();
//
//				robot.sendCommand("P1.MOVEFW 70");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.MOVEFW 70");
//				result = input.readLine();
//
//				robot.sendCommand("P1.ROTATERIGHT 45");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.MOVEFW 90");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.ROTATERIGHT 45");
//				result = input.readLine();
//
//				robot.sendCommand("P1.MOVEFW 90");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.ROTATERIGHT 45");
//				result = input.readLine();
//
//				robot.sendCommand("P1.MOVEFW 100");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.ROTATERIGHT 90");
//				result = input.readLine();
//
//				robot.sendCommand("P1.MOVEFW 80");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
//
//				robot.sendCommand("P1.MOVEFW 100");
//				result = input.readLine();
//
//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measures);
//				map.drawLaserScan(position, measures);
				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);

				if(!hasWallToFollow){
					findFall(100.0, 0);

				}  else {
					followWall();
				}
			} catch (IOException ioe) {
				System.err.println("execution stopped");
				running = false;
			}
		}

	}

	private void followWall() throws IOException {
		if (measures[0] < 10.0 && measures[90] != 100.0) {
			robot.sendCommand("P1.ROTATELEFT " + 90);
			result = input.readLine();
		} else if (measures[90] != 100.0) {
			int distance = (int) measures[0];

			if (distance > 30) {
				distance = 30;
			}

			robot.sendCommand("P1.MOVEFW " + (distance - 1));
			result = input.readLine();

		} else if (measures[90] == 100.0) {
			if (measures[0] < 30) {
				int distance = (int) measures[0];
				if (distance > 30)  {
					distance = 30;
				}
				robot.sendCommand("P1.MOVEFW " + (distance - 1));
				result = input.readLine();
			} else {
				robot.sendCommand("P1.MOVEFW 30");
				result = input.readLine();
			}

			robot.sendCommand("P1.ROTATERIGHT " + 90);
			result = input.readLine();

			int distance = (int) measures[270];

			if (distance > 30) {
				distance = 30;
			}
			robot.sendCommand("P1.MOVEFW " + distance);
			result = input.readLine();
		}
	}

	private void act() throws IOException{
		while (!isDone()) {
			scan();
			wall();
		}
		this.running = false;
	}

	private void findFall(double wallDistance, int wallDegree) throws IOException {
		for (int i = 0; i < 360; i++) {
			if (measures[i] < wallDistance) {
				wallDistance = measures[i];
				wallDegree = i;
			}
		}
		if (wallDistance == 100.0) {
			robot.sendCommand("P1.MOVEFW 100");
			result = input.readLine();
		} else {
			int distance = (int) wallDistance;
			distance = distance - 15;

			robot.sendCommand("P1.ROTATERIGHT " + wallDegree);
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW " + distance);
			result = input.readLine();

			robot.sendCommand("P1.ROTATELEFT " + 90);
			result = input.readLine();

			hasWallToFollow = true;
		}
	}

	private void scan() throws IOException {
		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);

		robot.sendCommand("L1.SCAN");
		result = input.readLine();
		parseMeasures(result, measures);
		map.drawLaserScan(position, measures);

		grid = map.getGrid();
	}


	private boolean isWall(int x, int y){
		return (grid[y/OFFSET][x/OFFSET] == map.getObstacle());
	}

	private void wall() throws IOException {
		int[] north = {(int)position[0] - OFFSET, (int)position[1]};
		int[] northEast = {(int)position[0] - OFFSET, (int)position[1] + OFFSET};
		int[] east = {(int)position[0], (int)position[1] + OFFSET};
		int[] southEast = {(int)position[0] + OFFSET, (int)position[1] + OFFSET};
		int[] south = {(int)position[0] + OFFSET, (int)position[1]};
		int[] southWest = {(int)position[0] + OFFSET, (int)position[1]  - OFFSET};
		int[] west = {(int)position[0], (int)position[1] - OFFSET};
		int[] northWest = {(int)position[0] - OFFSET, (int)position[1] - OFFSET};

		int[] left = new int[0];
//		int[] leftFront = new int[0];
		int[] front = new int[0];
//		int[] rightFront = new int[0];
		int[] right = new int[0];

		int[] back = new int[0];


		int direction = (int)Math.ceil(position[2]/10) *10;

		System.out.println("ROW: " + (int)position[0]);
		System.out.println("COL: " + (int)position[1]);
		System.out.println("DIR: " + (int)position[2]);

		switch (direction){
			case 90: //Going south
				left = east;
//				leftFront = southEast;
				front = south;
//				rightFront = southWest;
				right = west;
				back = north;
				break;
			case 180: //Going west
			case 190:
				left = south;
//				leftFront = southWest;
				front = west;
//				rightFront = northWest;
				right = north;
				back = east;
				break;
			case 270: //Going north
				left = west;
//				leftFront = northWest;
				front = north;
//				rightFront = northEast;
				right = east;
				back = south;
				break;
			case 360: // Going east
				left = north;
//				leftFront = northEast;
				front = east;
//				rightFront = southEast;
				right = south;
				back = west;
				break;
		}
		wall(left, front, right, back);

//		wall(left, leftFront, front, rightFront, right);
	}

	public void wall(int[] left, int[] front, int[] right, int[] back) throws IOException {
		if (isWall(right[0], right[1])){ //Top
			System.out.println("Found wall on the right");
			robot.sendCommand("P1.ROTATELEFT 90");
			result = input.readLine();
		} else if (isWall(front[0], front[0])){ //Left
			System.out.println("Found wall in front");
			if (!isWall(right[0], right[1])) {
				System.out.println("Walking ");
				robot.sendCommand("P1.MOVEFW " + OFFSET);
				result = input.readLine();
			}
		} else if (isWall(left[0], left[0])){
			System.out.println("Found wall on the left");
			if (!isWall(right[0], right[1])) {
				System.out.println("Walking ");
				robot.sendCommand("P1.MOVEFW " + OFFSET);
				result = input.readLine();
			}
		} else if(isWall(back[0], back[0])){
			System.out.println("Found wall in back");
		}

		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);

	}

	private void moveToClosestObstacle() throws IOException {
		robot.sendCommand("P1.ROTATERIGHT " + closestObstacle[0]);
		result = input.readLine();

		robot.sendCommand("P1.MOVEFW " + (closestObstacle[1] + OFFSET));
		result = input.readLine();

		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);
	}

	private int[] getCoordinatesOfSurroundingObstacles(int row, int column) throws IOException {
		int[] coordinates = new int[3];
		int[][] surroundings = {
				{row - OFFSET, column },	// left
				{row , column + OFFSET},	// bottom
				{row, column - OFFSET},		// top
				{row + OFFSET, column},		// right
		};

		for (int[] surrounding: surroundings) {
			if (grid[surrounding[0]/OFFSET][surrounding[1]/OFFSET] == map.getObstacle()){
				coordinates[0] = surrounding[0];
				coordinates[1] = surrounding[1];
			}
		}
		return coordinates;
	}

	private int[][] getDestinations(int[] coordinates) throws IOException {
		if (coordinates[0] == 0 && coordinates[1] == 0)
			throw  new IllegalArgumentException("Illegal coordinates given.");

		int[][] destination = new int[4][3];
		int[][] surroundings = {
				{coordinates[0] - OFFSET, coordinates[1]},		// left
				{coordinates[0], coordinates[1] + OFFSET},		// bottom
				{coordinates[0], coordinates[1] - OFFSET},		// top
				{coordinates[0] + OFFSET, coordinates[1]},		// right
		};

		int index = 0;
		for (int i = 0; i < surroundings.length; i++){
			if (grid[surroundings[i][0]/OFFSET][surroundings[i][1]/OFFSET] == map.getObstacle()){
				switch (i){
					case 0:		//left
						destination[index][0] = (int)position[0] - OFFSET;
						destination[index][1] = (int)position[1];
						break;
					case 1:		//bottom
						destination[index][0] = (int)position[0];
						destination[index][1] = (int)position[1]  + OFFSET;
						break;
					case 2:		//top
						destination[index][0] = (int)position[0];
						destination[index][1] = (int)position[1]  - OFFSET;
						break;
					case 3:		//right
						destination[index][0] = (int)position[0] + OFFSET ;
						destination[index][1] = (int)position[1];
					break;
				}
				destination[index][2] = i;
				index++;
			}
		}
		return destination;
	}

	private void move() throws IOException {
		move((int)position[0], (int)position[1], (int)Math.ceil(position[2]/10) * 10);
	}

	private void move(int row, int column, int direction) throws IOException {
		int[] coordinates = getCoordinatesOfSurroundingObstacles(row, column);

		try {
			int[][] destinations = getDestinations(coordinates);
			for (int i = 0; i < destinations.length; i++) {
				if (canMove(destinations[i][0], destinations[i][1])){
					int rotate = -1;
					switch (destinations[i][2]){
						case 0: //left
							switch (direction) {
								case 90: //bottom
									rotate = 90;
									break;
								case 180: //left
									rotate = 0;
									break;
								case 270: //top
									rotate = 270;
									break;
								case 360: //right
									rotate = 180;
									break;
							}
							break;
						case 1: //bottom
							switch (direction) {
								case 90: //bottom
									rotate = 0;
									break;
								case 180: //left
									rotate = 270;
									break;
								case 270: //top
									rotate = 180;
									break;
								case 360: //right
									rotate = 90;
									break;
							}
							break;
						case 2: //top
							switch (direction) {
								case 90: //bottom
									rotate = 180;
									break;
								case 180: //left
									rotate = 90;
									break;
								case 270: //top
									rotate = 0;
									break;
								case 360: //right
									rotate = 270;
									break;
							}
							break;
						case 3: //right
							switch (direction) {
								case 90: //bottom
									rotate = 270;
									break;
								case 180: //left
									rotate = 180;
									break;
								case 270: //top
									rotate = 90;
									break;
								case 360: //right
									rotate = 0;
									break;
							}
							break;
					}
					move(rotate);
					break;
				}
				break;
			}
		} catch (IllegalArgumentException ex) {

		}
	}

	private void move(int direction) throws IOException {
		if (direction == -1)
			return;

		robot.sendCommand("P1.ROTATERIGHT " + direction);
		result = input.readLine();

		robot.sendCommand("P1.MOVEFW " + OFFSET);
		result = input.readLine();

		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);
	}

	private boolean canMove(int row, int column){
		return (grid[row/OFFSET][column/OFFSET] == map.getEmpty());
	}

	private boolean isDone(){
		for (int row= 0 ; row < grid.length; row++){
			for (int col = 0; col < grid[row].length; col++){
				if (grid[row][col] == map.getUnknown()) {
					return false;
				}
			}
		}
		return true;
	}

	private void parsePosition(String value, double position[]) {
		int indexInit;
		int indexEnd;
		String parameter;

		indexInit = value.indexOf("X=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("Y=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("DIR=");
		parameter = value.substring(indexInit + 4);
		position[2] = Double.parseDouble(parameter);
	}

	private void parseMeasures(String value, double measures[]) {
		closestObstacle[1] = Double.MAX_VALUE;
		for (int i = 0; i < 360; i++) {
			measures[i] = 100.0;
		}
		if (value.length() >= 5) {
			value = value.substring(5);  // removes the "SCAN " keyword

			StringTokenizer tokenizer = new StringTokenizer(value, " ");

			double distance;
			int direction;
			while (tokenizer.hasMoreTokens()) {
				distance = Double.parseDouble(tokenizer.nextToken().substring(2));
				direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
				if (direction == 360) {
					direction = 0;
				}
				measures[direction] = distance;
				// Printing out all the degrees and what it encountered.
//				System.out.println("direction = " + direction + " distance = " + distance);

				if (distance < closestObstacle[1]){
					closestObstacle[0] = direction;
					closestObstacle[1] = distance;
				}
			}
		}
	}
}
