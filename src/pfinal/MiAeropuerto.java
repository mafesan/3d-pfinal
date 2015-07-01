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
import Utils.*;

//Imports de mis clases
import pfinal.Avion;
import pfinal.Pista;
import pfinal.Suelo;
import pfinal.Terminal;
import pfinal.Torre;


public class MiAeropuerto implements Drawable {

    //Inicializo variables de OpenGL
    private final OpenGLHelper openGLHelper = new OpenGLHelper("Aeropuerto - Miguel Angel Fernandez", new FPCameraController(-40, -5, -20));

    //Inicializo mis variables

    //Array donde irán metidos todos los objetos dibujables
    private Dibujable misDibujables[] = new Dibujable[11];

    //Objetos
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

    public String texturaSuelo = "mars.jpg";
    public String texturaPista = "road.jpg";
    public String texturaPista2 = "road2.jpg";
    public String texturaCristal = "glass.jpg";
    public String texturaCristal2 = "glass2.jpg";
    public String texturaMetal = "blackmetal.jpg";
    public String airbus380 = "a380.obj";

    private float velocidadLenta = 0.15f;
    private float velocidadMedia = 0.6f;
    
    private final int elemDib = 11;
    private final int finDex = elemDib - 1;
    
    private void initObjects() {
    
        //Clase que instancia los objetos

    	miSuelo = new Suelo(); 
        pistaPpal = new Pista();
        pistaSec = new Pista();
        torreControl = new Torre();
        supTorre = new Torre();
        miTerminal = new Terminal();
        miAvion1 = new Avion();
        miAvion2 = new Avion();
        miAvion3 = new Avion();
        miAvion4 = new Avion();
        pistaAux = new Pista();
    }
    
    private void drawSomeModel() {
    	
        // Clase que da a los objetos dibujables sus atributos pincipales
        // tales como: posición inicial, rotación, escala, textura, etc.
    	
        //=========== Suelo ===========//
        miSuelo.setTexture(texturaSuelo);
        miSuelo.setRotation(0.0f, 0.0f, 1f, 0);
    	miSuelo.setPosition(10,0,0);
    	miSuelo.setScale(50.0f, 1.0f, 50.0f);
    	
    	//=========== Pista principal ===========//
    	pistaPpal.setTexture(texturaPista);
    	pistaPpal.setRotation(0.0f, 0.0f, 1f, 0);
    	pistaPpal.setPosition(10, 0.1f, 0.0f);
    	pistaPpal.setScale(50.0f, 1.0f, 6.0f);
    	
    	//=========== Pista secundaria ===========//
    	pistaSec.setTexture(texturaPista);
    	pistaSec.setRotation(90.0f, 0.0f, 1f, 0.0f);
    	pistaSec.setPosition(10.0f, 0.1f, 21.0f);
    	pistaSec.setScale(5.0f, 1.0f, 15.0f);

    	//=========== Pista auxiliar ===========//
    	pistaAux.setTexture(texturaPista2);
    	pistaAux.setRotation(90.0f, 0.0f, 1f, 0.0f);
    	pistaAux.setPosition(-8.0f, 0.1f, -21.0f);
    	pistaAux.setScale(5.0f, 1.0f, 15.0f);
    	
    	//=========== Torre Control ===========//
    	  // Parte troncal
        torreControl.setTexture(texturaMetal);
        torreControl.setRotation(0.0f, 0.0f, 1f, 0);
    	torreControl.setPosition(8, 0.0f, -35.0f);
    	torreControl.setScale(1.0f, 10.0f, 1.0f);
    	  // Parte Superior
    	supTorre.setTexture(texturaCristal2);
    	supTorre.setRotation(0.0f, 0.0f, 1f, 0.0f, true);
    	supTorre.setPosition(8, 10.0f, -35.0f);
    	supTorre.setScale(2.0f, 1.0f, 2.0f);

    	//=========== Terminal ===========//
        miTerminal.setTexture(texturaCristal);
        miTerminal.setRotation(0.0f, 0.0f, 1f, 0);
    	miTerminal.setPosition(25, 0.0f, -35.0f);
    	miTerminal.setScale(10.0f, 5.0f, 7.0f);
		
    	//=========== Avión 1 ===========//

        miAvion1.setRotation(270.0f, 0.0f, 1f, 0);
		miAvion1.setPosition(-50, 1.0f, 0.0f, velocidadLenta);
		miAvion1.setScale(0.0018f, 0.0018f, 0.0018f);

    	//=========== Avión 2 ===========//

    	miAvion2.setRotation(270f, 0.0f, 1f, 0);
		miAvion2.setPosition(-50, 8.0f, -14.0f, velocidadMedia);
		miAvion2.setScale(0.0015f, 0.0015f, 0.0015f);
		
		//=========== Avión 3 ===========//

        miAvion3.setRotation(180f, 0.0f, 1f, 0);
		miAvion3.setPosition(47, 2.5f, -30.0f, 0.0f);
		miAvion3.setScale(0.003f, 0.003f, 0.003f);

    	//=========== Avión 4 ===========//

    	miAvion4.setRotation(0f, 0.0f, 1f, 0);
		miAvion4.setPosition(-15, 2.5f, 25.0f, 0.0f);
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

    }
    
    @Override
    public void draw(){
        // Método para dibujar. 

        //Primero damos a los objetos sus atributos con:
        drawSomeModel();
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
        openGLHelper.run(this);
    }

    public static void main(String[] args) {
        new MiAeropuerto().run();
    }

}    

