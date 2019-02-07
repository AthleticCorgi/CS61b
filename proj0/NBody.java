public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        Planet[] p = new Planet[n];
        in.readDouble();
        for (int i = 0; i < n; i++) {
            p[i] = new Planet (in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                in.readDouble(), in.readString());
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = NBody.readRadius(fileName);
        Planet[] p = NBody.readPlanets(fileName);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet pl : p) {
            pl.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
        double time = 0;
        double[] xForces = new double[p.length];
        double[] yForces = new double[p.length];
        while (time < T) {
            for (int i = 0; i < p.length; i++) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for (int i = 0; i < p.length; i++) {
                p[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet pl : p) {
                pl.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
                p[i].xxPos, p[i].yyPos, p[i].xxVel, 
                p[i].yyVel, p[i].mass, p[i].imgFileName); 
        }
    }
}

