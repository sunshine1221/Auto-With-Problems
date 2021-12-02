/* 
Hello!  I am going to comment directly both with additional comments from me as well as some comments I received from Coach Joe.  Please make changes
as you see fit and send an email to let me know to look at changes.  Thank you!  Amy
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Placing a block")
public class Autonomous_Test extends LinearOpMode {

/* For the declarations below, think about what you actually need.  Because this is an autonomous program, will you need all of these?
From Coach Joe:  *** He needs to rename the motors to more appropriate/descriptive names .  This is a MUST!
*/

    DcMotor motor1;
    boolean a, b;
    double x;
    double y;
    double p;
    boolean a1, b1, a2, b2;
    boolean up, down, left, right;
    double speed;
    DcMotor motor;
    boolean wheelon = false;
    double x2;
    // x is value of wheels, back is -x,  y+x y-x
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor wheel;
    
    @Override
    public void runOpMode() throws InterruptedException {
        //Declaring motors through hardwareMap
        motor1 = hardwareMap.dcMotor.get("claw");
        motor = hardwareMap.dcMotor.get("motor");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        wheel = hardwareMap.dcMotor.get("wheel");
        
/* 
Why are we switching between RUN_USING_ENCODER and RUN_TO_POSITION?  What is the difference between the two?  When should you use one over the other?

From Coach Joe:
RUN_TO_POSITION = go to the exact position and stay there. (more accurate)
RUN_USING_ENCODERS = get to the position as fast as possible and then stay if yoiu can (speed is more important than accuracy)
*/       
        //Encoders reset and initialized
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
/* 
From previous email:  Before your waitForStart() statement, I would definitely recommend adding some telemetry statements to confirm that the encoders 
for the motors that you want to reset are actually reset. 

From Coach Joe:
*** agreed, anytime he is using encoders (especially on things with hard stops like the lift) he should use telemetry atatements.
*/
        
        waitForStart();
        //Move lift up 1444 ticks, or to where the top level is
        movingvoid(1444, motor);
        //Moving chassis forward 12000 ticks
        movingvoid(12000, frontLeft);
        movingvoid(12000, backLeft);
        movingvoid(-12000, frontRight);
        movingvoid(-12000, backRight);
        //Opening claw
        motor1.setPower(-0.25);
        sleep(500);
        motor1.setPower(0);
    }
    /* 
    I moved your previous comments to where I think you mean for them to be in the code.
    
    From previous email:  we don't want the constraints for the chassis motors to be the same as the constraints for the 
    cascading lift.  Think about how you might want to fix this :)
    
    Coach Joe sent me a comment about what he thinks you should do, but I want you to think it over first and let me know what
    you are thinking.
    
    Other questions:  Why is ticks a double being passed in, but then is used as an int?  Why not just declare it an int?
    
    From Coach Joe: *** unless there is a specific reason to change between the modes, he should set the motor to one mode 
    and leave it there instead of switching back and forth between RUN_TO_POSITION and RUN_USING_ENCODERS
    
    Also from Coach Joe:  *** he should not use abs.  he should make sure the value is positive.  Plus he should use the 
    range/clip function to ensure it is within the correct range

    */
    
    public void movingvoid(double ticks, DcMotor motorbeingused){
        int newTarget;
        double speed2 = 1;
        //make sure opmode is active
        if(opModeIsActive() && motorbeingused.getCurrentPosition() >= 0 && motorbeingused.getCurrentPosition() <= 3190){
            //find current position and add distance to move
            newTarget = motorbeingused.getCurrentPosition() + (int)ticks;
            // set target position of motor
            motorbeingused.setTargetPosition(newTarget);
            //run to that position
            motorbeingused.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // make sure that power is not negative (abs)
            motorbeingused.setPower(Math.abs(speed2));

            // stop all motors
            motorbeingused.setPower(0);
            // set to run using encoders
            motorbeingused.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Height", motorbeingused.getCurrentPosition());
            telemetry.update();
        }
    }
}
