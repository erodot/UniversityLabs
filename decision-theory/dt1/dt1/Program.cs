using System;
using System.Linq;
using System.Collections.Generic;

namespace dt1
{
    class Vector{
        private IList<double> v;
        public int length { get; }

        public Vector(IList<double> withElements){
            v = withElements;
            length = withElements.Count;
        }

        public Vector Normalize()
        {
            IList<double> normalized = new List<double>();
            double norm = v.Sum();

            for (int i = 0; i < length; i++)
                normalized.Add(v[i] / norm);

            v = normalized;
            return this;
        }

        public double get(int i){
            return v[i];
        }

        public static Vector operator+(Vector first, Vector second){
            if (first.length != second.length)
                throw new Exception("Vectors have different lengthes.");

            IList<double> result = new List<double>();
            for (int i = 0; i < first.length; i++){
                result.Add(first.v[i] + second.v[i]);
            }

            return new Vector(result);
        }

        public static Vector operator *(double first, Vector second)
        {
            IList<double> result = new List<double>();
            for (int i = 0; i < second.length; i++)
            {
                result.Add(first * second.v[i]);
            }

            return new Vector(result);
        }

        public void Print(string withHeader = "", bool andPercents = false){
            Console.WriteLine("\n" + withHeader);
            for (int i = 0; i < length; i++)
                Console.WriteLine((i + 1) + ": " + (andPercents ? (Math.Round(100 * v[i], 2) + "%"): v[i].ToString()));
        }
    }

    class SquareMatrix{
        private IList<double> m;
        private int dimensions;

        public SquareMatrix(params double[] withElements){
            if (Math.Sqrt(withElements.Length) % 1 > Double.Epsilon)
                throw new Exception("These count of elements cannot be put in square matrix.");
                
            m = new List<double>(withElements);
            dimensions = (int)Math.Sqrt(withElements.Length);
        }

        public static SquareMatrix FromColumns(IList<Vector> columns){
            IList<double> m = new List<double>();
            for (int i = 0; i < columns[0].length; i++) // for every row
                for (int j = 0; j < columns.Count; j++) // for every column
                    m.Add(columns[j].get(i));
            return new SquareMatrix(m.ToArray());
        }

        public SquareMatrix NormalizeColumns(){
            IList<Vector> columns = new List<Vector>();
            for (int i = 0; i < dimensions; i++){
                IList<double> column = new List<double>();
                for (int j = i; j < dimensions * dimensions; j+=dimensions)
                    column.Add(m[j]);
                columns.Add(new Vector(column).Normalize());
            }
            return SquareMatrix.FromColumns(columns);
        }

        public Vector GetRowsSum()
        {
            IList<double> sum = new List<Double>();

            for (int i = 0; i < dimensions; i++)
            {
                double row_sum = 0;
                for (int j = 0; j < dimensions; j++)
                    row_sum += m[i * dimensions + j];
                sum.Add(row_sum);
            }

            return new Vector(sum);
        }

        public Vector GetColumn(int i){

            IList<double> col = new List<double>();
            for (int j = i; j < dimensions * dimensions; j += dimensions){
                col.Add(m[j]);
            }
            return new Vector(col);
        }
    }

    class MainClass
    {
        public static void Main(string[] args)
        {
            // FILLING DATA
            SquareMatrix A = new SquareMatrix(1, 3/2d, 
                                              2/3d, 1);

            SquareMatrix Ak = new SquareMatrix(1, 1/2d,
                                               2, 1);
            SquareMatrix Ad = new SquareMatrix(1, 3, 
                                               1/3d, 1);

            SquareMatrix Akg = new SquareMatrix(1, 1/2d, 1/3d, 
                                                2, 1, 1/2d, 
                                                3, 2, 1);
            SquareMatrix Akb = new SquareMatrix(1, 1/2d, 2, 
                                                2, 1, 3, 
                                                1/2d, 1/3d, 1);

            SquareMatrix Adg = new SquareMatrix(1, 1/4d, 1/2d, 
                                                4, 1, 1/3d,
                                                2, 3, 1);
            SquareMatrix Adb = new SquareMatrix(1, 2, 1/4d, 
                                                1/2d, 1, 1/3d, 
                                                4, 3, 1);

            // NORMALIZING
            Vector A_V = A.NormalizeColumns().GetColumn(0);
            Vector Ak_V = Ak.NormalizeColumns().GetColumn(0);
            Vector Ad_V = Ad.NormalizeColumns().GetColumn(0);

            Vector AkgSum = Akg.NormalizeColumns().GetRowsSum().Normalize();
            Vector AkbSum = Akb.NormalizeColumns().GetRowsSum().Normalize();
            Vector AdgSum = Adg.NormalizeColumns().GetRowsSum().Normalize();
            Vector AdbSum = Adb.NormalizeColumns().GetRowsSum().Normalize();

            // COMPUTATION
            Vector AkSum = Ak_V.get(0) * AkgSum + Ak_V.get(1) * AkbSum;
            Vector AdSum = Ad_V.get(0) * AdgSum + Ad_V.get(1) * AdbSum;

            Vector ASum = A_V.get(0) * AkSum + A_V.get(1) * AdSum;

            // RESULTS
            ASum.Print(withHeader:"ASum", andPercents:true);
        }
    }
}
