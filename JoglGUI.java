/*
* File: JoglGUI.java
* Author: John Kucera
* Date: September 8, 2021
* Purpose: This Java program is meant to create a unique graphics scene using
* OpenGL graphic components and transformation methods translate, rotate, and
* scale. 3 shapes icosahedron, sphere, torus, are made using GLUT. The other
* 3 shapes rectangular prism, tetrahedron, and rhombic dodecahedron are made
* using array sets of vertices/faces/colors created in Shapes.java. A key
* listener is implemented so that the user can use WASD for translation,
* arrow keys for rotation, and +/- for scaling. The panel updates for each
* transformation.
*/

// import necessary java classes
import java.awt.event.*;
import javax.swing.JFrame;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.util.gl2.GLUT;

// Class: JoglGUI
public class JoglGUI extends GLJPanel implements GLEventListener, KeyListener {
    
    // Variable Initialization
    private double scale = 0.2;
    private double rotateX = 220;
    private double rotateY = 20;
    private double translateX = 0;
    private double translateY = 0;
    private final GLUT glut = new GLUT();
    
    // Method: main. Creates Frame and initializes JOGL
    public static void main(final String[] args) {
        final JFrame window = new JFrame("Use: Arrow keys[Rotate], WASD[Translate], +/-[Scale]");
        final JoglGUI panel = new JoglGUI();
        window.setContentPane(panel);
        window.setSize(700,500);
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
    } // end of method

    // Method: Constructor
    public JoglGUI() {
        super(new GLCapabilities(null));
        addGLEventListener(this);
        addKeyListener(this);
    } // end of method

    // Method: create. Draws shapes stored in arrays from Shapes.java.
    private void create(GL2 gl2, Shapes shape) {
        int i,j;
        // Coloring in faces and vertices
        for (i = 0; i < shape.faces.length; i++) {
            gl2.glPushMatrix();
            gl2.glColor3dv(shape.faceColors[i], 0);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (j = 0; j < shape.faces[i].length; j++) {
                int vertexNum = shape.faces[i][j];
                gl2.glVertex3dv(shape.vertices[vertexNum], 0);
            } // end of inner for
            gl2.glEnd();
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (j = 0; j < shape.faces[i].length; j++) {
                int vertexNum = shape.faces[i][j];
                gl2.glVertex3dv(shape.vertices[vertexNum], 0);
            } // end of inner for
            gl2.glEnd();
            gl2.glPopMatrix();
        } // end of for
    } // end of method
    
    // Method: display. Creates 6 shapes: 3 shapes using GLUT and calls
    // create() for 3 shapes in Shapes.java.
    @Override
    public void display(final GLAutoDrawable drawable) {
        // Create GL2 object
        final GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        gl2.glTranslated(translateX, translateY, 0);

        // (GLUT) Icosahedron
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(0, 0, 0);
        gl2.glColor3d(0.1, 0.8, 0.7);
        glut.glutSolidIcosahedron();
        gl2.glPopMatrix();
        
        // (GLUT) Sphere
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(0, 0, -3);
        gl2.glColor3d(0.9, 0.5, 0.1);
        glut.glutSolidSphere(1, 10, 10);
        gl2.glPopMatrix();
        
        // (GLUT) Torus
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(-3, 0, 0);
        gl2.glColor3d(0.1, 0.4, 0.1);
        glut.glutSolidTorus(0.5, 1, 30, 30);
        gl2.glPopMatrix();
        
        // (Shapes.java Array) Rectangular Prism
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(0, 3, 0);
        create(gl2, Shapes.recPrism);
        gl2.glPopMatrix();
        
        // (Shapes.java Array) Tetrahedron
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(0, -3, 0);
        create(gl2, Shapes.tetrahedron);
        gl2.glPopMatrix();
        
        // (Shapes.java Array) Rhombic Dodecahedron
        gl2.glPushMatrix();
        gl2.glScaled(scale, scale, scale);
        gl2.glRotated(rotateY, 1, 0, 0);
        gl2.glRotated(rotateX, 0, 1, 0);
        gl2.glTranslated(3, 0, 0);
        create(gl2, Shapes.rhombicDodecahedron);
        gl2.glPopMatrix();
    } // end of method

    // Method: init. Set up GL when panel is created.
    @Override
    public void init(final GLAutoDrawable drawable) {
        final GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl2.glOrtho(-1, 1, -1, 1, -1, 1);
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glClearColor(0, 0, 0, 1);
        gl2.glEnable(GL.GL_DEPTH_TEST);
        
        // Lighting settings
        gl2.glEnable(GL2.GL_LIGHTING);
        gl2.glEnable(GL2.GL_LIGHT0);
        gl2.glEnable(GL2.GL_NORMALIZE);   
        gl2.glEnable(GL2.GL_COLOR_MATERIAL);
    } // end of method

    // Method: keyPressed. Whenever appropriate key is pressed, transformation
    // will occur, then panel is repainted to show updates.
    @Override
    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            // TRANSLATIONS
            case KeyEvent.VK_W:
                translateY += 0.1;
                break;
            case KeyEvent.VK_A:
                translateX -= 0.1;
                break;
            case KeyEvent.VK_S:
                translateY -= 0.1;
                break;
            case KeyEvent.VK_D:
                translateX += 0.1;
                break;
            
            // ROTATIONS
            case KeyEvent.VK_UP:
                rotateY -= 2;
                break;
            case KeyEvent.VK_LEFT:
                rotateX -= 2;
                break;
            case KeyEvent.VK_DOWN:
                rotateY += 2;
                break;
            case KeyEvent.VK_RIGHT:
                rotateX += 2;
                break;
            
            // SCALING
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_EQUALS:
                scale += 0.05;
                break;
            case KeyEvent.VK_MINUS:
                scale -= 0.05;
                break;
        } // end of switch
        repaint();
    } // end of method

    // Methods reshape, dispose, keyTyped, keyReleased: Overridden abstract
    // methods from implementing Listener interfaces
    @Override
    public void reshape(final GLAutoDrawable gLDrawable, final int x, final int y, final int width, int height) {
    
    }

    @Override
    public void dispose(final GLAutoDrawable drawable) {
    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
} // end of class