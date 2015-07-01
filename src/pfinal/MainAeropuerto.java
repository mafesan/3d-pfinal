/** 
 *
 * Clase principal de la práctica final de Gráficos y Visualización 3D
 * Grado ISAM, curso 2014-2015
 * Aeropuerto, 
 * @author Miguel Ángel Fernández Sánchez
 *
 **/

package pfinal;

//Imports de mis clases
import pfinal.MainAeropuerto;
import pfinal.Avion;
import pfinal.Pista;
import pfinal.Suelo;
import pfinal.Torre;
import pfinal.Terminal;
import pfinal.Sol2;

//Imports de OpenGL
import Utils.*;

public class MainAeropuerto implements Drawable {
	
	//Inicializo variables de OpenGL
	private ShaderProgram shaderProgram;
    private final OpenGLHelper openGLHelper = new OpenGLHelper("pfinal", new FPCameraController(-40, -5, -20));
    
  //Inicializo mis variables

    //Array donde irán metidos todos los objetos dibujables
    private Dibujable misDibujables[] = new Dibujable[12];
    //Objetos que voy a dibujar
    private Terminal miTerminal;
    private Torre supTorre;
    private Torre torreControl;
    private Pista pistaPpal;
    private Pista pistaSec;
    private Avion miAvion1;
    private Avion miAvion2;
    private Avion miAvion3;
    private Avion miAvion4;
    private Suelo miSuelo;
    private Pista pistaAux;
    private Sol2 sol2;
    
    // Variables de control
    private float velocidadLenta = 0.15f;
    private float velocidadMedia = 0.4f;
    private boolean torreMueve = true;
    private final int elemDib = 12;
    private final int finDex = elemDib - 1;

    private void initObjects() {
    	
      //Clase que instancia los objetos
    	
    	
		shaderProgram = openGLHelper.getShaderProgram();  
		miSuelo = new Suelo();
		miSuelo.setTexture("mars.jpg");
		  
		pistaPpal = new Pista();
		pistaPpal.setTexture("road.jpg");
		 
		pistaSec = new Pista();
		pistaSec.setTexture("road2.jpg");
		  
		pistaAux = new Pista();
		pistaAux.setTexture("road2.jpg");
		  
		miTerminal = new Terminal();
		miTerminal.setTexture("glass.jpg");
		  
		supTorre = new Torre();
		supTorre.setTexture("glass2.jpg");
		  
		torreControl = new Torre();
		torreControl.setTexture("blackmetal.jpg");
		  
		miAvion1 = new Avion();
		miAvion2 = new Avion();
		miAvion3 = new Avion();
		miAvion4 = new Avion();
		sol2 = new Sol2();
	}

    private void prepareBuffers() {
        shaderProgram = openGLHelper.getShaderProgram();              
    }

    @Override
    public void draw()
    {
    	// Método que llama al procedimiento que dibuja. 
        drawSomeModel();
    }
    
    float angle;

 
    private void drawSomeModel() {
    	// Clase que da a los objetos dibujables sus atributos pincipales
        // tales como: posición inicial, rotación, escala, textura, etc.
    	
    	shaderProgram = openGLHelper.getShaderProgram();
    	
    	//=========== Suelo ===========//
    	miSuelo.setRotation(0.0f, 0.0f, 1f, 0);
    	miSuelo.setPosition(10,0,0);
    	miSuelo.setScale(50.0f, 1.0f, 50.0f);

    	//=========== Pista principal ===========//
    	pistaPpal.setRotation(0.0f, 0.0f, 1f, 0);
    	pistaPpal.setPosition(10, 0.1f, 0.0f);
    	pistaPpal.setScale(50.0f, 1.0f, 6.0f);

    	//=========== Pista secundaria ===========//
    	pistaSec.setRotation(90.0f, 0.0f, 1f, 0.0f);
    	pistaSec.setPosition(10.0f, 0.1f, 21.0f);
    	pistaSec.setScale(5.0f, 1.0f, 15.0f);

    	//=========== Pista auxiliar ===========//
    	pistaAux.setRotation(90.0f, 0.0f, 1f, 0.0f);
    	pistaAux.setPosition(-8.0f, 0.1f, -21.0f);
    	pistaAux.setScale(5.0f, 1.0f, 15.0f);
    	
    	//=========== Terminal ===========//
    	miTerminal.setRotation(0.0f, 0.0f, 1f, 0);
    	miTerminal.setPosition(25, 0.0f, -35.0f);
    	miTerminal.setScale(10.0f, 7.0f, 7.0f);
    	
    	//=========== Torre Control ===========//
  	      // Parte Superior
    	supTorre.setRotation(0.0f, 0.0f, 1f, 0.0f, torreMueve);
    	supTorre.setPosition(8, 10.0f, -35.0f);
    	supTorre.setScale(2.0f, 1.0f, 2.0f);
    	  // Parte troncal
    	torreControl.setRotation(0.0f, 0.0f, 1f, 0);
    	torreControl.setPosition(8, 0.0f, -35.0f);
    	torreControl.setScale(1.0f, 10.0f, 1.0f);

    	//=========== Avión 1 (Pista) ===========//
		miAvion1.setRotation(270.0f, 0.0f, 1f, 0);
		miAvion1.setPosition(-50, 1.0f, 0.0f, velocidadLenta);
		miAvion1.setScale(0.0018f, 0.0018f, 0.0018f);
		
		//=========== Avión 2 (Volando)===========//
		miAvion2.setRotation(270f, 0.0f, 1f, 0);
		miAvion2.setPosition(-50, 8.0f, -14.0f, velocidadMedia);
		miAvion2.setScale(0.0015f, 0.0015f, 0.0015f);

		//=========== Avión 3 (Aparcado, izda Terminal ===========//
        miAvion3.setRotation(180f, 0.0f, 1f, 0);
		miAvion3.setPosition(47, 3f, -30.0f, 0.0f);
		miAvion3.setScale(0.003f, 0.003f, 0.003f);

    	//=========== Avión 4 (Aparcado)===========//
    	miAvion4.setRotation(0f, 0.0f, 1f, 0);
		miAvion4.setPosition(-15, 3f, 25.0f, 0.0f);
		miAvion4.setScale(0.004f, 0.004f, 0.004f);
		
		//======= Array de dibujables ========//
		misDibujables[0] = miAvion1;
		misDibujables[1] = miAvion2;
		misDibujables[2] = miSuelo;
		misDibujables[3] = pistaPpal;
		misDibujables[4] = miTerminal;
		misDibujables[5] = torreControl;
		misDibujables[6] = supTorre;
		misDibujables[7] = pistaSec;
		misDibujables[8] = miAvion3;
		misDibujables[9] = miAvion4;
		misDibujables[10] = pistaAux;
		misDibujables[11] = sol2;
		
		// Aplicamos polimorfismo dibujando todos los elementos del array de dibujables.
		for(int dex = 0; dex <= finDex; dex++) {
        	misDibujables[dex].prepararBuffers(openGLHelper);
            misDibujables[dex].dibujar(openGLHelper);
        }
    }

    public void run() {
        // Método principal que ejecuta los submétodos principales
        System.out.println("Inciando Aeropuerto...");
        openGLHelper.initGL("VS_Texture.vs", "FS_Texture.fs");
        initObjects();
        prepareBuffers();
        openGLHelper.run(this);
    }
    
    public static void main(String[] args) {
        new MainAeropuerto().run();
    }

}
