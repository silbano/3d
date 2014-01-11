package ThreeDee;

import ThreeDee.shapes.Shape;

import javax.swing.*;
import java.awt.*;

/**
 * JPAnel tüüpi paneel, kuhu joonistatakse kuvatavad 3d objektid.
 * Objekti intisaliseerimisel defineeritakse paneeli suurus ning vaikimisi
 * kaamera zoomväärtus.
 *
 */
class GraphicsPanel extends JPanel {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    public final double[] cameraRotation = new double[3];
    public int ratioConstant = 300;
    public final Render render;

    /**
     * Objekti konstruktor. Muid konstruktoreid objektil pole.
     * Parameetreid konstruktoril ei ole.
     * Antud konstruktori ülesandeks on paneeli suuruse defineerimine
     * ja Render() objekti initsialiseerimine.
     */
    protected GraphicsPanel() {
        this.setPreferredSize( new Dimension( WIDTH, HEIGHT ) );
        render = new Render(this);
    }

    /**
     * JPanel'i vaikimisi joonistamismeetod, mille antud juhul üle kirjutame.
     * Meetodit kutsutakse alati, kui on vaja paneeli uuesti joonistada, ehk siis näiteks
     * siis, kui kasutatakse paneeli repaint() meetodit.
     *
     * Meetodi implementatsioon:
     * 1) joonistab paneeli tausta ruudustiku
     * 2) Mõõdab kaadri joonistamiseks kulunud aega ning kuvab selle ekraanil
     * 3) Juhul, kui render() objektil on olemas objetk shape interface'ga Shape(),
     * siis kutsub render() objekti meetodit renderScene();
     *
     * @param g
     */

    @Override
    protected void paintComponent(Graphics g) {
        // asigneeri graafikaobjekt g render-objektile
        render.g = g;

        // puhasta ekraan
        ClearScreen(g);

        // joonista taustaruudustik
        render.renderBackground();

        // mõõda joonistamiseks kuluvat aega: defineeri algmoment
        long startTime = System.nanoTime();

        // kui render.shape on Shape tüüpi, siis joonista objekt
        if(render.shape instanceof Shape){
            render.renderScene();
        }
        // mõõda kulunud aega algmomendist lõppmomendini
        long estimatedTime = System.nanoTime() - startTime;

        // määra teksti värv (must)
        g.setColor(new Color(0,0,0));
        String text = "Kaadri joonistamiseks kulunud aeg (ms): " + estimatedTime / 1000000;

        // kuva tekst koos kulunud ajaga
        g.drawString(text,6,14);
    }

    /**
     * Meetod, mis tühjendab ekraani
     */
    void ClearScreen(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
