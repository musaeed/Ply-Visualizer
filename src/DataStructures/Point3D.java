package DataStructures;

public class Point3D {
	
	private float x;
	private float y;
	private float z;
	
	public Point3D(float x , float y , float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
	
	public float getZ(){
		return z;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setZ(float z){
		this.z = z;
	}
	
	public void setXYZ(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Point3D p){
		this.x += p.getX();
		this.y += p.getY();
		this.z += p.getZ();
	}
	
	public void normalize(){
		double x = this.x , y = this.y, z = this.z;
		double length = Math.sqrt(x*x+y*y+z*z);
		
		this.x = this.x / (float)length;
		this.y = this.y / (float)length;
		this.z = this.z / (float)length;
	}
}
