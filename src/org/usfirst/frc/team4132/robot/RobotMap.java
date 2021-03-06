/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4132.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	

	public static int frontRightMotor = 2, frontLeftMotor = 3, backRightMotor = 0, backLeftMotor = 5, armLifter = 1;
	public static int rightEncoderOne = 0, rightEncoderTwo = 1, leftEncoderOne = 2, leftEncoderTwo = 3;
	public static int grabberSolenoidOne = 0, grabberSolenoidTwo = 1, gearSolenoidOne = 2, gearSolenoidTwo = 3; 
	public static int limitSwitch = 0;

	
}

