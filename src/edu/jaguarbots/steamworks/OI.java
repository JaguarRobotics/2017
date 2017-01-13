package edu.jaguarbots.steamworks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.jaguarbots.steamworks.commands.climber.*;
import edu.jaguarbots.steamworks.commands.drive.*;
import edu.jaguarbots.steamworks.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public OI() throws InterruptedException {
		
//		if(Manipulator.getIsXbox() == false){
//			Manipulator_L1.whileHeld(new Intake());							//1
//		 	Manipulator_L3.whenPressed(new IntakeMiddle());					//2
//		 	Manipulator_R1.whileHeld(new Output());							//3
//		 	Manipulator_R3.whenPressed(new Ascend());						//4
//		 	Manipulator_R2.whenPressed(new ShooterFire());					//5
//		 	Manipulator_Start.whenPressed(new Down());						//6
//		 	Manipulator_Select.whenPressed(new Grab());						//7
//		 	Manipulator_DpadLeft.whenPressed(new IntakeBottom());			//8
//		 	Manipulator_DpadRight.whenPressed(new IntakeTop());				//9
//		 	Manipulator_DpadUp.whenPressed(new ShooterUp());				//10
//		 	Manipulator_DpadDown.whenPressed(new ShooterDown());			//11
//		} else{
		/*
		 * +----------------------------+
		 * |  This needs to be changed  |
		 * |        in the future       |
		 * |            |  |            |
		 * |            v  v            |
		 * +----------------------------+
		 */
			//ManipulatorXbox_A.whenPressed(new ShooterDown());				//1
			//ManipulatorXbox_B.whenPressed(new IntakeBottom());				//2
			//ManipulatorXbox_X.whenPressed(new IntakeTop());					//3
			//ManipulatorXbox_Y.whenPressed(new ShooterUp());					//4
			//ManipulatorXbox_LB.whileHeld(new Intake());						//5
			//ManipulatorXbox_RB.whileHeld(new Output());						//6
			ManipulatorXbox_Back.whenPressed(new Down());					//7
			ManipulatorXbox_Start.whenPressed(new Grab());					//8
			//ManipulatorXbox_RStick.whenPressed(new IntakeMiddle());			//9
			ManipulatorXbox_LStick.whenPressed(new Ascend());				//10
//		}
		Joystick1_Button1.whenPressed(new GearShiftLow()); // sol
		Joystick2_Button1.whenPressed(new GearShiftHigh());
		/*
		 * +----------------------------+
		 * |            ^  ^            |
		 * |            |  |            |
		 * |  This needs to be changed  |
		 * |        in the future       |
		 * +----------------------------+
		 * */
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());
	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public final Joystick Joystick1 = new Joystick(RobotMap.leftStick);
	public final Joystick Joystick2 = new Joystick(RobotMap.rightStick);
	public final Joystick Manipulator = new Joystick(RobotMap.manipulator);

	public final Button Joystick1_Button1 = new JoystickButton(Joystick1, 1);
	public final Button Joystick1_Button2 = new JoystickButton(Joystick1, 2);
	public final Button Joystick1_Button3 = new JoystickButton(Joystick1, 3);
	public final Button Joystick1_Button4 = new JoystickButton(Joystick1, 4);
	public final Button Joystick1_Button5 = new JoystickButton(Joystick1, 5);
	public final Button Joystick1_Button6 = new JoystickButton(Joystick1, 6);
	public final Button Joystick1_Button7 = new JoystickButton(Joystick1, 7);
	public final Button Joystick1_Button8 = new JoystickButton(Joystick1, 8);
	public final Button Joystick1_Button9 = new JoystickButton(Joystick1, 9);
	public final Button Joystick1_Button10 = new JoystickButton(Joystick1, 10);
	public final Button Joystick1_Button11 = new JoystickButton(Joystick1, 11);
	public final Button Joystick1_Button12 = new JoystickButton(Joystick1, 12);

	public final Button Joystick2_Button1 = new JoystickButton(Joystick2, 1);
	public final Button Joystick2_Button2 = new JoystickButton(Joystick2, 2);
	public final Button Joystick2_Button3 = new JoystickButton(Joystick2, 3);
	public final Button Joystick2_Button4 = new JoystickButton(Joystick2, 4);
	public final Button Joystick2_Button5 = new JoystickButton(Joystick2, 5);
	public final Button Joystick2_Button6 = new JoystickButton(Joystick2, 6);
	public final Button Joystick2_Button7 = new JoystickButton(Joystick2, 7);
	public final Button Joystick2_Button8 = new JoystickButton(Joystick2, 8);
	public final Button Joystick2_Button9 = new JoystickButton(Joystick2, 9);
	public final Button Joystick2_Button10 = new JoystickButton(Joystick2, 10);
	public final Button Joystick2_Button11 = new JoystickButton(Joystick2, 11);
	public final Button Joystick2_Button12 = new JoystickButton(Joystick2, 12);

	public final Button ManipulatorXbox_A = new JoystickButton(Manipulator, 1);
	public final Button ManipulatorXbox_B = new JoystickButton(Manipulator, 2);
	public final Button ManipulatorXbox_X = new JoystickButton(Manipulator, 3);
	public final Button ManipulatorXbox_Y = new JoystickButton(Manipulator, 4);
	public final Button ManipulatorXbox_LB = new JoystickButton(Manipulator, 5);
	public final Button ManipulatorXbox_RB = new JoystickButton(Manipulator, 6);
	public final Button ManipulatorXbox_Back = new JoystickButton(Manipulator, 7);
	public final Button ManipulatorXbox_Start = new JoystickButton(Manipulator, 8);
	public final Button ManipulatorXbox_LStick = new JoystickButton(Manipulator, 9);
	public final Button ManipulatorXbox_RStick = new JoystickButton(Manipulator, 10);
	
	public final Button Manipulator_Select = new JoystickButton(Manipulator, 1);
	public final Button Manipulator_L3 = new JoystickButton(Manipulator, 2);
	public final Button Manipulator_R3 = new JoystickButton(Manipulator, 3);
	public final Button Manipulator_Start = new JoystickButton(Manipulator, 4);
	public final Button Manipulator_DpadUp = new JoystickButton(Manipulator, 5);
	public final Button Manipulator_DpadRight = new JoystickButton(Manipulator, 6);
	public final Button Manipulator_DpadDown = new JoystickButton(Manipulator, 7);
	public final Button Manipulator_DpadLeft = new JoystickButton(Manipulator, 8);
	public final Button Manipulator_L2 = new JoystickButton(Manipulator, 9);
	public final Button Manipulator_R2 = new JoystickButton(Manipulator, 10);
	public final Button Manipulator_L1 = new JoystickButton(Manipulator, 11);
	public final Button Manipulator_R1 = new JoystickButton(Manipulator, 12);
	public final Button Manipulator_Triangle = new JoystickButton(Manipulator, 13);
	public final Button Manipulator_Circle = new JoystickButton(Manipulator, 14);
	public final Button Manipulator_X = new JoystickButton(Manipulator, 15);
	public final Button Manipulator_Square = new JoystickButton(Manipulator, 16);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.s
	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());
	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());
	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
