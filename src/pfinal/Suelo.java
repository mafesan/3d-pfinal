package pfinal;

// Imports de OpenGL

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import Utils.Matrix4f;
import Utils.OpenGLHelper;
import Utils.PlaneModel;
import Utils.Texture;

public class Suelo extends Dibujable {

    private PlaneModel planeModel;

    int vbo_v, vbo_n, vbo_c, vbo_t, vbo_e;

    private Texture texture;

    float x, y, z;
    float sx, sy, sz;
    float rx, ry, rz, angulo;

    public Suelo() {

        planeModel = new PlaneModel(5.0f, 5.0f);

        vbo_v = planeModel.createVerticesBuffer();
        vbo_c = planeModel.createColorsBuffer();
        vbo_t = planeModel.createTextCoordsBuffer();
        vbo_e = planeModel.createIndicesBuffer();

        texture = Texture.loadTexture("mars.jpg");
        x = y = z = rx = ry = rz = 0.0f;
        sx = sy = sz = 1.0f;
    }

    
    public int getVertexPositions() {
		return vbo_v;
	}

    public int getVertexColor() {
		return vbo_c;
	}

    public int getTexCoord() {
		return vbo_t;
	}

    public int getElementsBuffer() {
		return vbo_e;
	}

    public void dibujar(OpenGLHelper openGLHelper) {

        int posAttrib = openGLHelper.getShaderProgram().getAttributeLocation("aVertexPosition");
        glEnableVertexAttribArray(posAttrib);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexPositions());

        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);

        // ----------------------- COLORS DATA -------------------------------//
        
        
        int vertexColorAttribute = openGLHelper.getShaderProgram().getAttributeLocation("aVertexColor");
        glEnableVertexAttribArray(vertexColorAttribute);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexColor());
        glVertexAttribPointer(vertexColorAttribute, 3, GL_FLOAT, false, 0, 0);
        
        // ----------------------- TEXTURE COORDS ----------------------------//
     
        int texcoordAttribute = openGLHelper.getShaderProgram().getAttributeLocation("texcoord");
        glEnableVertexAttribArray(texcoordAttribute);
        glBindBuffer(GL_ARRAY_BUFFER, getTexCoord());
        glVertexAttribPointer(texcoordAttribute, 2, GL_FLOAT, false, 0, 0);
        
        // ----------------------- ELEMENTS INDICES --------------------------//
        
        int ebo = getElementsBuffer();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        //glBufferData(GL_ELEMENT_ARRAY_BUFFER, ebo, GL_STATIC_DRAW);

        int uniTex = openGLHelper.getShaderProgram().getUniformLocation("texImage");
        openGLHelper.getShaderProgram().setUniform(uniTex, 0);
      
        Matrix4f model = Matrix4f.rotate(angulo, rx, ry, rz); 
        model = Matrix4f.scale(sx, sy, sz).multiply(model);
        model = Matrix4f.translate(x, y, z).multiply(model);
        glUniformMatrix4(openGLHelper.getShaderProgram().getUniformLocation("model"), false, model.getBuffer());

        texture.bind();
       
        glDrawElements(GL_TRIANGLES, 2*3, GL_UNSIGNED_INT, 0);    
		
	}

    public void setRotation(float nangle, float x, float y, float z) {
	
		rx = x;
		ry = y;
		rz = z;
		angulo = nangle;
		
	}

    public void setTexture(String textfile) {
		
		 texture =  Texture.loadTexture(textfile);
		
	}

    public void setPosition(float px, float py, float pz) {
		
		x = px;
		y = py;
		z = pz;
		
	}

    public void setScale(float nsx, float nsy, float nsz) {
		
		sx = nsx;
		sy = nsy;
		sz = nsz;
	}

          
}
