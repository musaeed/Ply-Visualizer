package PlyParser;

import java.util.ArrayList;

import DataStructures.Point3D;
import DataStructures.Triangle;

public class Operations {

	public static float getSmallestPoint(ArrayList<Point3D> points){
		float ans = 1000.0f;
		
		for(int i = 0 ; i < points.size() ; i++){
			if(points.get(i).getX() < ans){
				ans = points.get(i).getX();
			}
			if(points.get(i).getY() < ans){
				ans = points.get(i).getY();
			}
			if(points.get(i).getZ() < ans){
				ans = points.get(i).getZ();
			}
		}
		
		return ans;
	}
	
	public static float getLargestPoint(ArrayList<Point3D> points){
		float ans = 0.0f;
		
		for(int i = 0 ; i < points.size() ; i++){
			if(points.get(i).getX() > ans){
				ans = points.get(i).getX();
			}
			if(points.get(i).getY() > ans){
				ans = points.get(i).getY();
			}
			if(points.get(i).getZ() > ans){
				ans = points.get(i).getZ();
			}
		}
		
		return ans;
	}
	
	public static ArrayList<Point3D> TranslatePoints(float translate , ArrayList<Point3D> points){
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		
		if(translate < 0){
			translate = -1.0f*translate;
		}
		
		for(Point3D point : points){
			ans.add(new Point3D(point.getX() + translate, point.getY() + translate, point.getZ() + translate));
		}
		
		return ans;
	}
	
	public static ArrayList<Point3D> ScalePoints(ArrayList<Point3D> points){
		float scale = getLargestPoint(points);
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		
		for(Point3D point : points){
			ans.add(new Point3D(point.getX()/scale, point.getY()/scale, point.getZ()/scale));
		}
		
		return ans;
	}
	
	public static ArrayList<Point3D> ScalePoints(ArrayList<Point3D> points , float ratio){

		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		
		for(Point3D point : points){
			ans.add(new Point3D(point.getX()*ratio, point.getY()*ratio, point.getZ()*ratio));
		}
		
		return ans;
	}
	
	public static ArrayList<Point3D> translateXAndYByHalf(ArrayList<Point3D> points){
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		
		for(Point3D point : points){
			ans.add(new Point3D(point.getX() - 0.5f, point.getY() - 0.5f, point.getZ()));
		}
		
		return ans;
	}
	
	public static ArrayList<Point3D> getNewPoints(ArrayList<Point3D> points){
		
		points = TranslatePoints(getSmallestPoint(points), points);
		points = ScalePoints(points);
		points = translateXAndYByHalf(points);
		points = ScalePoints(points, 2.0f);
		
		return points;
	}
	
	public static ArrayList<Point3D> setTranslation(ArrayList<Point3D> points , Point3D transVector){
		
		float x = transVector.getX() , y = transVector.getY() , z = transVector.getZ();
		
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		
		for(Point3D point : points){
			ans.add(new Point3D(point.getX() + x, point.getY() + y, point.getZ() + z));
		}
		
		return ans;
	}
	
	public static Point3D crossProduct(Point3D p1 , Point3D p2){
		  
		  float x = p1.getY() * p2.getZ() - p2.getY() * p1.getZ();
		  float y = p1.getZ() * p2.getX() - p2.getZ() * p1.getX();
		  float z = p1.getX() * p2.getY() - p2.getX() * p1.getY();
		  
		  return new Point3D(x,y,z);
		}
	
	public static ArrayList<Point3D> getNormals(ArrayList<Triangle> triangles , ArrayList<Point3D> points){
		
		ArrayList<Point3D> normals = new ArrayList<Point3D>();
		
		int count = 0;
		Point3D sumVector = new Point3D(0.0f, 0.0f, 0.0f);
		
		for(int i = 0 ; i < points.size() ; i++){
			for(Triangle triangle : triangles){
				if(triangle.isPointInside(i)){
					Point3D tNormal = crossProduct(points.get(triangle.getindX()) , points.get(triangle.getindY()));
					sumVector.add(tNormal);
					count++;
				}
			}
			
			Point3D temp = new Point3D(sumVector.getX()/(float)count, sumVector.getY()/(float)count,sumVector.getZ()/(float)count);
			temp.normalize();
			
			normals.add(temp);
			count = 0;
			sumVector.setXYZ(0.0f, 0.0f, 0.0f);
		}
		
		return normals;
	}
}
