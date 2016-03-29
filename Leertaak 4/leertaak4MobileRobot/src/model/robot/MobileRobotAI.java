package model.robot;

import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Stack;
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
	private double[] measuresLaser;
	private double[] measuresSonar;

	private String result;

	private double[] start = new double[2];
	private double[] wall;


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
		measuresLaser = new double[360];
		measuresSonar = new double[360];
		while (running) {
			try {

				PipedInputStream pipeIn = new PipedInputStream();
				input = new BufferedReader(new InputStreamReader(pipeIn));
				PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

				robot.setOutput(output);
<<<<<<< Updated upstream
				System.out.println("intelligence running");
=======

//      ases where a variable value is never used after its assignment, i.e.:
//				System.out.println("intelligence running");

//				robot.sendCommand("R1.GETPOS");
//				result = input.readLine();
//				parsePosition(result, position);
//
//				robot.sendCommand("L1.SCAN");
//				result = input.readLine();
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);
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
//				parseMeasures(result, measuresLaser);
//				map.drawLaserScan(position, measuresLaser);


>>>>>>> Stashed changes
				update();
				moveToClosestObstacle();
				wall = getWall();
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

		robot.sendCommand("S1.PULSE");
		result = input.readLine();
		parseMeasures(result, measuresSonar);
		map.drawLaserScan(position, measuresSonar);

		robot.sendCommand("L1.SCAN");
		result = input.readLine();
		parseMeasures(result, measuresLaser);
		map.drawLaserScan(position, measuresLaser);

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
		LinkedList linkedList = new LinkedList();

		while (){
			double x = wall[0];
			double y = wall[1];

			double [] [] surroundings = {
					{x - 10, y},		// top
					{x	, y + 10},		// right
					{x + 10, y},		// bottom
					{x 	, y - 10}		// left
			};

			for (double[] surrounding: surroundings){
				if(grid[(int)surrounding[0]][(int)surrounding[1]] == map.getObstacle()) {
					linkedList.add(surroundings[0]);
					break;
				}
			}
		}




//		double range = 9.0;
//		double deltaY = position[0] - start[0];
//		double deltaX = position[1] - start[1];
//
//		if (deltaY < 0.0) deltaY = deltaY * - 1.0;
//		if (deltaX < 0.0) deltaX = deltaX * - 1.0;
//
//
//		if(deltaX <= range && deltaY <= range) {
//			this.running = false;
//		}
	}

	private double[] getWall(){
		double[] wall = new double[2];
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[i].length;j++) {
				if (grid[i][j] == map.getObstacle()){
					wall[0] = i;
					wall[1] = j;
					return wall;
				}
			}
		}
		return wall;
	}

	private boolean checkWall() throws IOException {
		boolean validWall = false;
		//int column = (int) position[0];
		//int row = (int) position[1];

		int wallRangeSon = (int) measuresSonar[90];
		int wallRangeLas = (int) measuresLaser[90];
			if (measuresLaser[0] <= 20 || measuresSonar[0] <= 20){
				robot.sendCommand("P1.ROTATELEFT 90");
				result = input.readLine();
				validWall = true;
			}else if (measuresLaser[90] <= 30 || measuresSonar[90] <= 30) {
				validWall = true;
			}else if ((measuresLaser[0] > 30 && measuresLaser[90] > 30) || (measuresLaser[0] >= 100 && measuresLaser[90] >= 100)){
				if(measuresLaser[45] <= 30 || measuresSonar[45] <= 30){
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

					robot.sendCommand("S1.PULSE");
					result = input.readLine();
					parseMeasures(result, measuresSonar);
					map.drawLaserScan(position, measuresSonar);

					robot.sendCommand("L1.SCAN");
					result = input.readLine();
					parseMeasures(result, measuresLaser);
					map.drawLaserScan(position, measuresLaser);

					if(measuresLaser[0] <= 30 || measuresSonar[0] <= 30){
						if(measuresLaser[0] > 10 || measuresSonar[0] > 10){
							robot.sendCommand("P1.MOVEFW 10");
							result = input.readLine();
						}
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
				parseMeasures(result, measuresLaser);
				map.drawLaserScan(position, measuresLaser);
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
		if(measuresLaser[0] > 40 && measuresSonar[0] > 40) {
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
				measures[direction] = Math.round(distance);
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
