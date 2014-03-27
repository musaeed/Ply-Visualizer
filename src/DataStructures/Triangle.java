package DataStructures;

public class Triangle {
	
	private int indX;
	private int indY;
	private int indZ;
	
	public Triangle(int x , int y , int z){
		this.indX = x;
		this.indY = y;
		this.indZ = z;
	}
	
	public void setindX(int x){
		this.indX = x;
	}
	
	public void setindY(int y){
		this.indY = y;
	}
	
	public void setindZ(int z){
		this.indZ = z;
	}
	
	public int getindX(){
		return indX;
	}
	
	public int getindY(){
		return indY;
	}
	
	public int getindZ(){
		return indZ;
	}
	
	public boolean isPointInside(int index){
		
		if(index == indX || index == indY || index == indZ){
			return true;
		}
		return false;
		
	}
}
