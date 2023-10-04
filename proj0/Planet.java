public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV, 
                    double yV, double m, String img) {
                        this.xxPos = xP;
                        this.yyPos = yP;
                        this.xxVel = xV;
                        this.yyVel = yV;
                        this.mass = m;
                        this.imgFileName = img;
                    };

    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    };

    public double calcDistance(Planet p) {
        double dxSquare = Math.pow(this.xxPos - p.xxPos, 2);
        double dySquare = Math.pow(this.yyPos - p.yyPos, 2);

        return Math.sqrt(dxSquare + dySquare);
    }

    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        return (Planet.G * this.mass * p.mass) / Math.pow(dis, 2);
    }

    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;

        return force * dx / dis;

    }

    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double dx = p.yyPos - this.yyPos;

        return force * dx / dis;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netForceX = 0;
        for (int i = 0; i < ps.length; i++) {
            if (!this.equals(ps[i])) {
                double res = this.calcForceExertedByX(ps[i]);
                netForceX += res;
            }
        }

        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netForceY = 0;
        for (int i = 0; i < ps.length; i++) {
            if (!this.equals(ps[i])) {
                double res = this.calcForceExertedByY(ps[i]);
                netForceY += res;
            }
        }

        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" +imgFileName);
        StdDraw.show();
    }
}