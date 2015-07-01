/** 
 *
 * Clase Avion de la práctica final de Gráficos y Visualización 3D
 * Grado ISAM, curso 2014-2015
 * Aeropuerto, 
 * @author Miguel Ángel Fernández Sánchez
 *
 **/
package pfinal;


import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.opengl.GL11;

import pfinal.ModelLoader;
import Utils.Matrix4f;
import Utils.OpenGLHelper;

public class Avion extends Dibujable {
    
	private final ModelLoader fmodel = new ModelLoader("src/pfinal/a380.obj");
	
	private float count = 0.0f;
    private float count2 = 0.0f;
    private float count3 = 0.0f;
    
    public float posX = 0.0f;
    public float posY = 0.0f;
    public float posZ = 0.0f;
    private float speed;
    float mi_veloc;
    
    int vbo_v, vbo_c, vbo_e;

    float x, y, z;
    float sx, sy, sz;
    float rx, ry, rz, angulo;
        

    public Avion() {
        
    	vbo_v = fmodel.createVerticesBuffer();
        vbo_c = fmodel.createColorsBuffer();

        x = y = z = rx = ry = rz = 0.0f;
        sx = sy =sz = 1.0f;
        speed = 0.0f;
    }

	public int getVertexPositions() {
		return vbo_v;
	}

	public int getVertexColor() {
		return vbo_c;
	}

	public int getTexCoord() {
		return  0;
	}

	public int getElementsBuffer() {
		return vbo_e;
	}
	
	public void prepararBuffers(OpenGLHelper openGLHelper) {
		
		// Preparando Buffers de posición
		int posAttrib = openGLHelper.getShaderProgram().getAttributeLocation("aVertexPosition");
        glEnableVertexAttribArray(posAttrib);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexPositions());

        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);

        int colAttrib = openGLHelper.getShaderProgram().getUniformLocation("color");
		glEnableVertexAttribArray(colAttrib);
		glVertexAttribPointer(colAttrib, 3, GL_FLOAT, false, 3 * GL_FLOAT, 0);
		     
		// Preparando Buffers de color (Sale negro porque no hay informacion)

        int vertexColorAttribute = openGLHelper.getShaderProgram().getAttributeLocation("aVertexColor");
        glEnableVertexAttribArray(vertexColorAttribute);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexColor());
        glVertexAttribPointer(vertexColorAttribute, 3, GL_FLOAT, false, 0, 0);
	}

	public void dibujar(OpenGLHelper openGLHelper) {
		mi_veloc = getSpeed();
		if (mi_veloc > 0.0f){
			count += mi_veloc;
	    	count2 += 0.01f;
	        
	        posX = this.x + count;
	        if (posX > 400.0){
	        	posX = this.x;
	        	count = this.getSpeed();
	        	posY = this.y;
	        	count3 = 0.0f;
	        }
	        if (posX > 35.0){
	        	count3 += 0.03f;
	        	if (count3 > 5){
	        		count3 += 0.03f;
	        	}
	        	posY = this.y + count3;
	        }else{
	        	posY = this.y;
	        }
		}
		
        float posZ = this.z;
        
	    Matrix4f model = Matrix4f.rotate(angulo, rx, ry, rz); 
        model = Matrix4f.scale(sx, sy, sz).multiply(model);
        model = Matrix4f.translate(posX, posY, posZ).multiply(model);
        glUniformMatrix4(openGLHelper.getShaderProgram().getUniformLocation("model"), false, model.getBuffer());
       
        glDrawArrays(GL11.GL_TRIANGLES, 0, fmodel.getNumVertices()*3);	
	}


	public void setRotation(float nangle, float x, float y, float z) {	
		rx = x;
		ry = y;
		rz = z;
		angulo = nangle;
		
	}

	public void setPosition(float px, float py, float pz) {
		x = px;
		posX = x;
		y = py;
		posY = y;
		z = pz;
	}
	
	public void setPosition(float px, float py, float pz, float veloc) {	
		x = px;
		posX = x;
		y = py;
		posY = y;
		z = pz;
		speed = veloc;
	}
	
	public void setSpeed(float veloc) {
		speed = veloc;
	}

	public float getSpeed() {
		return speed;
	}
	
	public void setScale(float nsx, float nsy, float nsz) {
		sx = nsx;
		sy = nsy;
		sz = nsz;
	}
}