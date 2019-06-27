public class Planet{
	//instance variables
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	//constructors
	// xP(double): current x position of planet p.
 //    yP(double): current y position of planet p.
 //    xV(): current velocity in the x direction of planet p
 //    yV(): current velocity in the y direction of planet p
 //    m(double): mass of planet p
 //    img(String): name of the file that corresponds to the image that depicts the planet p
	public Planet(double xP,  double yP, double xV,
				   double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;	
	}
	//initialize an identical planet object

	public Planet(Planet p){
		xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

	}
	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(Planet p){
		return (G/Math.pow(calcDistance(p),2)* this.mass * p.mass);
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos - this.xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p)*(p.yyPos - this.yyPos)/calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] ps){
		double xForce = 0;
		for(Planet p:ps){
			if(!this.equals(p)){
				xForce += calcForceExertedByX(p);
			}
		}
		return xForce;
	}
	public double calcNetForceExertedByY(Planet[] ps){
		double yForce = 0;
		for(Planet p:ps){
			if(!this.equals(p)){
				yForce += calcForceExertedByY(p);
			}
		}
		return yForce;
	}
	/**
	 * Calculate how much forces exerted on the planet will cause the 
	 * planet to accelerate
	 */
	public void update(double dt, double Fx, double Fy){
		//calculate the acceleration using hte provided x and y force
		double aX = Fx/this.mass;
		double aY = Fy/this.mass;

		//calculate the new velocity by using the acceleration
		// and current velocity
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;

		//calculate the new position by using the velocity
		//computed in step 2 and the current position
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;



	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
		StdDraw.show();

	}


} 