package frc.team1091.robot;

import com.team1091.shared.components.Drive;
import com.team1091.shared.components.GameController;
import com.team1091.shared.control.TeamRobot;
import com.team1091.shared.control.TeamRobotImpl;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
    private TeamRobot teamRobot;

    Robot() {
        SpeedController scLeft = new Victor(0);
        SpeedController scRight = new Victor(1);

        // create real components wrapped and send them to the other project
        GameController controller = new GameController() {
            // adapt the existing xbox controller
            XboxController xbox = new XboxController(0);

            @Override
            public double getLeftY() {
                return xbox.getX(GenericHID.Hand.kLeft);
            }

            @Override
            public double getLeftX() {
                return xbox.getY(GenericHID.Hand.kLeft);
            }
        };

        Drive drive = new Drive() {
            DifferentialDrive differentialDrive = new DifferentialDrive(scLeft, scRight);

            @Override
            public void arcadeDrive(double speed, double turn) {
                differentialDrive.arcadeDrive(speed, turn);
            }

        };

        // then delegate to our shared code
        teamRobot = new TeamRobotImpl(
                controller,
                drive
        );
    }

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