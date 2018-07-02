package frc.team1091.robot;

import com.team1091.shared.components.IDrive;
import com.team1091.shared.components.IEncoder;
import com.team1091.shared.components.IGameController;
import com.team1091.shared.control.RobotComponents;
import com.team1091.shared.control.TeamRobot;
import com.team1091.shared.control.TeamRobotImpl;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import frc.team1091.robot.components.WrappedDrive;
import frc.team1091.robot.components.WrappedEncoder;
import frc.team1091.robot.components.WrappedXBox;

public class Robot extends IterativeRobot {
    private TeamRobot teamRobot;

    Robot() {
        SpeedController scLeft = new Victor(0);
        SpeedController scRight = new Victor(1);

        // create real components wrapped and send them to the other project
        IGameController controller = new WrappedXBox(0);
        IDrive drive = new WrappedDrive(scLeft, scRight);

        IEncoder encoderL = new WrappedEncoder(3, 4);
        IEncoder encoderR = new WrappedEncoder(5, 6);

        // then delegate to our shared code
        teamRobot = new TeamRobotImpl(
                new RobotComponents(
                        controller,
                        drive,
                        encoderL,
                        encoderR
                )
        );
    }

    // Delegate everything else to the TeamRobot
    @Override
    public void robotInit() {
        teamRobot.robotInit();
    }

    @Override
    public void disabledInit() {
        teamRobot.disabledInit();
    }

    @Override
    public void autonomousInit() {
        teamRobot.autonomousInit();
    }

    @Override
    public void teleopInit() {
        teamRobot.teleopInit();
    }

    @Override
    public void testInit() {
        teamRobot.testInit();
    }

    @Override
    public void disabledPeriodic() {
        teamRobot.disabledPeriodic();
    }

    @Override
    public void autonomousPeriodic() {
        teamRobot.autonomousPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        teamRobot.teleopPeriodic();
    }

    @Override
    public void testPeriodic() {
        teamRobot.testPeriodic();
    }
}