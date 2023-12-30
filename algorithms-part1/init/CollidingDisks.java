import edu.princeton.cs.algs4.CollisionSystem;
import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdDraw;

public class CollidingDisks {
    public static void main(String[] args) {
        int n = 20;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        }

        StdDraw.enableDoubleBuffering();

        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle();
        }

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(Double.POSITIVE_INFINITY);
    }
}