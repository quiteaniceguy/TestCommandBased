package org.usfirst.frc.team4132.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterStartLeftSideGoal extends CommandGroup {
	//double startX = 167;
	//double startY = 19;
	double startX = 0;
	double startY = 0;
	
	double[] pointsX =
		{
				100/*167,
				66,
				102*/
		}
			;
	double[] pointsY = 
		{
				0/*74,
				66,
				122*/
		}
			;
	public CenterStartLeftSideGoal() {
		//addSequential(new AutoDriveRobot(.6, .4, .6, .4, 2.5));
		addSequential(new SetInitialPositions(startX, startY));
		for(int i=0; i < pointsX.length; i++) {
			addSequential(new AutoDriveToPoint(pointsX[i], pointsY[i]));
		}
		/*
		addSequential(new AutoLift(0.8, 3));
		addSequential(new AutoGrab(true));
		Timer.delay(0.5);
		addSequential(new AutoGrab(false));
		Timer.delay(0.2);
		addSequential(new AutoLift(-0.8, 2));
		*/
	}
}
