package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.GGHardware;
import org.firstinspires.ftc.teamcode.GGParameters;
/**
 * Created by User on 10/30/2017.
 */

@TeleOp(name="WorldsTeleop",group="TeleOp")
public class worldsTeleop extends LinearOpMode
{

    GGHardware robot = new GGHardware();
    private ElapsedTime  runtime= new ElapsedTime();


    @Override
    public void runOpMode()
    {


        GGParameters parameters = new GGParameters(this);
        robot.init(parameters);
        robot.resetEncoders();
        waitForStart();
        while (opModeIsActive())
        {

            robot.backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            int position = robot.backleft.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();


            ///////////////////////////////////////
            ///                                 ///
            /// Equation that assigns power and ///
            /// direction of wheels based on    ///
            /// joystick position               ///
            ///                                 ///
            ///////////////////////////////////////
            robot.getJoyVals();

            robot.pwr = robot.y; //this can be tweaked for exponential power increase

            robot.frontright.setPower(Range.clip(robot.pwr - robot.x + robot.z, -1, 1));
            robot.backleft.setPower(Range.clip(robot.pwr - robot.x - robot.z, -1, 1));
            robot.frontleft.setPower(Range.clip(robot.pwr + robot.x - robot.z, -1, 1));
            robot.backright.setPower(Range.clip(robot.pwr + robot.x + robot.z, -1, 1));


            ////////////////////////
            ///                  ///
            ///   intake motors  ///
            ///                  ///
            ////////////////////////
            if (gamepad2.left_bumper)
            {
                robot.intakel.setPower(-0.75);
                robot.intaker.setPower(0.75);
            }

            if (gamepad2.right_bumper)
            {
                robot.intakel.setPower(0.75);
                robot.intaker.setPower(-0.75);
            }

            else if(gamepad2.right_trigger > 0.2 && gamepad2.right_bumper)
            {
                robot.intakel.setPower(0.5);
                robot.intaker.setPower(-1);
            }

            else
            {
                robot.intakel.setPower(0);
                robot.intaker.setPower(0);
                robot.intakel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                robot.intaker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

            //////////////////
            /////        /////
            /////flipper//////
            /////        /////
            //////////////////
            if (gamepad2.y)
            {
                robot.flipper.setPower(0.55);
            }

            else if (gamepad2.a)
            {
                robot.flipper.setPower(-0.25);
            }

            else
            {
                robot.flipper.setPower(0);
                robot.relicLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }


            ///////////////////
            ///             ///
            /// relic lift  ///
            ///             ///
            ///////////////////
            if(gamepad2.x)
            {
                robot.relicLift.setPower(-1);

            }

            else if(gamepad2.b)
            {
                robot.relicLift.setPower(1);

            }
            else
            {
                robot.relicLift.setPower(0);
                robot.relicLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            ///////////////////////
            ///                 ///
            ///relic wrist joint///
            ///                 ///
            ///////////////////////

            //relic wrist down
            if(gamepad2.dpad_down)
            {
                robot.relicUpDown.setPosition(0.30);
            }

            //color arm middle
            if(gamepad2.dpad_up)
            {
                robot.relicUpDown.setPosition(1);
            }

            //////////////////
            ///            ///
            /// relic claw ///
            ///            ///
            //////////////////
            if(gamepad1.dpad_left)
            {
                robot.relicClaw.setPosition(0.0);
            }
            if(gamepad1.dpad_right)
            {
                robot.relicClaw.setPosition(0.75);
            }

            //////////////////
            ///            ///
            /// Jewel Arm  ///
            ///            ///
            //////////////////
            if(gamepad1.y)
            {
                robot.jewelUpDown.setPosition(.4);
                robot.jewelSide.setPosition(.35);
                robot.jewelUpDown.setPosition(robot.JEWELUPDOWN_MAX_RANGE);
            }
            if(gamepad1.a)
            {
                robot.jewelUpDown.setPosition(robot.JEWELUPDOWN_MIN_RANGE);
            }
            if(gamepad1.b)
            {
                robot.jewelSide.setPosition(robot.JEWELSIDE_MAX_RANGE);
            }
            if(gamepad1.x)
            {
                robot.jewelSide.setPosition(robot.JEWELSIDE_MIN_RANGE);
            }

            if(gamepad1.right_bumper)
            {
                //robot.jewelUpDown.setPosition(.3);
                robot.jewelSide.setPosition(.35);
                sleep(1000);
                robot.jewelUpDown.setPosition(robot.JEWELUPDOWN_MIN_RANGE);
            }

        }
    }


}