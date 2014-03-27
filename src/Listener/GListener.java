package Listener;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import Components.BottomPanel;
import DataStructures.Point3D;
import DataStructures.Triangle;
import Gui.MainFrame;
import Gui.Progressbar;
import PlyParser.Operations;
import PlyParser.Parser;

import com.jogamp.opengl.util.gl2.GLUT;

public class GListener implements GLEventListener{

	public static ArrayList<Point3D> points = new ArrayList<Point3D>(),normals= new ArrayList<Point3D>();
	public static ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	public static float x_pos = 1;
	public static float y_pos = 1;
	public static float z_pos = 1;
	public static float theta = (float)Math.PI/6;
	public static float phi  = (float)Math.PI/2;
	public static float R = 8;
	public static float cR = 0.0f , cG = 0.0f , cB = 0.0f;
	public static float oR = 0.0f, oG = 1.0f , oB = 0.0f;

	public static boolean isTranslation = false , isNewFile = false , isNewStream = false , showAxes = true;
	public static Point3D translationVector = new Point3D(0.0f, 0.0f, 0.0f);
	public static String currentFile = "";

	GLUT glut;

	@Override
	public void display(GLAutoDrawable e) {

		if(isNewFile){
			parseFile();
		}
		if(isNewStream){
			parseStream();
		}

		GL2 gl = e.getGL().getGL2();
		GLU glu = GLU.createGLU(gl);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		x_pos =(float) (R * Math.cos(phi)*Math.cos(theta));
		y_pos = (float) (R * Math.sin(theta));
		z_pos = (float)(R * Math.sin(phi)*Math.cos(theta));

		gl.glClearColor(cR, cG, cB, 0.5f);
		//gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		//gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);


		//float qaBlack[] = {0.0f,0.0f,0.0f,1.0f};
		float qaGreen[] = {oR,oG,oB,1.0f};
		float qaWhite[] = {1.0f,1.0f,1.0f,1.0f};

		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_AMBIENT, getFloatBuffer(qaGreen));
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_DIFFUSE, getFloatBuffer(qaGreen));
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_SPECULAR, getFloatBuffer(qaWhite));
		gl.glMaterialf(GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_SHININESS, 100.0f);
		gl.glLoadIdentity();


		if (Math.cos(theta)>=0){
			glu.gluLookAt(x_pos, y_pos, z_pos, 0, 0, 0, 0, 1, 0);
		}
		else{
			glu.gluLookAt(x_pos, y_pos, z_pos, 0, 0, 0, 0, -1, 0);
		}
		if(showAxes){
			drawAxes(e);
		}


		if(isTranslation){
			points = Operations.setTranslation(points, translationVector);
			isTranslation = false;
		}

		gl.glBegin(GL.GL_TRIANGLES);

		for(int i = 0 ; i < triangles.size(); i++){


			gl.glNormal3f(normals.get(triangles.get(i).getindX()).getX(), normals.get(triangles.get(i).getindX()).getY(), normals.get(triangles.get(i).getindX()).getZ());
			gl.glVertex3f(points.get(triangles.get(i).getindX()).getX(), points.get(triangles.get(i).getindX()).getY(), points.get(triangles.get(i).getindX()).getZ());


			gl.glNormal3f(normals.get(triangles.get(i).getindY()).getX(), normals.get(triangles.get(i).getindY()).getY(), normals.get(triangles.get(i).getindY()).getZ());
			gl.glVertex3f(points.get(triangles.get(i).getindY()).getX(), points.get(triangles.get(i).getindY()).getY(), points.get(triangles.get(i).getindY()).getZ());

			gl.glNormal3f(normals.get(triangles.get(i).getindZ()).getX(), normals.get(triangles.get(i).getindZ()).getY(), normals.get(triangles.get(i).getindX()).getZ());
			gl.glVertex3f(points.get(triangles.get(i).getindZ()).getX(), points.get(triangles.get(i).getindZ()).getY(), points.get(triangles.get(i).getindX()).getZ());
		}


		gl.glEnd();

		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable e) {
	}

	@Override
	public void init(GLAutoDrawable e) {

		GL2 gl = e.getGL().getGL2();

		gl.glClearColor(0.0f, 0.0f, 0.0f,0.0f);
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-1.0 , 1.0 , -1.0, 1.0, -1.0, 1.0);

		gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, GL.GL_TRUE);
		gl.glEnable(GLLightingFunc.GL_LIGHTING);
		gl.glEnable(GLLightingFunc.GL_LIGHT0);
		gl.glEnable(GLLightingFunc.GL_LIGHT1);

		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		float qaAmbientLight[] = {0.2f,0.2f,0.2f,1.0f};
		float qaDiffuseLight[] = {0.8f,0.8f,0.8f,1.0f};
		float qaSpecularLight[] = {1.0f,1.0f,1.0f,1.0f};

		ByteBuffer vbb = ByteBuffer.allocateDirect(qaAmbientLight.length * 4); 
		vbb.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		FloatBuffer fbq = vbb.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		fbq.put(qaAmbientLight);    // add the coordinates to the FloatBuffer
		fbq.position(0);

		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, fbq);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_AMBIENT, fbq);

		
		ByteBuffer vbbd = ByteBuffer.allocateDirect(qaDiffuseLight.length * 4); 
		vbbd.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		FloatBuffer fbd = vbbd.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		fbd.put(qaDiffuseLight);    // add the coordinates to the FloatBuffer
		fbd.position(0);

		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, fbd);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_DIFFUSE, fbd);


		ByteBuffer vbbs = ByteBuffer.allocateDirect(qaSpecularLight.length * 4); 
		vbbs.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		FloatBuffer fbs = vbbs.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		fbs.put(qaSpecularLight);    // add the coordinates to the FloatBuffer
		fbs.position(0);

		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_SPECULAR, fbs);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_SPECULAR, fbs);


		float lightPosition [] = {0.5f,0.5f,-3.0f,1.0f};
		ByteBuffer vbbl = ByteBuffer.allocateDirect(lightPosition.length * 4); 
		vbbl.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		FloatBuffer fbl = vbbl.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		fbl.put(qaAmbientLight);    // add the coordinates to the FloatBuffer
		fbl.position(0);

		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, fbl);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_POSITION, getFloatBuffer(new float[] {0.5f,0.5f,3.0f,1.0f}));
		
		glut = new GLUT();
	}


	@Override
	public void reshape(GLAutoDrawable e, int arg1, int arg2, int w, int h) {

		GL2 gl = e.getGL().getGL2();
		GLU glu = GLU.createGLU(gl);

		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, w, h);
		glu.gluPerspective(60.0, (float) w/(float) h, 0.0, 60.0);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}

	public void drawAxes(GLAutoDrawable e)
	{
		GL2 gl = e.getGL().getGL2();

		gl.glBegin( GL.GL_LINES );
		gl.glVertex3f( -50.0f, 0.0f, 0.0f );
		gl.glVertex3f( 50.0f, 0.0f, 0.0f );
		gl.glEnd();

		gl.glBegin( GL.GL_LINES );
		gl.glVertex3f( 0.0f, -50.0f, 0.0f );
		gl.glVertex3f( 0.0f, 50.0f, 0.0f );
		gl.glEnd();

		gl.glBegin( GL.GL_LINES );
		gl.glVertex3f( 0.0f, 0.0f, -50.0f );
		gl.glVertex3f( 0.0f, 0.0f, 50.0f );
		gl.glEnd();
	}

	public void parseFile(){
		Parser parser = new Parser(currentFile);
		parser.parse();
		points = parser.getPoints();
		triangles = parser.getTriangles();
		Progressbar.setValue(90);
		normals = Operations.getNormals(triangles, points);
		Progressbar.setValue(100);
		isNewFile = false;
		Progressbar.setValue(0);
		MainFrame.canvas.requestFocus();
	}

	public void parseStream(){
		Parser parser = new Parser(BottomPanel.fileStream);
		parser.parse();
		points = parser.getPoints();
		triangles = parser.getTriangles();
		Progressbar.setValue(90);
		normals = Operations.getNormals(triangles, points);
		Progressbar.setValue(100);
		isNewStream = false;
		Progressbar.setValue(0);
		MainFrame.canvas.requestFocus();
	}

	public FloatBuffer getFloatBuffer(float [] arr){
		ByteBuffer vbbl = ByteBuffer.allocateDirect(arr.length * 4); 
		vbbl.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		FloatBuffer fbl = vbbl.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
		fbl.put(arr);    // add the coordinates to the FloatBuffer
		fbl.position(0);

		return fbl;
	}
}
