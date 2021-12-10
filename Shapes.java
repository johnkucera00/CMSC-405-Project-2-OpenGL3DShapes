/* 
* File: Shapes.java
* Author: John Kucera
* Date: September 8, 2021
* Purpose: This Java program is meant to hold information about the vertices,
* faces, and face colors of 3 shapes (Rectangular Prism, Tetrahedron, Rhombic
* Dodecahedron) in arrays. These arrays will be used to create the respective
* shapes in JoglGUI.java.
*/

// Class: Shapes
public class Shapes {
    
    // Variable Initialization. Arrays hold dimensions
    public double[][] vertices;
    public int[][] faces;
    public double[][] faceColors;

    // Method: Constructor
    public Shapes(double[][] vertices, int[][] faces, double[][] faceColors) {
        this.vertices = vertices;
        this.faces = faces;
        this.faceColors = faceColors;
    } // end of method
    
    // Shapes Object: Rectangular Prism
    public static Shapes recPrism = new Shapes (
        new double[][] { // Vertices
            {3,1,1},
            {3,1,-1},
            {3,-1,-1},
            {3,-1,1},
            {-1,1,1},
            {-1,1,-1},
            {-1,-1,-1},
            {-1,-1,1}
        },
        new int[][] { // Faces
            {0,1,2,3},
            {0,3,7,4},
            {0,4,5,1},
            {6,2,1,5},
            {6,5,4,7},
            {6,7,3,2}
        },
        new double[][] { // Face Colors
            {0.1,0.2,0.3},
            {0.4,0.7,0.8},
            {0.8,0.3,0.5},
            {0.7,0.6,1},
            {0.3,0.7,0.1},
            {0.9,1,0},
        }
    ); // end of object
    
    // Shapes Object: Tetrahedron
    public static Shapes tetrahedron = new Shapes (
        new double[][] { // Vertices
            {2,-1,-1},
            {-2.5,-1,1},
            {-2.5,1,-1},
            {2,1,1}
        },
        new int[][] { // Faces
            {0,3,2},
            {3,0,1},
            {0,2,1},
            {2,3,1}
        },
        new double[][] { // Face Colors
            {0.1,0,0.7},
            {0.5,0.8,0.2},
            {1,1,0},
            {0.4,0,0}
        }
    ); // end of object
    
    // Shapes Object: Rhombic Dodecahedron
    public static Shapes rhombicDodecahedron = new Shapes (
        new double[][] { // Vertices
            {0,1.5,0},
            {-1,-1,-1},
            {1,-1,-1},
            {1,1,-1},
            {-1,1,-1},
            {-1,-1,1},
            {1,-1,1},
            {1,1,1},
            {-1,1,1},
            {0,0,2},
            {0,0,-2},
            {-2,0,0},
            {2,0,0},
            {0,-1.5,0}
        },
        new int[][] { // Faces
            {1,13,2,10},
            {2,12,3,10},
            {3,0,4,10},
            {4,11,1,10},
            {2,13,6,12},
            {3,12,7,0},
            {4,0,8,11},
            {1,11,5,13},
            {5,9,6,13},
            {6,9,7,12},
            {7,9,8,0},
            {8,9,5,11}
        },
        new double[][] { // Face Colors
            {0.7,0.6,1},
            {0.3,0.7,0.1},
            {0.1,0.2,0.3},
            {0.4,0.7,0.8},
            {0.8,0.3,0.5},
            {0.9,1,0},
            {0.1,0.2,0.3},
            {0.7,0.6,1},
            {0.3,0.7,0.1},
            {0.9,1,0},
            {0.4,0.7,0.8},
            {0.8,0.3,0.5}
        }
    ); // end of object
} // end of class
