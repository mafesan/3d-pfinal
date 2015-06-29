
package pfinal;

// Imports de OpenGL
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.gl.BindBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.opengl.GL11;
import Utils.Matrix4f;
import Utils.OpenGLHelper;

// Mis imports
import pfinal.ModelLoader;

public class Avion extends Dibujable {

    private ModelLoader fmodel;

    private float count, count2, count3 = 0.0f;
    public float posX, posY, posZ;

    private float velocidad;

    int vbo_v, vbo_n, vbo_c;
    float x, y, z;
    float sx, sy, sz;
    float rx, ry, rz, angulo;

    private int uniModel;


    public NewAvion() {

        x = y = z = rx = ry = rz = 0.0f;
        sx = sy = sz = 1.0f;
    }

    public void setOBJ(String objFile) {
        fmodel = new ModelLoader(objFile);
    }

    public void setSpeed(float velocidad) {
        speed = velocidad;
    }

    public void setRotation(float mi_angulo, float x, float y, float z) {

        rx = x;
        ry = y;
        rz = z;
        angulo = mi_angulo;
    }

    public void setPosition(float px, float py, float pz) {

        x = px;
        posX = x;

        y = py;
        posY = y;

        z = pz;
        posZ = z;
    }

    public void setScale(float nsx, float nsy, float nsz) {

        sx = nsx;
        sy = nsy;
        sz = nsz;
    }

    public int getVertexPositions() {
        return vbo_v;
    }

    public int getVertexColor() {
        return 0;
    }

    public int getElementsBuffer() {
        return vbo_e;
    }

    public void dibujar(OpenGLHelper openGLHelper) {

        count += this.speed

        int posAttrib = openGLHelper.getShaderProgram().getAttributeLocation("glEnableVertexAttribArray(posAttrib);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexPositions());

        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);

        int colAttrib = openGLHelper.getShaderProgram().getUniformLocation("color");
        glEnableVertexAttribArray(colAttrib);
        glVertexAttribPointer(colAttrib, 3, GL_FLOAT, false, 3 * GL_FLOAT, 0);

        int vertexColorAttribute = openGLHelper.getShaderProgram().getAttributeLocation("aVertexColor");
        glEnableVertexAttribArray(vertexColorAttribute);
        glBindBuffer(GL_ARRAY_BUFFER, getVertexColor());
        glVertexAttribPointer(vertexColorAttribute, 3, GL_FLOAT, false, 0, 0);

        posX = this.x + count;

        if (posX > 400) {
            posX = this.x;
            count = this.velocidad;
            posY = this.y;
            count2 = 0.0f;

        }

        if (posX > 37.5) {
            count2 += 0.015f;
            if (count2 > 5){
                count2 += 0.03f;
            }
            posY = this.y + count2;
        }else{
            posY = this.y;
        }


        Matrix4f model = Matrix4f.rotate(angulo, rx, ry, rz);
        model = Matrix4f.scale(sx, sy, sz).multiply(model);
        model = Matrix4f.translate(posX, posY, posZ).multiply(model);
        glUniformMatrix4(openGLHelper.getShaderProgram().getUniformLocation("model"), false, model.getBuffer());

        glDrawArrays(GL11.GL_TRIANGLES, 0, fmodel.getNumVertices()*3);
        
    }
}

