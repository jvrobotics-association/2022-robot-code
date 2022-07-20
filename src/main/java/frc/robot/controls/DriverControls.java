package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drive.ZeroGyroCommand;

public class DriverControls {
    private final Joystick driveJoystick;

    DriverControls(int drivePortNumer) {
        driveJoystick = new Joystick(drivePortNumer);
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
