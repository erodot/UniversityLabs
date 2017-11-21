using System;
using System.Linq;
using System.Collections.Generic;

namespace DecisionTheory
{
    class Lab1
    {
        public static void Main(string[] args)
        {
            // FILLING DATA
            Matrix<double> A = new Matrix<double>(
                rowsCount:2, 
                columnsCount:2, 
                elements: new double[]{
                    1, 3/2d, 
                    2/3d, 1
                }
            );

            Matrix<double> Ak = new Matrix<double>(
                rowsCount:2, 
                columnsCount:2, 
                elements: new double[]{
                    1, 1/2d,
                    2, 1
                }
            );

            Matrix<double> Ad = new Matrix<double>(
                rowsCount:2, 
                columnsCount:2, 
                elements: new double[]{
                    1, 3, 
                    1/3d, 1
                }
            );

            Matrix<double> Akg = new Matrix<double>(
                rowsCount:3, 
                columnsCount:3, 
                elements: new double[]{
                    1, 1/2d, 1/3d, 
                    2, 1, 1/2d, 
                    3, 2, 1
                }
            );

            Matrix<double> Akb = new Matrix<double>(
                rowsCount:3, 
                columnsCount:3, 
                elements: new double[]{
                    1, 1/2d, 2, 
                    2, 1, 3, 
                    1/2d, 1/3d, 1
                }
            );

            Matrix<double> Adg = new Matrix<double>(
                rowsCount:3, 
                columnsCount:3, 
                elements: new double[]{
                    1, 1/4d, 1/2d, 
                    4, 1, 1/3d,
                    2, 3, 1
                }
            );

            Matrix<double> Adb = new Matrix<double>(
                rowsCount:3, 
                columnsCount:3, 
                elements: new double[]{
                    1, 2, 1/4d, 
                    1/2d, 1, 1/3d, 
                    4, 3, 1
                }
            );

            // NORMALIZING
            Vector<double> A_V = A.NormalizeColumns().GetColumn(0);
            Vector<double> Ak_V = Ak.NormalizeColumns().GetColumn(0);
            Vector<double> Ad_V = Ad.NormalizeColumns().GetColumn(0);

            Vector<double> AkgSum = Akg.NormalizeColumns().GetRowsSum().Normalize();
            Vector<double> AkbSum = Akb.NormalizeColumns().GetRowsSum().Normalize();
            Vector<double> AdgSum = Adg.NormalizeColumns().GetRowsSum().Normalize();
            Vector<double> AdbSum = Adb.NormalizeColumns().GetRowsSum().Normalize();

            // COMPUTATION
            Vector<double> AkSum = Ak_V.get(0) * AkgSum + Ak_V.get(1) * AkbSum;
            Vector<double> AdSum = Ad_V.get(0) * AdgSum + Ad_V.get(1) * AdbSum;

            Vector<double> ASum = A_V.get(0) * AkSum + A_V.get(1) * AdSum;

            // RESULTS
            ASum.PrintAsColumn(withHeader:"ASum", andPercents:true);
        }
    }
}
