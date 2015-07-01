/** 
 *
 * Clase abstracta Dibujable de la práctica final de Gráficos y Visualización 3D
 * Grado ISAM, curso 2014-2015
 * Aeropuerto, 
 * @author Miguel Ángel Fernández Sánchez
 *
 **/
package pfinal;

import Utils.OpenGLHelper;

public abstract class Dibujable {
	
    public abstract void dibujar(OpenGLHelper openGLHelper);
    public abstract void prepararBuffers(OpenGLHelper openGLHelper);
}