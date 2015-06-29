package pfinal;

import Utils.OpenGLHelper;

/**
 *
 * @author MiguelAngel
 */
public abstract class Dibujable {
    int x, y, z;
    
    public abstract void dibujar(OpenGLHelper openGLHelper);
}
