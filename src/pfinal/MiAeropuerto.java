/** 
 *
 * Clase principal de la práctica final de Gráficos y Visualización 3D
 * Grado ISAM, curso 2014-2015
 * Aeropuerto, 
 * @author Miguel Ángel Fernández Sánchez
 *
 **/

package pfinal;

//Imports de OpenGL
import Utils.FPCameraController;
import Utils.*;

//Imports de mis clases
import pfinal.Avion;
/*import pfinal.Pista;
import pfinal.Suelo;
import pfinal.Torre;
import pfinal.ATT;*/


public class MiAeropuerto implements Drawable {

    //Inicializo variables de OpenGL
    private ShaderProgram shaderProgram;
    private final OpenGLHelper openGLHelper = new OpenGLHelper("Aeropuerto - Mghfdez", new FPCameraController(-40, -5, -20));

    //Inicializo mis variables

    //Array donde irán metidos todos los objetos dibujables
    private Dibujable misDibujables[] = new Dibujable[10];

    //Objetos
    /*private Torre torreControl;
    private Torre miTerminal;
    private Suelo miSuelo;
    private Pista pistaPpal;*/
    private Avion miAvion1;
    private Avion miAvion2;

    public String texturaSuelo = "mars.jpg";
    public String texturaPista = "road.jpg";
    public String texturaCristal = "glass.jpg";
    public String airbus380 = "a380.obj";

    private float velocidadLenta = 0.5f;
    private float velocidadMedia = 0.7f;

    private final int elemDib = 6;
    private final int finDex = elemDib - 1;
    
    private void initObjects() {

        //Clase que crea el shader e instancia los objetos

        /*miSuelo = new Suelo();
        pistaPpal = new Pista();
        torreControl = new Torre();
        miTerminal = new Torre();*/
        miAvion1 = new Avion();
        miAvion2 = new Avion();

    }

    private void prepareBuffers() {
        shaderProgram = openGLHelper.getShaderProgram();
    }
    
    private void drawSomeModel() {

        // Clase que da a los objetos dibujables sus atributos pincipales
        // tales como: posición inicial, rotación, escala, textura, etc.
    	/*
        //=========== Suelo ===========//
        miSuelo.setTexture(texturaSuelo);
        miSuelo.setRotation(0.0f, 0.0f, 1f, 0);
    	miSuelo.setPosition(10,0,0);
    	miSuelo.setScale(50.0f, 1.0f, 50.0f);

    	//=========== Pista ===========//
    	pistaPpal.setTexture(texturaPista);
    	pistaPpal.setRotation(0.0f, 0.0f, 1f, 0);
    	pistaPpal.setPosition(10, 0.1f, 0.0f);
    	pistaPpal.setScale(50.0f, 1.0f, 6.0f);

    	//=========== Torre Control ===========//
        torreControl.setTexture(texturaCristal);
        torreControl.setRotation(0.0f, 0.0f, 1f, 0);
    	torreControl.setPosition(25, 0.0f, -35.0f);
    	torreControl.setScale(10.0f, 5.0f, 3.0f);

    	//=========== Terminal ===========//
        miTerminal.setTexture(texturaCristal);
        miTerminal.setRotation(0.0f, 0.0f, 1f, 0);
    	miTerminal.setPosition(25, 0.0f, -35.0f);
    	miTerminal.setScale(10.0f, 5.0f, 3.0f);
		*/
    	//=========== Avión 1 ===========//
        miAvion1.setOBJ(airbus380);
        miAvion1.setSpeed(velocidadLenta);
        miAvion1.setRotation(270.0f, 0.0f, 1f, 0);
		miAvion1.setPosition(-50, 1.0f, 0.0f, 0.5f);
		miAvion1.setScale(0.0018f, 0.0018f, 0.0018f);

    	//=========== Avión 2 ===========//
    	miAvion2.setOBJ(airbus380);
    	miAvion1.setSpeed(velocidadMedia);
    	miAvion2.setRotation(270f, 0.0f, 1f, 0);
		miAvion2.setPosition(-50, 8.0f, -14.0f, 0.7f);
		miAvion2.setScale(0.0015f, 0.0015f, 0.0015f);


		//=======   Array de dibujables ========//

		/*misDibujables[0] = miSuelo;
		misDibujables[1] = pistaPpal;
		misDibujables[2] = torreControl;
		misDibujables[3] = miTerminal;*/
		misDibujables[0] = miAvion1;
		misDibujables[1] = miAvion2;
		
    }

    @Override
    public void draw(){
        // Método para dibujar. 

        //Primero damos a los objetos sus atributos con:
        drawSomeModel();

        // Aplicamos polimorfismo dibujando todos los elementos del array de dibujables.


         for(int dex = 0; dex <= finDex; dex++) {
            misDibujables[dex].dibujar(openGLHelper);
         }
     }

    public void run() {
        // Método principal que ejecuta los submétodos principales
        
        openGLHelper.initGL("VS_Texture.vs", "FS_Texture.fs");
        initObjects();
        prepareBuffers();
        openGLHelper.run(this);
    }

    public static void main(String[] args) {
        new MiAeropuerto().run();
    }

}    

