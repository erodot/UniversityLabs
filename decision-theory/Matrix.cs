using System;
using System.Linq;
using System.Collections.Generic;

namespace DecisionTheory
{
    public class Matrix<T>{
        private List<T> m;
        private uint rowsCount;
        private uint columnsCount;

        public Matrix(uint rowsCount, uint columnsCount, params T[] elements){
            this.rowsCount = rowsCount;
            this.columnsCount = columnsCount;
            this.m = new List<T>(elements);

            long lack = elements.Count() - rowsCount * columnsCount;

            if(lack > 0){
                T[] nulls = new T[lack].Populate(default(T)); 
                m.AddRange(new List<T>(nulls));
            }  
            else if(lack < 0){
                m = m.GetRange(0, (int)Math.Abs(lack));
            }
            else {
                // do nothing
            }
        }

        public static Matrix<T> FromColumns(IList<Vector<T>> columns){
            List<T> m = new List<T>();
            uint rowsCount = columns[0].length;
            uint columnsCount = (uint)columns.Count;

            for (int i = 0; i < rowsCount; i++) // for every row
                for (int j = 0; j < columnsCount; j++) // for every column
                    m.Add(columns[j].get(i));
            return new Matrix<T>(rowsCount, columnsCount, m.ToArray());
        }

        public Matrix<T> NormalizeColumns(){
            List<Vector<T>> columns = new List<Vector<T>>();
            for (int i = 0; i < rowsCount; i++){
                List<T> column = new List<T>();
                for (int j = i; j < (rowsCount * columnsCount); j += (int)columnsCount)
                    column.Add(m[j]);
                columns.Add(new Vector<T>(column).Normalize());
            }
            return Matrix<T>.FromColumns(columns);
        }

        public Vector<T> GetRowsSum()
        {
            List<T> sum = new List<T>();

            for (int i = 0; i < rowsCount; i++)
            {
                T row_sum = default(T);
                for (int j = 0; j < columnsCount; j++)
                    row_sum += (dynamic)m[i * (int)columnsCount + j];
                sum.Add(row_sum);
            }

            return new Vector<T>(sum);
        }

        public Vector<T> GetColumn(uint i){

            List<T> col = new List<T>();
            for (int j = (int)i; j < (rowsCount * columnsCount); j += (int)columnsCount){
                col.Add(m[j]);
            }
            return new Vector<T>(col);
        }
    }

    public static class ArrayExtensions{
        public static T[] Populate<T>(this T[] arr, T value ) {
            for ( int i = 0; i < arr.Length; i++)
                arr[i] = value;

            return arr;
        }
    }
}