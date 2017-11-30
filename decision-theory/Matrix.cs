using System;
using System.Linq;
using System.Collections.Generic;

namespace DecisionTheory
{
    public class Matrix<T>{
        public List<T> m { get; }
        public uint rowsCount { get; }
        public uint columnsCount { get; }

        public List<Vector<T>> rows {
            get {
                List<Vector<T>> _rows = new List<Vector<T>>();
                for(int i = 0; i< rowsCount; i++){
                    List<T> row = new List<T>();
                    for(int j = 0; j< columnsCount; j++)
                        row.Add(m[(int)(i*columnsCount + j)]);
                    _rows.Add(new Vector<T>(row));
                }
                return _rows;
            }
        }

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

        public static Matrix<T> FromColumns(List<Vector<T>> columns){
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
                    row_sum += (dynamic)get((uint)i, (uint)j);
                sum.Add(row_sum);
            }

            return new Vector<T>(sum);
        }

        public Vector<T> GetColumnsSum()
        {
            List<T> sum = new List<T>();

            for (int i = 0; i < columnsCount; i++)
            {
                T column_sum = default(T);
                for (int j = 0; j < rowsCount; j++)
                    column_sum += (dynamic)get((uint)j, (uint)i);
                sum.Add(column_sum);
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

        public T get(uint x, uint y){
            return m[(int)(x * columnsCount + y)];
        }

        /// Медіана по кожному стовпчику.
        public Vector<T> GetColumnsMedian(){
            List<T> medians = new List<T>();
            for(int i=0; i<columnsCount; i++){
                Vector<T> columnSorted = GetColumn((uint)i).SortAsc();

                if(columnSorted.length % 2 == 0){ // парна кількість елементів
                    T first = columnSorted.get(((int)(columnSorted.length) / 2) - 1);
                    T second = columnSorted.get(((int)(columnSorted.length) / 2));
                    dynamic average = ((dynamic)first + (dynamic)second) / 2;
                    medians.Add(average);
                }
                else{ // непарна
                    medians.Add(columnSorted.get((int)(columnSorted.length) / 2));
                }
            }
            
            return new Vector<T>(medians);
        }

        public void Print(string withHeader=""){
            Console.WriteLine("\n" + withHeader);
            foreach(Vector<T> row in rows){
                Console.WriteLine(string.Join(" ", row.ToList().Select(el => el.ToString()).Select(el => el = el.PadRight(3 - el.Length))));
            }
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