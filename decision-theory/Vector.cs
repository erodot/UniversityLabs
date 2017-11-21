using System;
using System.Linq;
using System.Collections.Generic;

namespace DecisionTheory
{
    public class Vector<T>{
        private List<T> v;
        public uint length { get; }

        public Vector(List<T> withElements){
            v = withElements;
            length = (uint)withElements.Count;
        }

        public Vector<T> Normalize()
        {
            List<T> normalized = new List<T>();
            T norm = default(T);
            for(int i=0; i<length; i++)
                norm = norm + (dynamic)v[i];

            for (int i = 0; i < length; i++)
                normalized.Add((dynamic)v[i] / norm);

            v = normalized;
            return this;
        }

        public T get(int i){
            return v[i];
        }

        public static Vector<T> operator+(Vector<T> first, Vector<T> second){
            if (first.length != second.length)
                throw new Exception("Vectors have different lengthes.");

            List<T> result = new List<T>();
            for (int i = 0; i < first.length; i++){
                result.Add((dynamic)first.v[i] + second.v[i]);
            }

            return new Vector<T>(result);
        }

        public static Vector<T> operator *(T first, Vector<T> second)
        {
            List<T> result = new List<T>();
            for (int i = 0; i < second.length; i++)
            {
                result.Add((dynamic)first * second.v[i]);
            }

            return new Vector<T>(result);
        }

        public void PrintAsColumn(string withHeader = "", bool andPercents = false){
            Console.WriteLine("\n" + withHeader);
            for (int i = 0; i < length; i++)
                Console.WriteLine((i + 1) + ": " + (andPercents ? (Math.Round(100 * (dynamic)v[i], 2) + "%"): v[i].ToString()));
        }

        public void PrintAsRow(string withHeader = "", bool andPercents = false){
            string row = withHeader;
            
            for (int i = 0; i < length; i++)
                row += " " + (andPercents ? (Math.Round(100 * (dynamic)v[i], 2) + "%"): v[i].ToString());
            
            Console.WriteLine(row);
        }

        public Vector<T> Round(uint toDigits){
            List<T> rounded_v = new List<T>();
            for(int i=0; i < length; i++){
                rounded_v.Add(Math.Round((dynamic)v[i], (int)toDigits));
            }

            return new Vector<T>(rounded_v);
        }

        /// Ранг по вектору.
        public Vector<int> GetRanking(){
            List<int> ranking = new List<int>();
            for(int i=0; i < length; i++){
                int lessThan = 0;
                for(int j=0; j < length; j++)
                    if((dynamic)v[i]>(dynamic)v[j])
                        lessThan++;
                ranking.Add(lessThan + 1);
            }
            return new Vector<int>(ranking);
        }

        public Vector<T> SortAsc(){
            bool flag = true;
            T temp;

            List<T> sorted = new List<T>();
            foreach(T _ in v)
                sorted.Add(_);

            for (int i = 1; (i <= (length - 1)) && flag; i++)
            {
                flag = false;
                for (int j = 0; j < (length - 1); j++)
                {
                    if ((dynamic)sorted[j + 1] < (dynamic)sorted[j])
                    {
                        temp = sorted[j];
                        sorted[j] = sorted[j + 1];
                        sorted[j + 1] = temp;
                        flag = true;
                    }
                }
            }

            return new Vector<T>(sorted);
        }

        public List<T> ToList(){
            return v;
        }
    }
}