package ThreeDee.shapes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstraktne klass, mis defineerib joonistatava objekti olulised meetodid.
 *
 */
public abstract class AbstractShape implements Shape {

    // igal objektil peavad olema massiivid nurkade ja pindade jaoks
    protected double[][] corners;
    protected int[][] faces;

    /**
     * Meetod mis loeb CSV failist nurkade ja pindade koordinaadid ning
     * määrab need koordinaadid vastavasse massiivi.
     *
     * NB! Juhul, kui andmed ei ole failis vaid on defineeritud otse meetodis (nagu näiteks TriangleCube objekt),
     * siis loetakse see meetod lihtsalt üle
     *
     * @param fileName koordinaate sisaldava faili prefix
     */
    public void loadData(String fileName) {

        List<double[]> list = new LinkedList<double[]>();

        // proovime faili avada ning NURKADE andmed massiivi lugeda
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + "_corners.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] ar = line.split(",");
                double[] temp = new double[3];
                temp[0] = Double.parseDouble(ar[0]);
                temp[1] = Double.parseDouble(ar[1]);
                temp[2] = Double.parseDouble(ar[2]);
                list.add(temp);
                line = br.readLine();
            }

        // kui ebaõnnestusime, siis anname veateate
        } catch (Exception e){
            throw new IllegalStateException("Faili laadimine ebaõnnestus!");
        }
        this.corners = list.toArray(new double[0][0]);


        List<int[]> list2 = new LinkedList<int[]>();

        // proovime faili avada ning PINDADE andmed massiivi lugeda
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + "_faces.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] ar = line.split(",");
                int[] temp = new int[4];
                temp[0] = Integer.parseInt(ar[0]);
                temp[1] = Integer.parseInt(ar[1]);
                temp[2] = Integer.parseInt(ar[2]);
                temp[3] = 0;
                list2.add(temp);
                line = br.readLine();
            }

        // kui ebaõnnestusime, siis anname veateate
        } catch (Exception e){
            throw new IllegalStateException("Faili laadimine ebaõnnestus!");
        }
        this.faces = list2.toArray(new int[0][0]);
    }

    /**
     * Getter meetod nurkade massiivi tagastamiseks
     * @return
     */
    public double[][] getCorners(){
        return this.corners;
    }

    /**
     * Getter meetod pindade massiivi tagastamiseks
     * @return
     */
    public int[][] getFaces(){
        return this.faces;
    }


    /**
     * Setter meetod punna tagasikirjutamiseks massiivi
     *
     * @param i         pinna asukoht massiivis (massivi indeks)
     * @param newValue  pinna uus väärtus
     */
    public void setFaces(int i, int[] newValue) {
        this.faces[i] = newValue;
    }
}
