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
				System.out.println("intelligence running");

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
				update();
				moveToClosestObstacle();
				followWall(checkSurroundingObstacles((int)position[0], (int)position[1]));

//				for (int row= 0 ; row < grid.length; row++){
//					for (int col = 0; col < grid[row].length; col++){
//						if (grid[row][col] == map.getObstacle()) {
//							checkSurroundingObstacles(row, col);
//						}
//					}
//				}



				this.running = false;
			} catch (IOException ioe) {
				System.err.println("execution stopped");
				running = false;
			}
		}

	}

	private void update() throws IOException {
		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);

		robot.sendCommand("L1.SCAN");
		result = input.readLine();
		parseMeasures(result, measures);
		map.drawLaserScan(position, measures);

		grid = map.getGrid();
	}

	private void moveToClosestObstacle() throws IOException {
		robot.sendCommand("P1.ROTATERIGHT " + closestObstacle[0]);
		result = input.readLine();

		robot.sendCommand("P1.MOVEFW " + (closestObstacle[1]));
		result = input.readLine();

		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);
	}


	private int checkSurroundingObstacles(int row, int column) throws IOException {
		int [] [] surroundings = {
				{row - 1, column},		// top
				{row	, column + 1},	// right
				{row + 1, column},		// bottom
				{row	, column - 1}	// left
		};
		int direction = -1;

		for (int i = 0; i < surroundings.length; i++){
			try {
				if (grid[surroundings[i][0]][surroundings[i][1]] == map.getObstacle()){
					direction = i;
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
				continue;
			}
		}

		if (direction == -1) {
			checkSurroundingObstacles(row, column);
		}

		return direction;
	}

	private void followWall(int direction) throws IOException{
		System.out.println(direction);
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
				System.out.println("direction = " + direction + " distance = " + distance);

				if (distance < closestObstacle[1]){
					closestObstacle[0] = direction;
					closestObstacle[1] = distance;
				}
			}
		}
	}
}
