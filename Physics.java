public class Physics
{
    private final double DEFAULT_GRAVITY_ACCELERATION = 0.5;
    
    private double mass;
    private double xVel,yVel;
    private double xAccel,yAccel;
    
    public Physics(double mass) {
        mass = mass;
        xVel = 0;
        yVel = 0;
        xAccel = 0;
        yAccel = DEFAULT_GRAVITY_ACCELERATION;
    }
    
    public double getXVel() { return xVel;}
    public double getYVel() { return yVel;}
    public double getXAccel() { return xAccel;}
    public double getYAccel() { return yAccel;}
    
    public void setXVelocity(double value) {xVel = value;}
    public void setYVelocity(double value) {yVel = value;}
    public void setXAcceleration(double value) {xAccel = value;}
    public void setYAcceleration(double value) {yAccel = value;}
    
    public void update() {
        updateX();
        updateY();
    }
    
    private int updateX() {
        double XVelNaught = xVel;
        xVel += xAccel;
        double xAvg = (XVelNaught+xVel)/2;
        return (int)xAvg;
    }
    
    private int updateY() {
        double YVelNaught = yVel;
        yVel += yAccel;
        double yAvg = (YVelNaught+yVel)/2;
        return (int)yAvg;
    }
}
