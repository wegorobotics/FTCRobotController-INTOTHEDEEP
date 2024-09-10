package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp (name= "MecanumTeleopArm")
//@Disabled
public class MecanumTeleopArm extends OpMode {
    // motors
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;
    DcMotor arm_motorLeft;
    DcMotor arm_motorRight;



    //agtest change CRServo to Servo 11/14
    Servo wrist_servo;
    CRServo claw_servo;


    @Override
    public void init() {


        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");
        arm_motorLeft = hardwareMap.get(DcMotor.class, "arm_motorLeft");
        arm_motorRight = hardwareMap.get(DcMotor.class, "arm_motorRight");
        wrist_servo = hardwareMap.get(Servo.class, "wrist_servo");
        claw_servo = hardwareMap.get(CRServo.class, "claw_servo");


        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.FORWARD);
        arm_motorLeft.setDirection(DcMotor.Direction.FORWARD);
        arm_motorRight.setDirection(DcMotor.Direction.REVERSE);


        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //wrist_servo.resetDeviceConfigurationForOpMode(); //AG testing 11/14 - can delete
    }


    public void loop() {
        //testing a change for github
        // encoders
        double fl_Position = fl_Wheel.getCurrentPosition();
        double bl_Position = bl_Wheel.getCurrentPosition();
        double fr_Position = fr_Wheel.getCurrentPosition();
        double br_Position = br_Wheel.getCurrentPosition();
        double larm_Position = arm_motorLeft.getCurrentPosition();
        double rarm_Position = arm_motorRight.getCurrentPosition();
        double wrist_Position = wrist_servo.getPosition(); // agtest 11/14


        final double wrist_Speed = .02; // agtest 11/14


        telemetry.addData("Front Left Wheel Pos", fl_Position);
        telemetry.addData("Back Left Wheel Pos", bl_Position);
        telemetry.addData("Front Right Wheel Pos", fr_Position);
        telemetry.addData("Back Right Wheel Pos", br_Position);
        telemetry.addData("Left Arm Motor Pos", larm_Position);
        telemetry.addData("Right Arm Motor Pos", rarm_Position);
        telemetry.addData("Wrist Servo Pos", wrist_Position);
        telemetry.update();


        //wrist servo movement
        // if (gamepad1.atRest()) {
        //     wrist_servo.setPower(0);
        //}
        ///while(gamepad1.x || gamepad1.y)
        ///{
        /// if (gamepad1.x)
        //    {
        //        wrist_servo.setPower(1);
        //    }
        //   else if (gamepad1.y)
        //   {
        //        wrist_servo.setPower(-1);
        //}
        //}
        //while (!gamepad1.x && !gamepad1.y)
        //{
        //    wrist_servo.setPower(0);
        //}
        //Experimental code change
        //hand servo movement
        //position for closing


       /* agtest - commenting out previous wrist controls 11/14
       if (gamepad1.x) {
           wrist_servo.setPower(1);
       } else if (gamepad1.y) {
           wrist_servo.setPower(-1);
       } else {
           wrist_servo.setPower(0);
       }
       */
        // agtest - new wrist controls test 11/14


        if (gamepad1.x) {
            wrist_Position += wrist_Speed;
        } else if (gamepad1.y) {
            wrist_Position -= wrist_Speed;
        }
        wrist_servo.setPosition(wrist_Position);








       /*
       if (gamepad1.x) {
           wrist_servo.setPosition(0.25);
       }
       if (gamepad1.y) {
           wrist_servo.setPosition(0.75);
       }
       */


        //hand servo movement
        //command for closing

        if (gamepad1.b) {
            claw_servo.setPower(1);
        }
        //command for opening
        else if (gamepad1.a) {
            claw_servo.setPower(-1);
        } else {
            claw_servo.setPower(0);


            // arm motor movement :)
            double arm_speed = gamepad1.right_trigger - gamepad1.left_trigger;
            arm_motorLeft.setPower(arm_speed);
            arm_motorRight.setPower(arm_speed);


            // wheel movement
            double x = gamepad1.right_stick_y;
            double y = -gamepad1.right_stick_x * 1.1;
            double rx = gamepad1.left_stick_y; // rotation


            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.5);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;


            fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm_motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm_motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            boolean leftstrafe = gamepad1.right_bumper;
            boolean rightstrafe = gamepad1.left_bumper;


            if (rightstrafe) {
                fl_Wheel.setPower(0.7);
                fr_Wheel.setPower(0.7);
                bl_Wheel.setPower(-0.7);
                br_Wheel.setPower(-0.7);


            } else if (leftstrafe) {
                fl_Wheel.setPower(-0.7);
                fr_Wheel.setPower(-0.7);
                bl_Wheel.setPower(0.7);
                br_Wheel.setPower(0.7);
            } else {
                fl_Wheel.setPower(frontLeftPower);
                bl_Wheel.setPower(backLeftPower);
                fr_Wheel.setPower(frontRightPower);
                br_Wheel.setPower(backRightPower);
                // strafepower levels changed from 1 to 0.5 by Asher in an attempt to adreess problem
                //with wheel spin speed being out of sych. Untested.


                fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                arm_motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                arm_motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            }
        }
    }
}
