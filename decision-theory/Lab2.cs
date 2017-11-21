using System;

namespace DecisionTheory{
    public class Lab2{
        public static void Main(string[] args)
        {
            Matrix<double> A = new Matrix<double>(
                rowsCount:12,
                columnsCount:8,
                elements: new double[]{
                    5, 3, 1, 2, 8, 4, 6, 7,
                    5, 4, 3, 1, 8, 2, 6, 7,
                    1, 7, 5, 4, 8, 2, 3, 6,
                    6, 4, 2.5, 2.5, 8, 1, 7, 5,
                    8, 2, 4, 6, 3, 5, 1, 7,
                    5, 6, 4, 3, 2, 1, 7, 8,
                    6, 1, 2, 3, 5, 4, 8, 7,
                    6, 1, 3, 2, 7, 4, 6, 8,
                    6, 1, 3, 2, 5, 4, 7, 8,
                    5, 3, 2, 1, 8, 4, 6, 7,
                    7, 1, 3, 2, 6, 5, 5, 8,
                    1, 6, 5, 3, 8, 4, 2, 7,
                }
            );
        }
    }
}