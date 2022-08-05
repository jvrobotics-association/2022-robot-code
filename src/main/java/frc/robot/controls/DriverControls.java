package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.elevator.AutoMoveElevator;
import frc.robot.commands.elevator.ElevatorDown;
import frc.robot.commands.elevator.ElevatorUp;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.ReverseUpperIntake;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.ForwardIndexer;
import frc.robot.commands.shooter.ReverseFlywheel;
import frc.robot.commands.shooter.ReverseIndexer;
import frc.robot.commands.shooter.Shoot;

public class DriverControls {
    private final Joystick driveJoystick;
    private final Joystick controlPannel;

    DriverControls(int drivePortNumer, int controlPortNumber) {
        driveJoystick = new Joystick(drivePortNumer);
        controlPannel = new Joystick(controlPortNumber);


        // TODO: Reconfigure button mapping to match the driver's preference

        // joystick commands
        new JoystickButton(driveJoystick, 1).toggleWhenPressed(new Shoot());
        new JoystickButton(driveJoystick, 2).toggleWhenPressed(new RunIntake());
        new JoystickButton(driveJoystick, 3).toggleWhenPressed(new AutoMoveElevator());
        new JoystickButton(driveJoystick, 4).whenHeld(new ReverseFlywheel());
        new JoystickButton(driveJoystick, 5).whenHeld(new ReverseIndexer());
        new JoystickButton(driveJoystick, 6).whenHeld(new ReverseIntake());
        new JoystickButton(driveJoystick, 7).whenHeld(new ReverseUpperIntake());
        new JoystickButton(driveJoystick, 8).whenHeld(new ForwardIndexer());
        
        // control pannel commands
        new JoystickButton(controlPannel, 4).whenHeld(new ElevatorUp());
        new JoystickButton(controlPannel, 5).whenHeld(new ElevatorDown());
    }

    public double getForward() {
        return driveJoystick.getRawAxis(1);
    }

    public double getStrafe() {
        return driveJoystick.getRawAxis(0);
    }

    public double getYaw() {
        return driveJoystick.getRawAxis(2);
    }

    



    // DriverControls(int portNumber) {
    //     controller = new XboxController(portNumber);

    //     // Drive Commands
    //     new JoystickButton(controller, XboxController.Button.kStart.value).whenPressed(new ZeroGyroCommand());
    //     new JoystickButton(controller, XboxController.Button.kRightBumper.value).whenPressed(new XLockCommand());

    // }
    // /** Left stick X (up-down) axis. */
    // public double getForward() {
    //     return -controller.getRawAxis(XboxController.Axis.kLeftY.value);
    // }

    // /** Left stick Y (left-right) axis. */
    // public double getStrafe() {
    //     return -controller.getRawAxis(XboxController.Axis.kLeftX.value);
    // }

    // /** Right stick Y (left-right) axis. */
    // public double getYaw() {
    //     return -controller.getRawAxis(XboxController.Axis.kRightX.value);
    // }
}
