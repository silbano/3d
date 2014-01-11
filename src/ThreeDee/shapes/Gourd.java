package ThreeDee.shapes;

/**
 * 3d objekt. Sisaldab informatsiooni objekti joonistamise kohta.
 */
public class Gourd extends AbstractShape {

    /**
     * Konstruktor
     */
    public Gourd() {
        String fileName = "gourd";
        loadData(fileName);
    }

    /**
     * Meetod objekti nime tagastamiseks stringina.
     * Kasutatakse ControlPaneli rippmenüüs objekti nimena.
     *
     * @return
     */
    public String toString() {
        return "Gourd";
    }
}
