package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name = "Camera Red Close", group = "Camera")
//@Disabled
public class Camera_Red_Close extends LinearOpMode {
    DcMotor fl_Wheel;
    DcMotor bl_Wheel;
    DcMotor fr_Wheel;
    DcMotor br_Wheel;
    //DcMotor arm_motor;
    //Servo wrist_servo;
    // Servo hand_servo;
    //DcMotor drone_motor;



    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "RedPenguinB.tflite";
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/RedPenguinB.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "Penguin",
    };

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    private TfodProcessor tfod;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {

        fl_Wheel = hardwareMap.get(DcMotor.class, "fl_motor");
        bl_Wheel = hardwareMap.get(DcMotor.class, "bl_motor");
        fr_Wheel = hardwareMap.get(DcMotor.class, "fr_motor");
        br_Wheel = hardwareMap.get(DcMotor.class, "br_motor");
        //    arm_motor = hardwareMap.get(DcMotor.class, "arm_motor");
        //  wrist_servo = hardwareMap.get(Servo.class, "wrist_servo");
        //hand_servo = hardwareMap.get(Servo.class, "hand_servo");
        //drone_motor = hardwareMap.get(DcMotor.class, "drone_motor");


        fr_Wheel.setDirection(DcMotor.Direction.REVERSE);
        fl_Wheel.setDirection(DcMotor.Direction.REVERSE);
        br_Wheel.setDirection(DcMotor.Direction.REVERSE);
        bl_Wheel.setDirection(DcMotor.Direction.REVERSE);
        //  arm_motor.setDirection(DcMotor.Direction.FORWARD);
        //drone_motor.setDirection(DcMotor.Direction.FORWARD);


        fr_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //    arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //      drone_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        initTfod();

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");

        sleep(15000);

        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                telemetryTfod();

                // Push telemetry to the Driver Station.
                telemetry.update();

                // Save CPU resources; can resume streaming when needed.
                if (gamepad1.dpad_down) {
                    visionPortal.stopStreaming();
                } else if (gamepad1.dpad_up) {
                    visionPortal.resumeStreaming();
                }

                // Share the CPU.
                sleep(20);
            }
        }

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }   // end runOpMode()

    /**
     * Initialize the TensorFlow Object Detection processor.
     */
    private void initTfod() {

        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()

                // With the following lines commented out, the default TfodProcessor Builder
                // will load the default model for the season. To define a custom model to load,
                // choose one of the following:
                //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
                //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
                .setModelAssetName(TFOD_MODEL_ASSET)
                //.setModelFileName(TFOD_MODEL_FILE)

                // The following default settings are available to un-comment and edit as needed to
                // set parameters for custom models.
                .setModelLabels(LABELS)
                //.setIsModelTensorFlow2(true)
                //.setIsModelQuantized(true)
                //.setModelInputSize(300)
                //.setModelAspectRatio(16.0 / 9.0)

                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */
    private void telemetryTfod() {

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());



            if (x<500 && x>200) {

                waitForStart();

                // double drift = 0.93;

                sleep(1500);

                fr_Wheel.setPower(0.5);
                fl_Wheel.setPower(0.5);
                br_Wheel.setPower(0.5);
                bl_Wheel.setPower(0.5);

                sleep(1350);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(900);

                fr_Wheel.setPower(-0.5);
                fl_Wheel.setPower(-0.5);
                br_Wheel.setPower(-0.5);
                bl_Wheel.setPower(-0.5);

                sleep(250);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(100000);

            } else if (x>500 && x<640){

                waitForStart();

                // double drift = 0.93;

                sleep(1500);

                fr_Wheel.setPower(0.5);
                fl_Wheel.setPower(0.5);
                br_Wheel.setPower(0.5);
                bl_Wheel.setPower(0.5);

                sleep(200);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(1000);

                fr_Wheel.setPower(-.5);
                fl_Wheel.setPower(-.5);
                br_Wheel.setPower(.5);
                bl_Wheel.setPower(.5);

                sleep(650);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(1000);

                fr_Wheel.setPower(.5);
                fl_Wheel.setPower(.5);
                br_Wheel.setPower(.5);
                bl_Wheel.setPower(.5);

                sleep(800);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(1000);

                fr_Wheel.setPower(-0.5);
                fl_Wheel.setPower(-0.5);
                br_Wheel.setPower(-0.5);
                bl_Wheel.setPower(-0.5);

                sleep(250);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(100000);

            } else if (x>0 && x<150){

                sleep(1500);

                fr_Wheel.setPower(0.5);
                fl_Wheel.setPower(0.5);
                br_Wheel.setPower(0.5);
                bl_Wheel.setPower(0.5);

                sleep(1000);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(1000);

                fr_Wheel.setPower(-0.2);
                fl_Wheel.setPower(0.2);
                br_Wheel.setPower(-0.2);
                bl_Wheel.setPower(0.2);

                sleep(2300);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(1000);

                fr_Wheel.setPower(0.4);
                fl_Wheel.setPower(0.4);
                br_Wheel.setPower(0.4);
                bl_Wheel.setPower(0.4);

                sleep(450);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(500);

                fr_Wheel.setPower(-0.5);
                fl_Wheel.setPower(-0.5);
                br_Wheel.setPower(-0.5);
                bl_Wheel.setPower(-0.5);

                sleep(250);

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(10000000);

            }

            else {

                fr_Wheel.setPower(0);
                fl_Wheel.setPower(0);
                br_Wheel.setPower(0);
                bl_Wheel.setPower(0);

                sleep(100000);

            }




        }   // end for() loop

    }   // end method telemetryTfod()

}   // end class