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

	private double[] start = new double[2];
	private int loop;


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
				update();
				moveToClosestObstacle();
				update();
				while(running){
					if(checkWall()) {
						followWall();
					}else{
						update();
					}
					checkPosition();
				}
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
		int direction = (int) closestObstacle[0];
		String command = "ROTATE";
		switch(direction){
			case 0:
				// rijdt vooruit
				command = "";
				break;
			case 90:
			case 180:
				command = "ROTATERIGHT " + direction;
				break;
			case 270:
				command = "ROTATELEFT " + (360 - direction);
				break;
		}
		robot.sendCommand("P1." + command);
		result = input.readLine();

		robot.sendCommand("P1.MOVEFW " + (closestObstacle[1] - 10));
		result = input.readLine();

		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);

		start[0] = position[0];
		start[1] = position[1];
	}

	private void checkPosition() throws IOException {
//		double offset = 20.0;
//		double[][] surroundings = {
//				{start[0] - offset, start[1]},
//				{start[0] - offset, start[1] + offset},
//				{start[0]	, start[1] + offset},
//				{start[0] + offset, start[1] + offset},
//				{start[0], start[1]},
//				{start[0] + offset, start[1]},
//				{start[0] + offset, start[1] - offset},
//				{start[0]	, start[1] - offset},
//				{start[0] - offset, start[1] - offset}
//		};
//
//		for (double[] surrounding: surroundings){
//			if (surrounding[0] == position[0] && surrounding[1] == position[1]){
//				this.running = false;
//			}
//		}
		double range = 9.0;
		double deltaY = position[0] - start[0];
		double deltaX = position[1] - start[1];

		if (deltaY < 0.0) deltaY = deltaY * - 1.0;
		if (deltaX < 0.0) deltaX = deltaX * - 1.0;


		if(deltaX <= range && deltaY <= range) {
			this.running = false;
		}
	}

	private boolean checkWall() throws IOException {
		boolean validWall = false;
		//int column = (int) position[0];
		//int row = (int) position[1];

		int robotDir = (int) position[2];
		int wallRange = (int) measures[90];
			if (measures[0] <= 30){
				robot.sendCommand("P1.ROTATELEFT 90");
				result = input.readLine();
				validWall = true;
			}else if ((measures[0] > 30 && measures[90] > 30) || (measures[0] >= 100 && measures[90] >= 100)){
				if(measures[45] <= 30){
					robot.sendCommand("P1.MOVEFW 20");
					result = input.readLine();
				}else {
					robot.sendCommand("P1.MOVEFW 20");
					result = input.readLine();

					robot.sendCommand("P1.ROTATERIGHT 90");
					result = input.readLine();

					robot.sendCommand("R1.GETPOS");
					result = input.readLine();
					parsePosition(result, position);

					robot.sendCommand("L1.SCAN");
					result = input.readLine();
					parseMeasures(result, measures);
					map.drawLaserScan(position, measures);

					if(measures[0] <= 30){
						robot.sendCommand("P1.MOVEFW 10");
						result = input.readLine();
					}else {
						robot.sendCommand("P1.MOVEFW 20");
						result = input.readLine();
					}
				}
				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);
			}else if (measures[0] >= (wallRange-1)){
				validWall = true;
			}
			else{
				robot.sendCommand("P1.MOVEFW 10");
				result = input.readLine();
			}
		robot.sendCommand("R1.GETPOS");
		result = input.readLine();
		parsePosition(result, position);
		return validWall;
	}

	private void followWall() throws IOException{
		if(measures[0] > 40) {
			robot.sendCommand("P1.MOVEFW 20");
			result = input.readLine();
		}else{
			robot.sendCommand("P1.MOVEFW 10");
			result = input.readLine();
		}
		update();
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

				if(direction == 0 || direction == 90 || direction == 180 || direction == 270){
					if (distance < closestObstacle[1]){
						closestObstacle[0] = direction;
						closestObstacle[1] = distance;
					}
				}
			}
		}
	}
}
