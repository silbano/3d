package ThreeDee;
import java.awt.*;
import ThreeDee.shapes.Shape;

/**
 * See klass defineerib 3d graafika joonistamiseks vajalikud meetodid.
 * Klassi loogika baseerub Cores2 Softare Solutions'i JavaScript tutorialil, originaali
 * asukoht: http://www.cores2.com/3D_Tutorial/#view-projection.
 * JavaScript -> Java implementatsioon on autori (Silver Lumi) loodud.
 *
 * Osaliselt on Java lahendus erinev tutorialis toodud, näiteks on muudetud
 * kasutusel olnud "mullisortimisalgoritmi", asendades see efektiivsema versiooniga
 * (idee allikas: http://stackoverflow.com/questions/20183549/why-does-my-bubble-sort-sort-reversed-data-far-faster-than-random-data-or-data-w)
 */

public class Render {

    public Graphics g;
    private final GraphicsPanel panel;
    private final int height;
    private final int width;
    private int centerX;
    private int centerY;
    private int[] cameraPos;

    public Shape shape;

    /**
     * Objekti konstruktor. Muid konstruktoreid objektil pole.
     * Parameetriks on graafikapaneel (JPanel laiendus) GraphicsPanel.
     *
     * @param panel
     */
    public Render(GraphicsPanel panel){
        this.panel = panel;
        this.height = panel.getPreferredSize().height;
        this.width = panel.getPreferredSize().width;
    }

    /**
     * "Setter" meetod joonistatava objekti määramiseks.
     *
     * @param shape     joonistatav objekt
     */
    public void setShapeToRender(Shape shape) {
        this.shape = shape;
    }

    /**
     * Meetod mis käivitab objekti joonistamise.
     * Defineerib kaamera algpositsiooni.
     */
    public void renderScene() {

        this.centerX = this.width / 2;
        this.centerY = this.width / 2;

        this.cameraPos = new int[3];
        this.cameraPos[0] = 0;
        this.cameraPos[1] = 0;
        this.cameraPos[2] = -5;


        // Alusta objekti joonistamisega
        this.renderShape(this.shape);
    }

    /**
     * Meetod, mis joonistab etteantud koordinaatidesse etteantud värviga punkti.
     * Punkti joonistamiseks kasutatakse fillRect meetodit. Vaikimisi on punkti suuruseks
     * 2x2 pikslit.
     *
     * @param x     joonistatava punkti x-koordinaat. Integer
     * @param y     joonitatava punkti y-koordinaat. Integer
     * @param color joonistatava punkti värv. Objekt Color
     */
    void renderPoint(int x, int y, Color color) {
        this.g.setColor(color);
        this.g.fillRect(x,y, 2, 2);
    }

    /**
     * Meetod, mis joonistab etteantud koordinaatidesse värviga täidetud kolmnurga
     *
     * @param x1    esimese nurga x-koordinaat
     * @param y1    esimese nurga y-koordinaat
     * @param x2    teise nurga x-koordinaat
     * @param y2    teise nurga y-koordinaat
     * @param x3    kolmanda nurga x-koordinaat
     * @param y3    kolmanda nurga y-koordinaat
     * @param color kolmnurga värv (objekt Color)
     */
    void renderFillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
        int[] xPoints  = new int[3];
        int[] yPoints  = new int[3];

        xPoints[0] = x1;
        xPoints[1] = x2;
        xPoints[2] = x3;
        yPoints[0] = y1;
        yPoints[1] = y2;
        yPoints[2] = y3;

