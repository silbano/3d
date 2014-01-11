package ThreeDee.shapes;

/**
 * 3d objekt. Sisaldab informatsiooni objekti joonistamise kohta.
 */
public class TriangleCube extends AbstractShape {

    /**
     * Objekti konstruktor
     */
    public TriangleCube() {
        loadData();
    }

    /**
     * Meetod objekti nime tagastamiseks stringina.
     * Kasutatakse ControlPaneli rippmenüüs objekti nimena.
     *
     * @return
     */
    public String toString() {
        return "Triangle Cube";
    }

    /**
     * Meetod, mis kirjutab üle abstraktses klassis kirjeldatud loadData() meetodi,
     * kuna antud juhul on andmete laadimise implementatsioon teistsugune.
     */
    public void loadData() {

        // defineerime massiivi suuruse nurkadem koordinaatide hoidmiseks
        this.corners = new double[8][3];

        this.corners[0][0] = -1;
        this.corners[0][1] = -1;
        this.corners[0][2] = 1;

        this.corners[1][0] = -1;
        this.corners[1][1] = 1;
        this.corners[1][2] = 1;

        this.corners[2][0] = 1;
        this.corners[2][1] = 1;
        this.corners[2][2] = 1;

        this.corners[3][0] = 1;
        this.corners[3][1] = -1;
        this.corners[3][2] = 1;

        this.corners[4][0] = -1;
        this.corners[4][1] = -1;
        this.corners[4][2] = -1;

        this.corners[5][0] = -1;
        this.corners[5][1] = 1;
        this.corners[5][2] = -1;

        this.corners[6][0] = 1;
        this.corners[6][1] = 1;
        this.corners[6][2] = -1;

        this.corners[7][0] = 1;
        this.corners[7][1] = -1;
        this.corners[7][2] = -1;

        // defineerime massiivi suuruse pindade koordinaatide hoidmiseks
        this.faces = new int[12][4];

        this.faces[0][0] = 0;
        this.faces[0][1] = 1;
        this.faces[0][2] = 2;
        this.faces[0][3] = 1;

        this.faces[1][0] = 2;
        this.faces[1][1] = 3;
        this.faces[1][2] = 0;
        this.faces[1][3] = 1;

        this.faces[2][0] = 1;
        this.faces[2][1] = 5;
        this.faces[2][2] = 6;
        this.faces[2][3] = 2;

        this.faces[3][0] = 6;
        this.faces[3][1] = 2;
        this.faces[3][2] = 1;
        this.faces[3][3] = 2;

        this.faces[4][0] = 5;
        this.faces[4][1] = 4;
        this.faces[4][2] = 7;
        this.faces[4][3] = 3;

        this.faces[5][0] = 7;
        this.faces[5][1] = 6;
        this.faces[5][2] = 5;
        this.faces[5][3] = 3;

        this.faces[6][0] = 4;
        this.faces[6][1] = 0;
        this.faces[6][2] = 3;
        this.faces[6][3] = 4;

        this.faces[7][0] = 3;
        this.faces[7][1] = 7;
        this.faces[7][2] = 4;
        this.faces[7][3] = 4;

        this.faces[8][0] = 3;
        this.faces[8][1] = 2;
        this.faces[8][2] = 6;
        this.faces[8][3] = 5;

        this.faces[9][0] = 6;
        this.faces[9][1] = 7;
        this.faces[9][2] = 3;
        this.faces[9][3] = 5;

        this.faces[10][0] = 0;
        this.faces[10][1] = 5;
        this.faces[10][2] = 1;
        this.faces[10][3] = 6;

        this.faces[11][0] = 0;
        this.faces[11][1] = 4;
        this.faces[11][2] = 5;
        this.faces[11][3] = 6;
    }
}
