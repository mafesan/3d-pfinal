package pfinal;

// Imports de OpenGL

import static org.lwjgl.opengl.GL15.GL_ARRAAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBundBuffer;
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

        texture = Texture.LoadTexture("mars.jpg");
        x = y = z = rx = ry = rz = 0.0f;
        sx = sy = sz = 1.0f;
    }

          
}