        this.g.setColor(color);
        this.g.fillPolygon(xPoints, yPoints, 3);
        this.g.setColor(Color.BLACK);
        this.g.drawPolygon(xPoints,yPoints,3);
    }

    /**
     * Meetod objekti koordinaatide arvutamiseks. Teostab järgnevat:
     * 1) Leiab 3d objekti 2d koordinaadid vastavalt "perspective projection" algoritmile
     * 2)
     *
     * @param shape
     */
    void renderShape(Shape shape){


        int[][] pointList = new int[shape.getCorners().length][2]; // massiiv punktikoordinaatide hoidmiseks
        int[] depthList = new int[shape.getCorners().length]; // massiiv punktikauguste koordinaatide hoidmiseks

        // kõigepealt joonista nurkade punktid
        for(int i = 0; i < shape.getCorners().length;i++)
        {
            // ajutine massiiv andmete hoidmiseks
            double[] workingVertex = {shape.getCorners()[i][0],shape.getCorners()[i][1],shape.getCorners()[i][2]};

            // keerame vertexi't vastavalt "perspective projection" algoritmile
            double temp = workingVertex[2];
            workingVertex[2] = -workingVertex[0] * Math.sin(this.panel.cameraRotation[1]) - workingVertex[2] * Math.cos(this.panel.cameraRotation[1]);
            workingVertex[0] = -workingVertex[0] * Math.cos(this.panel.cameraRotation[1]) + temp * Math.sin(this.panel.cameraRotation[1]);

            temp = workingVertex[2];
            workingVertex[2] = -workingVertex[1] * Math.sin(this.panel.cameraRotation[0]) + workingVertex[2]*Math.cos(this.panel.cameraRotation[0]);
            workingVertex[1] = workingVertex[1] * Math.cos(this.panel.cameraRotation[0]) + temp*Math.sin(this.panel.cameraRotation[0]);

            temp = workingVertex[0];
            workingVertex[0] = (workingVertex[0] * Math.cos(this.panel.cameraRotation[2]) - workingVertex[1]*Math.sin(this.panel.cameraRotation[2]));
            workingVertex[1] = (workingVertex[1] * Math.cos(this.panel.cameraRotation[2]) + temp*Math.sin(this.panel.cameraRotation[2]));

            workingVertex[0] -= cameraPos[0];
            workingVertex[1] -= cameraPos[1];
            workingVertex[2] -= cameraPos[2];

            // konverteeri kolmemõõtmelised koordinaadid kahemõõtmelisele ekraanile
            int screenX = (int)Math.round((this.panel.ratioConstant * workingVertex[0]) / workingVertex[2]);
            int screenY = (int)Math.round((this.panel.ratioConstant * workingVertex[1]) / workingVertex[2]);

            pointList[i][0] = centerX + screenX;
            pointList[i][1] = centerY + screenY;

            // lisame iga punkti "sügavuse" (z-koordinaad) punktikauguse massiivi
            depthList[i] = (int)Math.round(workingVertex[2]);

            // vastavalt leitud projekteeritud koordinaatidele, joonista punkt
            this.renderPoint(screenX + centerX, screenY + centerY, new Color(0, 0, 0));
        }

        // painter'i algoritm
        // kasutatakse selleks, et joonistada vaid kaamerale kõige lähemal olevad pinnad.
        // tegemist pole täiusliku meetodiga (teatud juhtudel esineb defekte), kuid antud demo jaoks on
        // see implementatsioon piisav
        double[] averageFaceDepth = new double[shape.getFaces().length];
        for(int i = 0; i < shape.getFaces().length; i++) {
            averageFaceDepth[i] = depthList[shape.getFaces()[i][0]];
            averageFaceDepth[i] += depthList[shape.getFaces()[i][1]];
            averageFaceDepth[i] += depthList[shape.getFaces()[i][2]];
            averageFaceDepth[i] = averageFaceDepth[i] / 3;
        }

        // sordi kõik pinnad keskmise pinna kauguse järgi.
        // kasutame "mullisortimisalgoritmi"
        // kuna demos toodud sortimisalgoritm on liiga aeglane, siis on implementeeritud alternatiivne sortimisalgoritm
        // allikas: http://stackoverflow.com/questions/20183549/why-does-my-bubble-sort-sort-reversed-data-far-faster-than-random-data-or-data-w

        int newChecks;
        int checks = averageFaceDepth.length - 1;
        do {
           newChecks =0;
            for(int i = 0; i < checks; i++)
            {
                if (averageFaceDepth[i] > averageFaceDepth[i + 1])
                {
                    double temp = averageFaceDepth[i];
                    averageFaceDepth[i] = averageFaceDepth[i + 1];
                    averageFaceDepth[i + 1] = temp;

                    int[] temp2 = shape.getFaces()[i];
                    shape.setFaces(i, shape.getFaces()[i + 1]);
                    shape.setFaces(i + 1, temp2);
                    newChecks = i;
                }
            }
            checks = newChecks;
        } while (checks > 0);


        // keera massiiv tagurpidi
        int len = shape.getFaces().length / 2;
        for(int i = 0; i < len; i++)
        {
            int[] temp = shape.getFaces()[i];
            shape.setFaces(i,shape.getFaces()[shape.getFaces().length - i -1]);
            shape.setFaces(shape.getFaces().length - i - 1,temp);
        }

        // punktid on joonistatud. enne, kui alustame kolmnurkade joonistamist, määrame neile värvi
        Color color = new Color(250, 151, 0);

        // joonistame kolmnurgad
        // mõõdame ka joonistamiseks kuluvat aega
        double startTime = System.nanoTime();
        for(int i = 0; i < shape.getFaces().length ; i++)
        {
            int[] pointA = pointList[shape.getFaces()[i][0]];
            int[] pointB = pointList[shape.getFaces()[i][1]];
            int[] pointC = pointList[shape.getFaces()[i][2]];

            this.renderFillTriangle(pointA[0], pointA[1], pointB[0], pointB[1], pointC[0], pointC[1],
                    color);
        }
        double durationTime = System.nanoTime() - startTime;

        // kuva lisainformatsioon joonistatava objekti kohta
        g.setColor(new Color(0,0,0));
        String text1 = "Joonistatavate pindade arv: " + shape.getFaces().length;
        String text2 = "Pindade joonistamiseks kulunud aeg (ms): " + Math.round(durationTime / 100000);

        g.drawString(text1,6,34);
        g.drawString(text2,6,54);
    }

    /**
     * Meetod ilusa ruudulise tausta joonistamiseks.
     */
    public void renderBackground(){
        // ruudu suurus
        int squareSize = 16;

        // default värv. kui kõik läheb hästi, siis kasutaja seda ei näe
        this.g.setColor(new Color(192, 220, 218));
        this.g.fillRect(0,0,this.width,this.height);
        for(int y = 0; y < Math.floor((this.height + squareSize)/squareSize); y++)
        {
            // vaheldumisi joonista erineva värviga kastike
            for(int x = 0; x < Math.floor((this.width + squareSize) / squareSize); x++)
            {
                Color targetColor = new Color(175,175,175); // värv 1
                if(x % 2 != y % 2)
                    targetColor = new Color(235,235,235); // värv 2

                this.g.setColor(targetColor);
                this.g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }
        }
    }
}
