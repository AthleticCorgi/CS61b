public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV,
        double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    
    public double calcDistance(Planet p) {
        double rSquare = (xxPos - p.xxPos) * (xxPos - p.xxPos) +
        (yyPos - p.yyPos) * (yyPos - p.yyPos);
        return Math.sqrt(rSquare);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForce = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForce += this.calcForceExertedByX(p);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForce = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForce += this.calcForceExertedByY(p);
            }
        }
        return netForce;
    }
    
    public void update(double dt, double fX, double fY) {
        xxPos = xxPos + dt * xxVel + dt * dt * fX / mass;
        yyPos = yyPos + dt * yyVel + dt * dt * fY / mass;
        xxVel = xxVel + dt * fX / mass;
        yyVel = yyVel + dt * fY / mass;
    }

    private boolean equals (Planet p) {
        return (xxPos == p.xxPos && yyPos == p.yyPos && mass == p.mass
            && xxVel == p.xxVel && yyVel == p.yyVel && imgFileName == p.imgFileName);
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}
