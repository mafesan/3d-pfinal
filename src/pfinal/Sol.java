/** 
 *
 * Clase Sol de la práctica final de Gráficos y Visualización 3D
 * Grado ISAM, curso 2014-2015
 * Aeropuerto, 
 * @author Miguel Ángel Fernández Sánchez
 *
 **/
package pfinal;

import Utils.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;


public class Sol extends Dibujable {

    private int uniModel;
    private int uNMatrixAttribute;

    private final SphereModel sphereModel = new SphereModel(20);

    public void prepararBuffers(OpenGLHelper openGLHelper) {

	        // --------------------- VERTICES POSITIONS --------------------------//
	        int vbo_v = sphereModel.createVerticesBuffer();

	        int posAttrib = openGLHelper.getShaderProgram().getAttributeLocation("aVertexPosition");
	        glEnableVertexAttribArray(posAttrib);
	        glBindBuffer(GL_ARRAY_BUFFER, vbo_v);
	        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);

	        // ----------------------- NORMALS DATA -------------------------------//
	        int vbo_n = sphereModel.createVerticesBuffer();

	        int vertexNormalAttribute = openGLHelper.getShaderProgram().getAttributeLocation("aVertexNormal");
	        glEnableVertexAttribArray(vertexNormalAttribute);
	        glBindBuffer(GL_ARRAY_BUFFER, vbo_n);
	        glVertexAttribPointer(vertexNormalAttribute, 3, GL_FLOAT, false, 0, 0);


	        
	        // ----------------------- ELEMENTS INDICES --------------------------//
	        int ebo = sphereModel.createIndicesBuffer();

	        uniModel = openGLHelper.getShaderProgram().getUniformLocation("model");

	        uNMatrixAttribute = openGLHelper.getShaderProgram().getUniformLocation("uNMatrix");
	    }

	    @Override
	    public void dibujar(OpenGLHelper openGLHelper) {
	        /* Render a model */
	        drawSomeModel(openGLHelper);
	    }

	    private void drawSomeModel(OpenGLHelper openGLHelper) {
	        /* Render X, Y, Z */
	        Matrix4f model = Matrix4f.translate(12, 20, -35f);
	        glUniformMatrix4(uniModel, false, model.getBuffer());

	        Matrix3f normalMatrix = model.multiply(openGLHelper.getViewMatrix()).toMatrix3f().invert();
	        normalMatrix.transpose();
	        glUniformMatrix3(uNMatrixAttribute, false, normalMatrix.getBuffer());

	        glDrawElements(GL_TRIANGLES, sphereModel.getIndicesLength(), GL_UNSIGNED_INT, 0);
	    }

}
