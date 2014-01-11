package ThreeDee.shapes;


/**
 * 3d objekt. Sisaldab informatsiooni objekti joonistamise kohta.
 */
public class Dude extends AbstractShape {

    /**
     * Konstruktor
     */
    public Dude() {
        String fileName = "dude";
        loadData(fileName);
    }


    /**
     * Meetod objekti nime tagastamiseks stringina.
     * Kasutatakse ControlPaneli rippmenüüs objekti nimena.
     *
     * @return
     */
    public String toString() {
        return "Dude";
    }
}
