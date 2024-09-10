package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp (name= "MecanumTeleopTesting")
//@Disabled
public class MecanumTeleopTesting extends OpMode {
    // motors
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;
    DcMotor drone_launcher;
    DcMotor hang_motor;
    DcMotor pull_motor;



    @Override
    public void init() {


        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");
        drone_launcher = hardwareMap.get(DcMotor.class, "drone_launcher");
        hang_motor = hardwareMap.get(DcMotor.class, "hang_motor");
        pull_motor = hardwareMap.get(DcMotor.class, "pull_motor");


        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        drone_launcher.setDirection(DcMotor.Direction.FORWARD);
        hang_motor.setDirection(DcMotor.Direction.FORWARD);
        pull_motor.setDirection(DcMotor.Direction.FORWARD);


        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drone_launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hang_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pull_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void loop() {
        //testing a change for github
        // encoders
        double fl_Position = fl_Wheel.getCurrentPosition();
        double bl_Position = bl_Wheel.getCurrentPosition();
        double fr_Position = fr_Wheel.getCurrentPosition();
        double br_Position = br_Wheel.getCurrentPosition();


        telemetry.addData("Front Left Wheel Pos", fl_Position);
        telemetry.addData("Back Left Wheel Pos", bl_Position);
        telemetry.addData("Front Right Wheel Pos", fr_Position);
        telemetry.addData("Back Right Wheel Pos", br_Position);
        telemetry.update();


            // wheel movement
            double x = gamepad1.right_stick_y;
            double y = gamepad1.right_stick_x * 1.1;
            double rx = gamepad1.left_stick_y; // rotation


            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.2);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;


            fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            drone_launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            boolean leftstrafe = gamepad1.right_bumper;
            boolean rightstrafe = gamepad1.left_bumper;

            if (gamepad1.x) {
                drone_launcher.setPower(2.0);

            }

                else {
                drone_launcher.setPower(0);

        }

                if (gamepad2.dpad_up) {
                    hang_motor.setPower(1);


                }

                else if (gamepad2.dpad_down){
                    hang_motor.setPower(-1);

        }
                else {
                    hang_motor.setPower(0);
                }

                if (gamepad2.a) {

                    pull_motor.setPower(1);
                }

                else if (gamepad2.b) {

                    pull_motor.setPower(-1);

                }

                else {
                    pull_motor.setPower(0);


                }

            if (rightstrafe) {
                fl_Wheel.setPower(-0.7);
                fr_Wheel.setPower(0.7);
                bl_Wheel.setPower(0.7);
                br_Wheel.setPower(-0.7);


            } else if (leftstrafe) {
                fl_Wheel.setPower(0.7);
                fr_Wheel.setPower(-0.7);
                bl_Wheel.setPower(-0.7);
                br_Wheel.setPower(0.7);


            } else {
                fl_Wheel.setPower(frontLeftPower);
                bl_Wheel.setPower(backLeftPower);
                fr_Wheel.setPower(frontRightPower);
                br_Wheel.setPower(backRightPower);

                fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            }
        }
    }