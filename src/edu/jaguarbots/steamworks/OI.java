package edu.jaguarbots.steamworks;

import edu.jaguarbots.steamworks.commands.climber.Ascend;
import edu.jaguarbots.steamworks.commands.climber.Descend;
import edu.jaguarbots.steamworks.commands.drive.EncoderDrive;
import edu.jaguarbots.steamworks.commands.drive.GearShiftHigh;
import edu.jaguarbots.steamworks.commands.drive.GearShiftLow;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements RobotMap {
	public OI() throws InterruptedException {
	    Joystick0_Button1.whileHeld(new Ascend()); //climber
	    Joystick1_Button1.whileHeld(new Ascend());
		Joystick0_Button6.whileHeld(new Descend()); //reverse the climber (just in case, may be needed to remove rope)
		Joystick1_Button6.whileHeld(new Descend());
	    Joystick0_Button2.whenPressed(new GearShiftLow()); //gear shifting
	    Joystick0_Button3.whenPressed(new GearShiftHigh());
		Joystick1_Button2.whenPressed(new GearShiftLow());
		Joystick1_Button3.whenPressed(new GearShiftHigh());
		//manipulator controls
		ManipulatorXbox_A.whileHeld(new Ascend());
		ManipulatorXbox_Y.whileHeld(new Descend());
		ManipulatorXbox_LB.whileHeld(new GearShiftLow());
		ManipulatorXbox_RB.whileHeld(new GearShiftHigh());
//		Joystick0_Button10.whenPressed(new EncoderDrive(36)); //for testing
//		Joystick1_Button10.whenPressed(new EncoderDrive(36));
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

	public final Joystick Joystick0 = new Joystick(LEFT_JOYSTICK_PORT);
	public final Joystick Joystick1 = new Joystick(RIGHT_JOYSTICK_PORT);
	public final Joystick Manipulator = new Joystick(MANIPULATOR_JOYSTICK_PORT);

	//Buttons here are as labelled on the controllers.
	public final Button Joystick0_Button1 = new JoystickButton(Joystick0, 1);
	public final Button Joystick0_Button2 = new JoystickButton(Joystick0, 2);
	public final Button Joystick0_Button3 = new JoystickButton(Joystick0, 3);
	public final Button Joystick0_Button4 = new JoystickButton(Joystick0, 4);
	public final Button Joystick0_Button5 = new JoystickButton(Joystick0, 5);
	public final Button Joystick0_Button6 = new JoystickButton(Joystick0, 6);
	public final Button Joystick0_Button7 = new JoystickButton(Joystick0, 7);
	public final Button Joystick0_Button8 = new JoystickButton(Joystick0, 8);
	public final Button Joystick0_Button9 = new JoystickButton(Joystick0, 9);
	public final Button Joystick0_Button10 = new JoystickButton(Joystick0, 10);
	public final Button Joystick0_Button11 = new JoystickButton(Joystick0, 11);

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

	//Xbox Controls
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
	
	//Dualshock/Playstation controller buttons if needed. currently unlikely to be used.
/*	public final Button Manipulator_Select = new JoystickButton(Manipulator, 1);
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
	public final Button Manipulator_Square = new JoystickButton(Manipulator, 16);*/

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
