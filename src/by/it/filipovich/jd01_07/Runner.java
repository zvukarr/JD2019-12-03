package by.it.filipovich.jd01_07;

public class Runner {
    public static void main(String[] args) {

        Scalar scalar = new Scalar(3.1415);
        System.out.println(scalar);

        Scalar scalar1 = new Scalar(scalar);
        System.out.println(scalar1);

        Scalar scalar2 = new Scalar("3.1415");
        System.out.println(scalar2);

        Vector vector = new Vector(new double[]{1.0, 2.0, 4.0});
        System.out.println(vector);

        Vector vector1 = new Vector(vector);
        System.out.println(vector1);

        Vector vector2 = new Vector("{1.0, 2.0, 4.0}");
        System.out.println(vector2);

        Matrix matrix = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        System.out.println(matrix);

        Matrix matrix2 = new Matrix(matrix);
        System.out.println(matrix2);

        Matrix matrix3 = new Matrix("{{1,2,3},{4,5,6},{7,8,9}}");
        System.out.println(matrix3);

    }
}
