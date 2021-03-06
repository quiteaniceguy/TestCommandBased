/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4132.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4132.robot.commands.CenterStartLeftSideGoal;
import org.usfirst.frc.team4132.robot.commands.CenterStartRightSideGoal;
import org.usfirst.frc.team4132.robot.commands.DriveFromJoystick;
import org.usfirst.frc.team4132.robot.commands.LeftSideStartLeftSideGoal;
import org.usfirst.frc.team4132.robot.commands.LeftSideStartRightSideGoal;
import org.usfirst.frc.team4132.robot.commands.RightSideStartLeftSideGoal;
import org.usfirst.frc.team4132.robot.commands.RightSideStartRightSideGoal;
import org.usfirst.frc.team4132.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4132.robot.subsystems.EncoderSystem;
import org.usfirst.frc.team4132.robot.subsystems.LifterSystem;
import org.usfirst.frc.team4132.robot.subsystems.PneumaticGearSystem;
import org.usfirst.frc.team4132.robot.subsystems.PneumaticGrabberSystem;

import com.kauailabs.navx.frc.AHRS;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	//subsystems
	public static DriveSystem driveSystem;
	public static LifterSystem lifterSystem;

	public static PneumaticGrabberSystem pneumaticGrabberSystem;
	public static PneumaticGearSystem pneumaticGearSystem;
	public static EncoderSystem encoderSystem;

	public static AHRS ahrs; /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

	//PIDController turnController;
	public static OI m_oi;
	Command m_autonomousCommand;
	Command driveFromJoystick;


	/*
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		String cool = SmartDashboard.getString("DB/String 0", "myDefaultData");
		m_oi = new OI();

		
		/*  subsystems  */
		driveSystem = new DriveSystem();
		lifterSystem = new LifterSystem();
		pneumaticGrabberSystem = new PneumaticGrabberSystem(RobotMap.grabberSolenoidOne, RobotMap.grabberSolenoidTwo);
		pneumaticGearSystem = new PneumaticGearSystem(RobotMap.gearSolenoidOne, RobotMap.gearSolenoidTwo);
		encoderSystem = new EncoderSystem();

		ahrs = new AHRS(SPI.Port.kMXP);
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {


		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		 //schedule the autonomous command (example)String gameData;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		// If the goal is to the left, then change the autonomous accordingly
        if (gameData.length() > 0) {
        	if (gameData.charAt(0) == 'L') {
        		if(SmartDashboard.getString("DB/String 0", "myDefaultData") == "Right") {
        			m_autonomousCommand = new RightSideStartLeftSideGoal();
        		}
        		else if(SmartDashboard.getString("DB/String 0", "myDefaultData") == "Center") {
        			m_autonomousCommand = new CenterStartLeftSideGoal();
        		}
        		else {
        			m_autonomousCommand = new LeftSideStartLeftSideGoal();
        		}
        	} 
        	// If the goal is not to the left, use the selected autonomous
        	else {
        		if(SmartDashboard.getString("DB/String 0", "myDefaultData") == "Right") {
        			m_autonomousCommand = new RightSideStartRightSideGoal();
        		}
        		else if(SmartDashboard.getString("DB/String 0", "myDefaultData") == "Center") {
        			m_autonomousCommand = new CenterStartRightSideGoal();
        		}
        		else {
        			m_autonomousCommand = new LeftSideStartRightSideGoal();
        		}
        	}
        }
        
        // Start autonomous
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

		driveFromJoystick = new DriveFromJoystick();
		driveFromJoystick.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//System.out.println("\nGyro X: " + ahrs.getRoll()+ "\nGyro Y: " + ahrs.getPitch()+ "\nGyro Z: " + ahrs.getYaw());
		Scheduler.getInstance().run();
		System.out.print(ahrs.getYaw());
		System.out.print(ahrs.getPitch());
		System.out.print(ahrs.getRoll());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
