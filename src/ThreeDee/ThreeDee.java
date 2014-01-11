package ThreeDee;
import javax.swing.*;

/**
 * Programm, mis implementeerib kolmemõõtmelise graafika kuvamise lahenduse,
 * kasutades java standardteeke (awt, swing), mitte aga 3d teeke.
 * Programmi koostamise eesmärgiks on selgitada 3d graafikamootorite
 * tööpõhimõtteid ning kontseptsioone.
 *
 * @author Silver Lumi
 * @since 0.1
 */
class ThreeDee   {
    /**
     * Meetod, mis initsialiseerib graafilised komponendid
     * ning teeb tööakna nähtavaks. Tööaken koosneb kahest paneelist (JPanel):
     * ühte kasutatakse graafika kuvamiseks, teist aga nuppude jms hoidmiseks.
     */
    private static void createGUI() {
        // Loo põhiaken
        JFrame frame = new JFrame("3D ENGINE - SILVER LUMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Loo kastike, kuhu sisse hakkame joonistama.
        GraphicsPanel panel = new GraphicsPanel();

        // Loo kontrollpaneel koos nuppudega
        ControlPanel control = new ControlPanel(panel);

        container.add(panel);
        container.add(control);
        frame.add(container);

        // Tee põhiaken nähtavaks
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}

