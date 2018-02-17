package org.usfirst.frc.team4132.robot.commands;

import org.usfirst.frc.team4132.robot.Robot;
import org.usfirst.frc.team4132.robot.XboxControllerMap;

import edu.wpi.first.wpilibj.command.Command;

public class SolenoidFromJoystick extends Command{
	
	enum ShooterSolenoidStates{
		IN, OUT, DORMANTIN, DORMANTOUT;
	}
	
	ShooterSolenoidStates shooterSolenoidState;
	
	public SolenoidFromJoystick() {
		requires(Robot.pneumaticSystem);
		shooterSolenoidState = ShooterSolenoidStates.IN;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void execute() {
		switch(shooterSolenoidState) {
			case IN:
				Robot.pneumaticSystem.solenoidIn();
				if(!Robot.m_oi.stickOne.getRawButton(XboxControllerMap.Y)) {
					shooterSolenoidState = ShooterSolenoidStates.DORMANTIN;
				}
				
				break;
				
			case DORMANTIN:
				Robot.pneumaticSystem.solenoidIn();
				if(Robot.m_oi.stickOne.getRawButton(XboxControllerMap.Y)) {
					shooterSolenoidState = ShooterSolenoidStates.OUT;
				}
				
				break;
				
			case OUT:
				Robot.pneumaticSystem.solenoidOut();
				if(!Robot.m_oi.stickOne.getRawButton(XboxControllerMap.Y)) {
					shooterSolenoidState = ShooterSolenoidStates.DORMANTOUT;
				}
				
				break;
				
			case DORMANTOUT:
				Robot.pneumaticSystem.solenoidOut();
				if(Robot.m_oi.stickOne.getRawButton(XboxControllerMap.Y)) {
					shooterSolenoidState = ShooterSolenoidStates.IN;
				}
				
				break;
		}
	}

}
