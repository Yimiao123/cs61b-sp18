public class NBody{
	public static double readRadius(String fileName){
		// reading the file name
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] allPlanets = new Planet[N];
		for(int i = 0; i<N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			allPlanets[i]= new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);

		}
		return allPlanets;
	}

	public static void main (String[] args) {
		//get data
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet [] allPlanets = readPlanets(filename);
		double radius = readRadius(filename);

		//draw background
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p: allPlanets){
			p.draw();
		}
		//draw animation
		//prevent flicking in the animation
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time < T){
			double[] xForces = new double[allPlanets.length];
			double[] yForces = new double[allPlanets.length];
			for(int i = 0;i<allPlanets.length; i++){
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
				

			}
			for(int i = 0;i<allPlanets.length; i++){
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			//draw all of the planets
			for(Planet p:allPlanets){
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;

 		}
 		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName); 
                }  

	}

} 