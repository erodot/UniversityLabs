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

        public void Print(string withHeader = "", bool andPercents = false){
            Console.WriteLine("\n" + withHeader);
            for (int i = 0; i < length; i++)
                Console.WriteLine((i + 1) + ": " + (andPercents ? (Math.Round(100 * (dynamic)v[i], 2) + "%"): v[i].ToString()));
        }
    }
}