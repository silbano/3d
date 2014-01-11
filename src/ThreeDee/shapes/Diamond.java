package ThreeDee.shapes;

/**
 * 3d objekt. Sisaldab informatsiooni objekti joonistamise kohta.
 */
public class Diamond extends AbstractShape {

    /**
     * Konstruktor
     */
    public Diamond() {
        String fileName = "teemant";
        loadData(fileName);
    }

    /**
     * Meetod objekti nime tagastamiseks stringina.
     * Kasutatakse ControlPaneli rippmenüüs objekti nimena.
     *
     * @return
     */
    public String toString() {
        return "Diamond";
    }
}
