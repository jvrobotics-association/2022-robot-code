package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class CustomGyroModule implements Gyro {
    private final static CustomGyroModule INSTANCE = new CustomGyroModule();

    public static CustomGyroModule getInstance() {
        return INSTANCE;
    }

    private final ADIS16470_IMU imu;

    public CustomGyroModule() {
        this.imu = new ADIS16470_IMU();
    }

    @Override
    public void calibrate() {
        imu.calibrate();
    }

    @Override
    public void reset() {
        imu.reset();
    }

    @Override
    public double getAngle() {
        return imu.getAngle();
    }

    @Override
    public double getRate() {
        return imu.getRate();
    }

    @Override
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(-getAngle());
    }

    @Override
    public void close() {
        imu.close();
    }
}
