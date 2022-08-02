// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.drive.TeleopDriveCommand;
import frc.robot.controls.Controls;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    public static Constants CONSTANTS;
    public static DriveSubsystem DRIVE;
    public static ElevatorSubsystem ELEVATOR;
    public static ShooterSubsystem SHOOTER;
    public static IntakeSubsystem INTAKE;
    public static Controls CONTROLS;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        CONSTANTS = new Constants();
        DRIVE = new DriveSubsystem();
        ELEVATOR = new ElevatorSubsystem();
        SHOOTER = new ShooterSubsystem();
        INTAKE = new IntakeSubsystem();

        CONTROLS = new Controls();

        DRIVE.setDefaultCommand(new TeleopDriveCommand());

        // Configure the button bindings
        configureButtonBindings();
    }


    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }
}
