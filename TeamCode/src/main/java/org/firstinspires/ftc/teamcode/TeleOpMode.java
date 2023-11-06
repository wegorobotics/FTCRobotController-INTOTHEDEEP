package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/* Created by the WEGO Robotics rookie team on 10/10/2023. */
@TeleOp()
public class TeleOpMode extends OpMode {
    //private DcMotor frontLeft;
    //private DcMotor frontRight;
    //private DcMotor backLeft;
    //private DcMotor backRight;
    private DcMotor fr_Wheel;
    @Override
    public void init() {

        telemetry.addData("Hello","World");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        //frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        //frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        //backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        //backRight = hardwareMap.get(DcMotor.class, "backRight");
        //waitForStart();

        //while (opModeIsActive()) {
        //}
    }
    @Override
    public void loop() {
        fr_Wheel.setPower(0.1);
    }


}