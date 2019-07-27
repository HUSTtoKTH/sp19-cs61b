public class Body{
    public static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Body(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**calculates the distance between two Bodys  */
    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double distance = Math.sqrt((dx*dx + dy*dy));
        return distance;
    }

    /** takes in a Body, and returns a double describing the force exerted on this body by the given body. */
    public double calcForceExertedBy(Body b){
        double distance = this.calcDistance(b);
        double F = (G*this.mass*b.mass)/(distance *distance);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - this.xxPos;
        double F = this.calcForceExertedBy(b);
        double distance = this.calcDistance(b);
        double Fx = (F*dx)/distance;
        return Fx;
    }

    public double calcForceExertedByY(Body b){
        double dy = b.yyPos - this.yyPos;
        double F = this.calcForceExertedBy(b);
        double distance = this.calcDistance(b);
        double Fy = (F*dy)/distance;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bs){
        double Fx = 0;
        for(Body b:bs){
            if(!this.equals(b)){
                Fx += this.calcForceExertedByX(b);
            }
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Body[] bs){
        double Fy = 0;
        for(Body b:bs){
            if(!(this.equals(b))){
                Fy += this.calcForceExertedByY(b);
            }
        }
        return Fy;
    }

    public void update(double sec, double Fx, double Fy){
        double ax = Fx/this.mass;
        double ay = Fy/this.mass;
        this.xxVel += ax*sec;
        this.yyVel += ay*sec;
        this.xxPos += this.xxVel*sec;
        this.yyPos += this.yyVel*sec;

    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/"+this.imgFileName);
    }
}