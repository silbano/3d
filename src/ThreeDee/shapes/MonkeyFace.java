package ThreeDee.shapes;

/**
 * 3d objekt. Sisaldab informatsiooni objekti joonistamise kohta.
 */
public class MonkeyFace extends AbstractShape  {

    /**
     * Konstruktor
     */
    public MonkeyFace() {
        String fileName = "monkey";
        loadData(fileName);
    }

    /**
     * Meetod objekti nime tagastamiseks stringina.
     * Kasutatakse ControlPaneli rippmenüüs objekti nimena.
     *
     * @return
     */
    public String toString() {
        return "Monkey Face";
    }
}
