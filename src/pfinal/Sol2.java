package pfinal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utils.Matrix4f;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import Utils.OpenGLHelper;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 *
 * @author MiguelAngel
 */
public class Sol2 extends Dibujable{
    float x, y, z;
    float rx, ry, rz;
    float sx, sy, sz;
    int speed = 600;
    int num_people;
    private float count = 0;
    private float count2 = 0;
    private float count3 = 0;
    private int vbo_v;
    private int vbo_c;

    public Sol2(){
    	x = y = z = rx = ry = rz = 0.0f;
        sx = sy = sz = 1.0f;
    }
    public void TakeOff() {
        System.out.println("Avion despegando...");
    }
    
    public void LandPlane() {
        System.out.println("Aterrizaje OK");
    }
    
    public void PlaneSpeed() {
        System.out.println("La velocidad del avion es: " + speed + " km/h.");
    }

    float[] vertices = new float[]
            {
                  1.0f,  1.0f,  1.0f ,         // Top Right Of The Quad (Front)
                 -1.0f,  1.0f,  1.0f ,         // Top Left Of The Quad (Front)
                 -1.0f, -1.0f,  1.0f ,         // Bottom Left Of The Quad (Front)
                  1.0f, -1.0f,  1.0f ,         // Bottom Right Of The Quad (Front)
                  
                  1.0f, 1.0f, -1.0f ,          // Top Right Of The Quad (Top)
                 -1.0f, 1.0f, -1.0f ,          // Top Left Of The Quad (Top)
                 -1.0f, 1.0f,  1.0f ,          // Bottom Left Of The Quad (Top)
                  1.0f, 1.0f,  1.0f ,          // Bottom Right Of The Quad (Top)
            
                  1.0f, -1.0f,  1.0f ,         // Top Right Of The Quad (Bottom)
                 -1.0f, -1.0f,  1.0f ,         // Top Left Of The Quad (Bottom)
                 -1.0f, -1.0f, -1.0f ,         // Bottom Left Of The Quad (Bottom)
                  1.0f, -1.0f, -1.0f ,         // Bottom Right Of The Quad (Bottom)
            
                  1.0f, -1.0f, -1.0f ,         // Bottom Left Of The Quad (Back)
                 -1.0f, -1.0f, -1.0f ,         // Bottom Right Of The Quad (Back)
                 -1.0f,  1.0f, -1.0f ,         // Top Right Of The Quad (Back)
                  1.0f,  1.0f, -1.0f ,         // Top Left Of The Quad (Back)
            
                 -1.0f,  1.0f,  1.0f ,         // Top Right Of The Quad (Left)
                 -1.0f,  1.0f, -1.0f ,         // Top Left Of The Quad (Left)
                 -1.0f, -1.0f, -1.0f ,         // Bottom Left Of The Quad (Left)
                 -1.0f, -1.0f,  1.0f ,         // Bottom Right Of The Quad (Left)

                  1.0f,  1.0f, -1.0f ,         // Top Right Of The Quad (Right)
                  1.0f,  1.0f,  1.0f ,         // Top Left Of The Quad (Right)
                  1.0f, -1.0f,  1.0f ,         // Bottom Left Of The Quad (Right)
                  1.0f, -1.0f, -1.0f           // Bottom Right Of The Quad (Right)
            };
    
    //Definimos los colores de los vertices
    float[] colors = new float[]
            {
                  1.0f,  1.0f,  0.0f ,         // Top Right Of The Quad (Front)
                  1.0f,  1.0f,  0.0f ,         // Top Left Of The Quad (Front)
                  1.0f,  1.0f,  0.0f ,         // Bottom Left Of The Quad (Front)
                  1.0f,  1.0f,  0.0f ,         // Bottom Right Of The Quad (Front)
                  
                  1.0f,  1.0f,  0.0f ,          // Top Right Of The Quad (Top)
                  1.0f,  1.0f,  0.0f ,          // Top Left Of The Quad (Top)
                  1.0f,  1.0f,  0.0f ,          // Bottom Left Of The Quad (Top)
                  1.0f,  1.0f,  0.0f ,          // Bottom Right Of The Quad (Top)
                  
                  1.0f,  1.0f,  0.0f ,         // Top Right Of The Quad (Bottom)
                  1.0f,  1.0f,  0.0f ,         // Top Left Of The Quad (Bottom)
                  1.0f,  1.0f,  0.0f ,         // Bottom Left Of The Quad (Bottom)
                  1.0f,  1.0f,  0.0f ,         // Bottom Right Of The Quad (Bottom)
            
                  1.0f,  1.0f,  0.0f ,         // Bottom Left Of The Quad (Back)
                  1.0f,  1.0f,  0.0f ,         // Bottom Right Of The Quad (Back)
                  1.0f,  1.0f,  0.0f ,         // Top Right Of The Quad (Back)
                  1.0f,  1.0f,  0.0f ,         // Top Left Of The Quad (Back)
            
                  1.0f,  1.0f,  0.0f ,         // Top Right Of The Quad (Left)
                  1.0f,  1.0f,  0.0f ,         // Top Left Of The Quad (Left)
                  1.0f,  1.0f,  0.0f ,         // Bottom Left Of The Quad (Left)
                  1.0f,  1.0f,  0.0f ,         // Bottom Right Of The Quad (Left)

                  1.0f,  1.0f,  0.0f ,         // Top Right Of The Quad (Right)
                  1.0f,  1.0f,  0.0f ,         // Top Left Of The Quad (Right)
                  1.0f,  1.0f,  0.0f ,         // Bottom Left Of The Quad (Right)
                  1.0f,  1.0f,  0.0f          // Bottom Right Of The Quad (Right)
            };
    
    public void prepararBuffers(OpenGLHelper openGLHelper){
    	// Definimos los vertices
    	System.out.println("Preparando avión...");
      
    }
    @Override
    public void dibujar(OpenGLHelper openGLHelper) {
        //System.out.println("Dibujando avión...");
    	count += 0.03f;
    	count2 += 0.01f;
    	count3 += 0.06f;
        
    	FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        int vbo_v = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo_v);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
 
        int posAttrib = openGLHelper.getShaderProgram().getAttributeLocation("aVertexPosition");
        glEnableVertexAttribArray(posAttrib);
        glBindBuffer(GL_ARRAY_BUFFER, vbo_v);
        
        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);
        
        FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();
 
        int vbo_c = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo_c);
        glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);

        int vertexColorAttribute = openGLHelper.getShaderProgram().getAttributeLocation("aVertexColor");
        glEnableVertexAttribArray(vertexColorAttribute);
        glBindBuffer(GL_ARRAY_BUFFER, vbo_c);
        glVertexAttribPointer(vertexColorAttribute, 3, GL_FLOAT, false, 0, 0);
        float posX = 1 + (float)Math.cos(count);
        float posY = 30 + (float)Math.sin(count2);
        float posZ = -7 + (float)Math.sin(count3);
        
        Matrix4f model = Matrix4f.rotate(count3+200, 0.7f, 1f, 1f);
        model = Matrix4f.scale(1.5f, 1.5f, 1.5f).multiply(model);
        model = Matrix4f.translate(posX, posY, posZ).multiply(model);
        glUniformMatrix4(openGLHelper.getShaderProgram().getUniformLocation("model"), false, model.getBuffer());
        glDrawArrays(GL_QUADS, 0, 4*6);
        
    }
    
}


/*Segun la tarjeta con el código que ha subido Agustinm
--> si no funciona: 
donde pone #version 150 -->borrar
poner 130 \n
*/