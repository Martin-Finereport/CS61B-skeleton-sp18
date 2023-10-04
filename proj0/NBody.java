public class NBody {
    public static double radius = 0;
    public static String imageToDraw = "images/starfield.jpg";
    public static double T;
    public static double dt;
    public static String filename;
    public static Planet[] planets;


    public static void main(String[] args) {
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];

        radius = NBody.readRadius(filename);
        planets = NBody.readPlanets(filename);

        double[] xForces = new double[5];
        double[] yForces = new double[5];

        
        
        drawBg();
        StdDraw.enableDoubleBuffering();

        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }

        for (double time = 0; time < T; time += dt) {
           for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
           }
           for (int i = 0; i < planets.length; i++) {
               planets[i].update(dt, xForces[i], yForces[i]);
           }
           drawBg();

           for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
           }
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel,
            planets[i].mass, planets[i].imgFileName);
        }
    }

    public static void drawBg() {
        StdDraw.setScale(-radius, radius);
		/* Clears the drawing window. */
		StdDraw.clear();

        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();
		StdDraw.pause(2000);		
    }
    public static double readRadius(String filename) {
        In in = new In(filename);

        int numOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    };

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        // how to leap over this verbose process
        int numOfPlanets = in.readInt();
        double radius = in.readDouble();
        int i = 0;
        Planet[] res = new Planet[5];
        while (i < 5) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            res[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            i++;
        }

        return res;
    }
    
}