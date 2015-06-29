
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
    
    
    
}
