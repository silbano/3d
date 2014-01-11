package ThreeDee;

import ThreeDee.shapes.Shape;
import ThreeDee.shapes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

/**
 * JPanel tüüpi paneel, mis sisaldab kasutatavaid interaktiivseid objekte (punktid, slider)
 * Kontrollpaneel võimaldab kuvatavaid objekte keerata ning zoomida.
 */

class ControlPanel extends JPanel {

    private final GraphicsPanel panel;

    /**
     * Objekti konstruktor. Muid konstruktoreid objektil pole.
     *
     * @param panel graafikapaaneel, milles sisalduvaid objekte hakkame mõjutama
     */
    public ControlPanel(GraphicsPanel panel) {

        this.panel = panel;

        // lisa nupp, mis keerab objekti X-Y teljel
        JButton keeraXY = new JButton("Keera XY");
        keeraXY.setSize(100,100);

        // lisa nupp, mis keerab objekti Y-Z teljel
        JButton keeraYZ = new JButton("Keera YZ");
        keeraYZ.setSize(100,100);

        // lisa nupp, mis keerab objekti X-Z teljel
        JButton keeraXZ = new JButton("Keera XZ");
        keeraXZ.setSize(100,100);

        // defineerime ComboBox rippmenüü ja
        // lisame kõik võimalikud joonistatavad objektid
        final JComboBox valiKujund = new JComboBox();
        valiKujund.addItem("-");
        valiKujund.addItem(new Dude());
        valiKujund.addItem(new TriangleCube());
        valiKujund.addItem(new MonkeyFace());
        valiKujund.addItem(new Diamond());
        valiKujund.addItem(new Gourd());

        // defineerime slaideri kaamera zoomimiseks
        final JSlider kaameraZoom = new JSlider(JSlider.HORIZONTAL, 0, 255, 100);
        kaameraZoom.setMajorTickSpacing(40);
        kaameraZoom.setMinorTickSpacing(10);
        kaameraZoom.setMaximum(800);
        kaameraZoom.setPaintTicks(true);

        // event listener kaamera zoomisliderile
        kaameraZoom.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                kaameraZoom(kaameraZoom.getValue());
            }
        });

        // event listener joonistatava objekti valimiseks
        valiKujund.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object shape = valiKujund.getSelectedItem();

                // kontrolli, kas tegemist on SHAPE tüüpi objektiga
                // kui jah, siis joonista!
                if(shape instanceof Shape) {
                    render((Shape)shape);
                }
            }
        });

        // event listener objekti keeramiseks
        keeraXY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { keeraXY(); }
        });

        // event listener objekti keeramiseks
        keeraYZ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { keeraYZ();}
        });

        // event listener objekti keeramiseks
        keeraXZ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { keeraXZ(); }
        });

        // lisa kõik defineeritud elemendid paneelile
        this.add(keeraXY, BorderLayout.LINE_START);
        this.add(keeraYZ, BorderLayout.LINE_START);
        this.add(keeraXZ, BorderLayout.LINE_START);
        this.add(valiKujund, BorderLayout.LINE_START);
        this.add(kaameraZoom, BorderLayout.LINE_START);

    }

    // joonista valitud objekt
    void render(Shape shape) {
        this.panel.render.setShapeToRender(shape);
        this.panel.repaint();
    }


    // keera objekti
    void keeraXY() {
        this.panel.cameraRotation[0] += 0.1;
        this.panel.repaint();
    }

    // keera objekti
    void keeraYZ() {
        this.panel.cameraRotation[1] += 0.1;
        this.panel.repaint();
    }

    // keera objekti
    void keeraXZ() {
        this.panel.cameraRotation[2] += 0.1;
        this.panel.repaint();
    }

    // zoomi kaamerat
    void kaameraZoom(int i) {
        this.panel.ratioConstant = i;
        this.panel.repaint();
    }


}
