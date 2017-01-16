package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.utilities.GeneratedProfile;
import org.usfirst.frc.team1241.robot.utilities.MotionProfile;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    CANTalon talon;
    
    Victor rightFront, rightBack;
    Victor leftFront, leftBack;

    MotionProfile profile;
    
    public Drivetrain(){
    	rightFront = new Victor(0);
    	rightBack = new Victor(1);
    	
    	leftFront = new Victor(2);
    	leftBack = new Victor(3);
    	
    	talon = new CANTalon(1);
    	talon.reverseOutput(true);
    	talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		talon.reverseSensor(false);
		
		FeedbackDeviceStatus status = talon.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		talon.setProfile(0);
		talon.setPID(0.3, 0, 0);
		
		talon.setProfile(1);
		talon.setPID(0, 0, 0);
		talon.setF(0.09106);
		
		talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
		switch(status){
		case FeedbackStatusPresent:
			System.out.println("ENCODER FOUND");
			break;
		case FeedbackStatusNotPresent:
			System.out.println("ENCODER NOT FOUND");
			break;
		case FeedbackStatusUnknown:
			System.out.println("ENCODER NOT FOUND");
			break;
		}
		
		profile = new MotionProfile(talon, GeneratedProfile.Points, GeneratedProfile.kNumPoints);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double encoderGet(){
    	return talon.getPosition();
    }
    
    public void resetEncoder(){
    	talon.setPosition(0);
    }
    
    public void runMotor(double input){
    	talon.set(input);
    }
    
    public void setControlMode(TalonControlMode state){
    	if(state == CANTalon.TalonControlMode.MotionProfile)
    		talon.setProfile(1);
    	else
    		talon.setProfile(0);
    	
    	talon.changeControlMode(state);
    }
    
    public TalonControlMode getTalonControlMode(){
    	return talon.getControlMode();
    }
    
    public MotionProfile getProfile(){
    	return profile;
    }
}

