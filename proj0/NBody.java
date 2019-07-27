public class NBody{

    public static String imageToDraw = "./images/starfield.jpg";
    public static void main(String[] args) {


        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Body[] bodies = readBodies(filename);
/**initiate pic */
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        for(Body body:bodies){
            body.draw();
        }
        StdDraw.show();
/**create animation */
        double time = 0;
        double duration = 10;
        while(time < T){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i = 0; i<bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i<bodies.length; i++){
                bodies[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for(Body body:bodies){
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                          bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }

    public static double readRadius(String file){
        In in = new In(file); 
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String file){

        In in = new In(file);
        int N = in.readInt();
        double R = in.readDouble();
        double x;
        double y;
        double vx;
        double vy;
        double m;
        String img;
        
        Body[] bodies = new Body[N];
        for(int i=0; i<N; i++){
            x = in.readDouble();
            y = in.readDouble();
            vx = in.readDouble();
            vy = in.readDouble();
            m = in.readDouble();
            img = in.readString();
            bodies[i] = new Body(x,y,vx,vy,m,img);
        }
        return bodies;
    }
}