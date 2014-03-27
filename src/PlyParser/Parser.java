package PlyParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import DataStructures.Point3D;
import DataStructures.Triangle;
import Gui.Progressbar;

public class Parser {
	
	private File file;
	private int iVertices , iFaces;
	private ArrayList<Point3D> points;
	private ArrayList<Triangle> triangles;
	private InputStream stream;
	private boolean isStream;
	
	public Parser(String filename){
		
		this.file = new File(filename);
		points = new ArrayList<Point3D>();
		triangles = new ArrayList<Triangle>();
		iVertices = 0;
		iFaces = 0;
		isStream = false;
		
	}
	
	public Parser(InputStream stream){
		this.stream = stream;
		points = new ArrayList<Point3D>();
		triangles = new ArrayList<Triangle>();
		iVertices = 0;
		iFaces = 0;
		isStream = true;
	}
	
	
	
	@SuppressWarnings("resource")
	public void parse(){
		
		Scanner sc = null;
		Progressbar.setValue(5);
		try {
			sc = isStream? new Scanner(stream) : new Scanner(file);
		} catch (FileNotFoundException e) {
			try {
				Runtime.getRuntime().exec(new String [] {"notify-send", "PLY Visualizer" , "The file cannot be opened"});
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		
		double count = 5.0;
		
		while(sc.hasNext()){
			String temp = sc.nextLine();
			
			if(temp.equals("end_header")){
				break;
			}
			
			StringTokenizer st = new StringTokenizer(temp);
			
			if(st.nextToken().equals("element")){
				String identifier = st.nextToken();
				if(identifier.equals("vertex")){
					iVertices = Integer.parseInt(st.nextToken());
					continue;
				}
				if(identifier.equals("face")){
					iFaces = Integer.parseInt(st.nextToken());
					continue;
				}
			}
			if(count < 30.0){
				Progressbar.setValue((int)count);
				count += 0.3;
			}
		}
		
		Progressbar.setValue(30);
		count = 30;
		
		for(int i = 0 ; i < iVertices ; i++){
			String temp = sc.nextLine();
			Scanner getter = new Scanner(temp);
			points.add(new Point3D(getter.nextFloat(), getter.nextFloat(), getter.nextFloat()));
			getter.close();
			if(count < 50){
				Progressbar.setValue((int)count);
				count += 0.3;
			}
		}
		
		points = Operations.getNewPoints(points);
		Progressbar.setValue(50);
		count = 50.0;
		
		for(int i = 0 ; i < iFaces ; i++){
			String temp = sc.nextLine();
			Scanner getter = new Scanner(temp);
			getter.nextInt();
			triangles.add(new Triangle(getter.nextInt() , getter.nextInt(),getter.nextInt()));
			getter.close();
			
			if(count < 80.0){
				Progressbar.setValue((int)count);
				count+=0.3;
			}
		}
		
		
		Progressbar.setValue(80);
	}
	
	public int getIVertices(){
		return iVertices;
	}
	
	public int getIFaces(){
		return iFaces;
	}
	
	public ArrayList<Point3D> getPoints(){
		return points;
	}
	
	public ArrayList<Triangle> getTriangles(){
		return triangles;
	}

}
